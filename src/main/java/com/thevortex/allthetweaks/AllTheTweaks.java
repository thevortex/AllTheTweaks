package com.thevortex.allthetweaks;

import com.thevortex.allthetweaks.blocks.TweakBlocks;
import com.thevortex.allthetweaks.config.Configuration;
import com.thevortex.allthetweaks.config.Reference;
import com.thevortex.allthetweaks.proxy.MyCons;
import com.thevortex.allthetweaks.proxy.OffThread;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.resources.PlayerSkin;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.loading.FMLPaths;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.common.NeoForge;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.launch.MixinBootstrap;

@Mod(AllTheTweaks.MODID)
public class AllTheTweaks
{
    public static final String MODID = "allthetweaks";
    public static final Logger LOGGER = LogManager.getLogger(MODID);
    public static long IPCC;
    public static boolean configFire = false;
    public static String ATM;
    public static String DISPLAY;
    public static ResourceLocation BACKGROUND;

    public AllTheTweaks(IEventBus modEventBus, ModContainer modContainer) {
        MixinBootstrap.init();

        modContainer.registerConfig(ModConfig.Type.COMMON, Configuration.COMMON_SPEC);
        // Register ourselves for server and other game events we are interested in
        TweakBlocks.BLOCKS.register(modEventBus);
        TweakBlocks.ITEMS.register(modEventBus);
        TweakBlocks.CREATIVE_TABS.register(modEventBus);
        
        
        //NeoForge.EVENT_BUS.register(Configuration.class);
        //NeoForge.EVENT_BUS.register(Events.class);
        Configuration.loadConfig(Configuration.COMMON_SPEC, FMLPaths.CONFIGDIR.get().resolve("allthetweaks-common.toml"));
        Configuration.bakeConfigs();

    }

       @EventBusSubscriber(modid = Reference.MOD_ID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
  public static class ClientProxy {

    @SubscribeEvent
    public static void setupClient(FMLClientSetupEvent evt) {
        if(Configuration.COMMON.discord.get()) {
            NeoForge.EVENT_BUS.register(UpdateDRP.class);
            DRP.start();
            MyCons.setWindowIcon(); 
           
        }
    }
   
   

    
  }
    
}
    

