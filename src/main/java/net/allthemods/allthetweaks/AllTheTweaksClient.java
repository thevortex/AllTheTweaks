package net.allthemods.allthetweaks;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.common.NeoForge;

import net.allthemods.allthetweaks.api.ATT;
import net.allthemods.allthetweaks.client.discord.DiscordRpcManager;
import net.allthemods.allthetweaks.client.window.ATTWindowModifier;

@Mod(value = ATT.MOD_ID, dist = Dist.CLIENT)
@EventBusSubscriber(modid = ATT.MOD_ID, value = Dist.CLIENT)
public class AllTheTweaksClient {
    
    public AllTheTweaksClient(final IEventBus bus, final ModContainer container) {
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }
    
    @SubscribeEvent
    private static void onClientSetup(final FMLClientSetupEvent event) {
        NeoForge.EVENT_BUS.addListener(DiscordRpcManager::onClientTick);
        event.enqueueWork(DiscordRpcManager::start);
        event.enqueueWork(() -> ATTWindowModifier.apply());
    }
    
    @SubscribeEvent
    public static void onConfigReloading(ModConfigEvent.Reloading event) {
        AllTheTweaksClient.handle(event.getConfig());
    }
    
    private static void handle(ModConfig config) {
        if (config.getSpec() != ATTConfig.CLIENT) return;
        ATTWindowModifier.apply();
    }
}
