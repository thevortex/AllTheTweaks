package com.thevortex.allthetweaks;

import com.thevortex.allthetweaks.blocks.TweakBlocks;
import com.thevortex.allthetweaks.config.Configuration;
import com.thevortex.allthetweaks.events.Events;
import com.thevortex.allthetweaks.proxy.*;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.carver.WorldCarver;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

import com.thevortex.allthetweaks.proxy.IProxy;


import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.launch.MixinBootstrap;

import java.util.Set;


// The value here should match an entry in the META-INF/mods.toml file
@Mod("allthetweaks")
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
		MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(Configuration.class);
        MinecraftForge.EVENT_BUS.register(Events.class);

    }

	public void setup(final FMLCommonSetupEvent event) {


	}

}
