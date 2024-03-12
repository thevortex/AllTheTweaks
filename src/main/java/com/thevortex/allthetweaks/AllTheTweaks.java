package com.thevortex.allthetweaks;

import com.thevortex.allthetweaks.blocks.TweakBlocks;
import com.thevortex.allthetweaks.config.Configuration;
import com.thevortex.allthetweaks.events.Events;
import com.thevortex.allthetweaks.proxy.ClientProxy;
import com.thevortex.allthetweaks.proxy.IProxy;
import com.thevortex.allthetweaks.proxy.ServerProxy;
import com.thevortex.allthetweaks.special_registry.TFCJobs;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.launch.MixinBootstrap;

@Mod(AllTheTweaks.MODID)
public class AllTheTweaks
{
    public static final String MODID = "allthetweaks";
    public static final Logger LOGGER = LogManager.getLogger(MODID);
    public static IProxy proxy = DistExecutor.safeRunForDist(() -> ClientProxy::new, () -> ServerProxy::new);
    public static long IPCC;
    public static boolean configFire = false;
    public static String ATM;
    public static String DISPLAY;
    public static ResourceLocation BACKGROUND;

    public AllTheTweaks() {
        MixinBootstrap.init();

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Configuration.COMMON_SPEC);
        // Register ourselves for server and other game events we are interested in
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        TweakBlocks.BLOCKS.register(modEventBus);
        TweakBlocks.ITEMS.register(modEventBus);
        TweakBlocks.CREATIVE_TABS.register(modEventBus);
        if (ModList.get().isLoaded("tfc") /*&& ModList.get().isLoaded("firmalife")*/) {
            TFCJobs.POI_TYPES.register(modEventBus);
        }

        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(Configuration.class);
        MinecraftForge.EVENT_BUS.register(Events.class);
    }
}
