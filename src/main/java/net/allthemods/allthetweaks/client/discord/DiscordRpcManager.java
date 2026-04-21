package net.allthemods.allthetweaks.client.discord;

import net.neoforged.fml.ModList;
import net.neoforged.neoforge.client.event.ClientTickEvent;

import net.allthemods.allthetweaks.ATTConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fun.crashsystem.jdrpc.DiscordIPC;
import fun.crashsystem.jdrpc.activity.Activity;
import fun.crashsystem.jdrpc.activity.ActivityType;

import java.time.Instant;

public final class DiscordRpcManager {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(DiscordRpcManager.class);
    private static final Object LOCK = new Object();
    
    private static final String LOGO_KEY = "icon";
    private static final String DISCORD_URL = "https://discord.gg/allthemods";
    private static final long RECONNECT_DELAY_MS = 10_000L;
    
    private static boolean started;
    private static boolean shutdownHookRegistered;
    private static long startTime;
    private static long nextReconnectAt;
    
    private static DiscordIPC client;
    private static PackMode pack = PackMode.ATM11;
    private static RPCState state = RPCState.STARTING;
    private static Snapshot lastSnapshot;
    
    private DiscordRpcManager() { }
    
    public static void start() {
        synchronized (DiscordRpcManager.LOCK) {
            if (DiscordRpcManager.started) {
                DiscordRpcManager.LOGGER.debug("Cannot start, already started");
                return;
            }
            
            DiscordRpcManager.started = true;
            DiscordRpcManager.startTime = Instant.now().getEpochSecond();
            DiscordRpcManager.nextReconnectAt = 0L;
            DiscordRpcManager.pack = DiscordRpcManager.getConfiguredPack();
            DiscordRpcManager.state = RPCState.resolve();
            DiscordRpcManager.lastSnapshot = null;
            
            if (!DiscordRpcManager.shutdownHookRegistered) {
                Runtime.getRuntime().addShutdownHook(new Thread(DiscordRpcManager::shutdown, "AllTheTweaks-DiscordRpcShutdown"));
                DiscordRpcManager.shutdownHookRegistered = true;
            }
        }
        
        DiscordRpcManager.update();
        DiscordRpcManager.LOGGER.info("AllTheTweaks Discord RPC Manager started");
    }
    
    public static void shutdown() {
        synchronized (DiscordRpcManager.LOCK) {
            if (!DiscordRpcManager.started) {
                DiscordRpcManager.LOGGER.warn("Cannot shutdown, not started");
                return;
            }
            
            DiscordRpcManager.started = false;
            DiscordRpcManager.lastSnapshot = null;
            DiscordRpcManager.nextReconnectAt = 0L;
            DiscordRpcManager.startTime = 0L;
            
            DiscordRpcManager.closeClient();
            DiscordRpcManager.LOGGER.info("AllTheTweaks Discord RPC Manager stopped");
        }
    }
    
    public static void onClientTick(ClientTickEvent.Post event) {
        DiscordRpcManager.refreshFromGame();
        DiscordRpcManager.update();
    }
    
    private static void refreshFromGame() {
        boolean rpcEnabled = DiscordRpcManager.isConfiguredEnabled();
        PackMode nextPack = DiscordRpcManager.getConfiguredPack();
        RPCState nextState = RPCState.resolve();
        
        synchronized (DiscordRpcManager.LOCK) {
            if (!rpcEnabled) {
                if (DiscordRpcManager.started) {
                    DiscordRpcManager.started = false;
                    DiscordRpcManager.lastSnapshot = null;
                    DiscordRpcManager.nextReconnectAt = 0L;
                    DiscordRpcManager.startTime = 0L;
                    DiscordRpcManager.closeClient();
                }
                return;
            }
            
            if (!DiscordRpcManager.started) {
                DiscordRpcManager.started = true;
                DiscordRpcManager.startTime = Instant.now().getEpochSecond();
                DiscordRpcManager.nextReconnectAt = 0L;
                DiscordRpcManager.lastSnapshot = null;
            }
            
            if (DiscordRpcManager.pack.getApplicationId() != nextPack.getApplicationId()) {
                DiscordRpcManager.closeClient();
                DiscordRpcManager.nextReconnectAt = 0L;
                DiscordRpcManager.lastSnapshot = null;
            } else if (DiscordRpcManager.pack != nextPack) {
                DiscordRpcManager.lastSnapshot = null;
            }
            
            DiscordRpcManager.pack = nextPack;
            
            if (DiscordRpcManager.state != nextState) {
                DiscordRpcManager.state = nextState;
                DiscordRpcManager.lastSnapshot = null;
            }
        }
    }
    
