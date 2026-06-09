package net.allthemods.allthetweaks.client.discord;

import net.neoforged.fml.ModList;

import net.allthemods.allthetweaks.ATTConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fun.crashsystem.jdrpc.DiscordIPC;
import fun.crashsystem.jdrpc.activity.Activity;
import fun.crashsystem.jdrpc.activity.ActivityType;

import java.time.Instant;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicBoolean;

public final class DiscordRpcManager {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(DiscordRpcManager.class);
    private static final Object LOCK = new Object();
    private static final Object CLIENT_LOCK = new Object();
    
    private static final String LOGO_KEY = "icon";
    private static final String DISCORD_URL = "https://discord.gg/allthemods";
    private static final long RECONNECT_DELAY_MS = 10_000L;
    private static final ExecutorService EXECUTOR = Executors.newSingleThreadExecutor(task -> {
        Thread thread = new Thread(task, "AllTheTweaks-DiscordRpc");
        thread.setDaemon(true);
        return thread;
    });
    private static final AtomicBoolean workerScheduled = new AtomicBoolean();
    
    private static boolean started;
    private static boolean shutdownHookRegistered;
    private static boolean updateRequested;
    private static boolean closeRequested;
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
            
            if (!DiscordRpcManager.shutdownHookRegistered) {
                Runtime.getRuntime().addShutdownHook(new Thread(DiscordRpcManager::shutdown, "AllTheTweaks-DiscordRpcShutdown"));
                DiscordRpcManager.shutdownHookRegistered = true;
            }
        }
        
        DiscordRpcManager.refreshFromConfig();
        DiscordRpcManager.LOGGER.info("AllTheTweaks Discord RPC Manager started");
    }
    
    public static void refreshFromConfig() {
        if (!DiscordRpcManager.isConfiguredEnabled()) {
            synchronized (DiscordRpcManager.LOCK) {
                DiscordRpcManager.started = false;
                DiscordRpcManager.lastSnapshot = null;
                DiscordRpcManager.nextReconnectAt = 0L;
                DiscordRpcManager.startTime = 0L;
                DiscordRpcManager.closeRequested = true;
            }

            DiscordRpcManager.scheduleUpdate();
            return;
        }

        PackMode nextPack = DiscordRpcManager.getConfiguredPack();
        RPCState nextState = RPCState.resolve();

        synchronized (DiscordRpcManager.LOCK) {
            if (!DiscordRpcManager.started) {
                DiscordRpcManager.started = true;
                DiscordRpcManager.startTime = Instant.now().getEpochSecond();
                DiscordRpcManager.nextReconnectAt = 0L;
                DiscordRpcManager.lastSnapshot = null;
            }

            if (DiscordRpcManager.pack.getApplicationId() != nextPack.getApplicationId()) {
                DiscordRpcManager.closeRequested = true;
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

        DiscordRpcManager.scheduleUpdate();
    }

    private static void shutdown() {
        synchronized (DiscordRpcManager.LOCK) {
            DiscordRpcManager.started = false;
            DiscordRpcManager.lastSnapshot = null;
            DiscordRpcManager.nextReconnectAt = 0L;
            DiscordRpcManager.startTime = 0L;
            DiscordRpcManager.closeRequested = false;
        }
        
        synchronized (DiscordRpcManager.CLIENT_LOCK) {
            DiscordRpcManager.closeClient();
        }
    }
    
    private static void scheduleUpdate() {
        synchronized (DiscordRpcManager.LOCK) {
            DiscordRpcManager.updateRequested = true;
        }
        
        if (!DiscordRpcManager.workerScheduled.compareAndSet(false, true)) {
            return;
        }
        
        try {
            DiscordRpcManager.EXECUTOR.execute(DiscordRpcManager::doWork);
        } catch (RejectedExecutionException exception) {
            DiscordRpcManager.workerScheduled.set(false);
            DiscordRpcManager.LOGGER.debug("Discord RPC worker rejected update", exception);
        }
    }
    
    private static void doWork() {
        try {
            while (true) {
                synchronized (DiscordRpcManager.LOCK) {
                    DiscordRpcManager.updateRequested = false;
                }
                
                DiscordRpcManager.update();
                
                synchronized (DiscordRpcManager.LOCK) {
                    if (!DiscordRpcManager.updateRequested) {
                        return;
                    }
                }
            }
        } finally {
            DiscordRpcManager.workerScheduled.set(false);
            
            synchronized (DiscordRpcManager.LOCK) {
                if (DiscordRpcManager.updateRequested) {
                    DiscordRpcManager.scheduleUpdate();
                }
            }
        }
    }
    
    private static void update() {
        Snapshot snapshot;
        boolean shouldClose;
        
        synchronized (DiscordRpcManager.LOCK) {
            shouldClose = DiscordRpcManager.closeRequested;
            DiscordRpcManager.closeRequested = false;
            
            if (!DiscordRpcManager.started) {
                snapshot = null;
            } else {
                long now = System.currentTimeMillis();
                if (!shouldClose && now < DiscordRpcManager.nextReconnectAt) {
                    return;
                }
                
                snapshot = DiscordRpcManager.createSnapshot();
            }
        }
        
        if (shouldClose) {
            synchronized (DiscordRpcManager.CLIENT_LOCK) {
                DiscordRpcManager.closeClient();
            }
        }
        
        if (snapshot == null) return;
        if (!DiscordRpcManager.connect(snapshot)) return;
        
        synchronized (DiscordRpcManager.LOCK) {
            if (!DiscordRpcManager.isCurrentSnapshot(snapshot)) return;
            if (snapshot.equals(DiscordRpcManager.lastSnapshot)) return;
        }
        
        try {
            Activity activity = DiscordRpcManager.buildActivity(snapshot);
            
            synchronized (DiscordRpcManager.CLIENT_LOCK) {
                if (DiscordRpcManager.client == null || !DiscordRpcManager.client.isConnected()) return;
                DiscordRpcManager.client.setActivity(activity);
            }
            
            synchronized (DiscordRpcManager.LOCK) {
                if (DiscordRpcManager.started) {
                    DiscordRpcManager.lastSnapshot = snapshot;
                }
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
    
    private static boolean connect(Snapshot snapshot) {
        synchronized (DiscordRpcManager.CLIENT_LOCK) {
            if (DiscordRpcManager.client != null && DiscordRpcManager.client.isConnected()) {
                return true;
            }
            
            try {
                DiscordRpcManager.closeClient();
                
                DiscordRpcManager.client = DiscordIPC.create(snapshot.applicationId());
                DiscordRpcManager.client.connect();
                
                synchronized (DiscordRpcManager.LOCK) {
                    DiscordRpcManager.nextReconnectAt = 0L;
                    DiscordRpcManager.lastSnapshot = null;
                }
                return true;
            } catch (Exception exception) {
                synchronized (DiscordRpcManager.LOCK) {
                    DiscordRpcManager.nextReconnectAt = System.currentTimeMillis() + DiscordRpcManager.RECONNECT_DELAY_MS;
                    DiscordRpcManager.lastSnapshot = null;
                }
                
                DiscordRpcManager.closeClient();
                DiscordRpcManager.LOGGER.debug("Discord RPC unavailable: {}", exception.getMessage());
                DiscordRpcManager.LOGGER.trace("Discord RPC connection failure stack trace", exception);
                return false;
            }
        }
    }
    
    private static boolean isCurrentSnapshot(Snapshot snapshot) {
        if (!DiscordRpcManager.started) return false;
        
        return snapshot.applicationId() == DiscordRpcManager.pack.getApplicationId()
                && snapshot.details().equals(DiscordRpcManager.state.display())
                && snapshot.state().equals(ModList.get().getMods().size() + " Mods")
                && snapshot.curseforgeUrl().equals(DiscordRpcManager.pack.getCurseforgeUrl())
                && snapshot.startTime() == DiscordRpcManager.startTime;
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
        synchronized (DiscordRpcManager.CLIENT_LOCK) {
            DiscordRpcManager.closeClient();
        }
        
        synchronized (DiscordRpcManager.LOCK) {
            DiscordRpcManager.lastSnapshot = null;
            DiscordRpcManager.nextReconnectAt = System.currentTimeMillis() + DiscordRpcManager.RECONNECT_DELAY_MS;
        }
        
        DiscordRpcManager.LOGGER.debug("Discord RPC update failed: {}", exception.getMessage());
        DiscordRpcManager.LOGGER.trace("Discord RPC update failure stack trace", exception);
    }
    
    private static void closeClient() {
        DiscordIPC currentClient = DiscordRpcManager.client;
        DiscordRpcManager.client = null;
        if (currentClient == null) return;
        
        try {
            currentClient.clearActivity();
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