    private static void update() {
        Snapshot snapshot;
        DiscordIPC currentClient;
        
        synchronized (DiscordRpcManager.LOCK) {
            if (!DiscordRpcManager.started) return;
            if (!DiscordRpcManager.connect()) return;
            
            snapshot = DiscordRpcManager.createSnapshot();
            currentClient = DiscordRpcManager.client;
            
            if (currentClient == null || !currentClient.isConnected()) return;
            if (snapshot.equals(DiscordRpcManager.lastSnapshot)) return;
        }
        
        try {
            Activity activity = DiscordRpcManager.buildActivity(snapshot);
            
            synchronized (DiscordRpcManager.LOCK) {
                if (DiscordRpcManager.client != currentClient || !currentClient.isConnected()) return;
                currentClient.setActivity(activity);
                DiscordRpcManager.lastSnapshot = snapshot;
            }
        } catch (Exception exception) {
            DiscordRpcManager.handleFailure(exception);
        }
    }
    
    private static Snapshot createSnapshot() {
        PackMode currentPack = DiscordRpcManager.pack;
        RPCState currentState = DiscordRpcManager.state;
        
        return new Snapshot(
                currentPack.getApplicationId(),
                currentState.display(),
                ModList.get().getMods().size() + " Mods",
                currentPack.getCurseforgeUrl(),
                DiscordRpcManager.startTime
        );
    }
    
    private static boolean connect() {
        if (DiscordRpcManager.client != null && DiscordRpcManager.client.isConnected()) {
            return true;
        }
        
        long now = System.currentTimeMillis();
        if (now < DiscordRpcManager.nextReconnectAt) return false;
        
        try {
            DiscordRpcManager.closeClient();
            
            Snapshot snapshot = DiscordRpcManager.createSnapshot();
            DiscordRpcManager.client = DiscordIPC.create(snapshot.applicationId());
            DiscordRpcManager.client.connect();
            
            DiscordRpcManager.nextReconnectAt = 0L;
            DiscordRpcManager.lastSnapshot = null;
            return true;
        } catch (Exception exception) {
            DiscordRpcManager.nextReconnectAt = now + DiscordRpcManager.RECONNECT_DELAY_MS;
            DiscordRpcManager.closeClient();
            DiscordRpcManager.LOGGER.debug("Discord RPC unavailable: {}", exception.getMessage());
            return false;
        }
    }
    
    private static Activity buildActivity(Snapshot snapshot) {
        return new Activity.Builder()
                .setType(ActivityType.PLAYING)
                .setDetails(snapshot.details())
                .setState(snapshot.state())
                .setLargeImage(DiscordRpcManager.LOGO_KEY, snapshot.details())
                .setStartTimestamp(snapshot.startTime())
                .addButton("CurseForge", snapshot.curseforgeUrl())
                .addButton("Discord", DiscordRpcManager.DISCORD_URL)
                .build();
    }
    
    private static void handleFailure(Exception exception) {
        synchronized (DiscordRpcManager.LOCK) {
            DiscordRpcManager.lastSnapshot = null;
            DiscordRpcManager.nextReconnectAt = System.currentTimeMillis() + DiscordRpcManager.RECONNECT_DELAY_MS;
            DiscordRpcManager.closeClient();
        }
        
        DiscordRpcManager.LOGGER.debug("Discord RPC update failed: {}", exception.getMessage());
        DiscordRpcManager.LOGGER.trace("Discord RPC update failure stack trace", exception);
    }
    
    private static void closeClient() {
        DiscordIPC currentClient = DiscordRpcManager.client;
        DiscordRpcManager.client = null;
        if (currentClient == null) return;
        
        try {
            if (currentClient.isConnected()) currentClient.clearActivity();
        } catch (Exception exception) {
            DiscordRpcManager.LOGGER.debug("Failed to clear Discord RPC activity", exception);
        }
        
        try {
            currentClient.close();
        } catch (Exception exception) {
            DiscordRpcManager.LOGGER.debug("Failed to close Discord RPC client", exception);
        }
    }
    
    private static boolean isConfiguredEnabled() {
        return ATTConfig.RPC_ENABLED.get();
    }
    
    private static PackMode getConfiguredPack() {
        return ATTConfig.PACK_MODE.get();
    }
    
    private record Snapshot(
            long applicationId,
            String details,
            String state,
            String curseforgeUrl,
            long startTime
    ) { }
}