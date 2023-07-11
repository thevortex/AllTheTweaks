package com.thevortex.allthetweaks.config;

import java.util.Map;

import net.minecraftforge.fml.event.config.ModConfigEvent;
import org.apache.commons.lang3.tuple.Pair;

import com.thevortex.allthetweaks.AllTheTweaks;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@EventBusSubscriber(modid = AllTheTweaks.MODID, bus = EventBusSubscriber.Bus.MOD)
public class Configuration {
	public static final ForgeConfigSpec COMMON_SPEC;
	public static final Common COMMON;
	static {
		final Pair<Common, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Common::new);
		COMMON_SPEC = specPair.getRight();
		COMMON = specPair.getLeft();
	}
	public static class Common {
		public final ForgeConfigSpec.IntValue mainmode;
		public final ForgeConfigSpec.BooleanValue discord;
		public final ForgeConfigSpec.IntValue majorver;
		public final ForgeConfigSpec.IntValue minorver;
		public final ForgeConfigSpec.IntValue minorrevver;
		//public final ForgeConfigSpec.IntValue tickSpeed;
		
		public Common(ForgeConfigSpec.Builder BUILDER) {
			BUILDER.push("packmode");
			mainmode = BUILDER.comment("ATM = 0 SLOP = 1 SKY = 2 MAGIC = 3 EXPERT = 4 VB = 5").defineInRange("enable",
					0, 0, 5);
			BUILDER.pop();

			BUILDER.push("discord");
			discord = BUILDER.comment("Enable Discord Rich Prescence").define("discord",true);
			BUILDER.pop();

			BUILDER.push("packversionmaj");
			majorver = BUILDER.comment("Pack Release Version Format : X").defineInRange("major",
					1, 0, 32768);
			BUILDER.pop();

			BUILDER.push("packversionmin");
			minorver = BUILDER.comment("Pack Minor Version : X").defineInRange("minor",
					0, 0, 32768);
			BUILDER.pop();

			BUILDER.push("packversionminrev");
			minorrevver = BUILDER.comment("Pack Minor Version Revision : X").defineInRange("minorrev",
					0, 0, 32768);
			BUILDER.pop();


		}
	}
	@SubscribeEvent
	public static void onModConfigEvent(final ModConfigEvent configEvent) {
		bakeConfigs();
		AllTheTweaks.configFire = true;
		
	}
	@SubscribeEvent
	public static void commonSetupEvent(final FMLCommonSetupEvent event) {
		AllTheTweaks.proxy.init();
	}
	private static void bakeConfigs() {

		AllTheTweaks.proxy.assignBG(Configuration.COMMON.mainmode.get());
		int SLOP = Configuration.COMMON.mainmode.get();
		if(SLOP == 1) {
        	AllTheTweaks.IPCC = cfgSLOP.IPCC;
        	AllTheTweaks.ATM = cfgSLOP.ATM;
        	AllTheTweaks.DISPLAY = cfgSLOP.DISPLAY;
        	AllTheTweaks.BACKGROUND = cfgSLOP.BACKGROUND;
        	return;
        }
		int SKY = Configuration.COMMON.mainmode.get();
		if (SKY == 2) {
			AllTheTweaks.IPCC = cfgSKY.IPCC;
			AllTheTweaks.ATM = cfgSKY.ATM;
			AllTheTweaks.DISPLAY = cfgSKY.DISPLAY;
			AllTheTweaks.BACKGROUND = cfgSKY.BACKGROUND;
			return;
		}
		int MAGIC = Configuration.COMMON.mainmode.get();
		if (MAGIC == 3) {
			AllTheTweaks.IPCC = cfgMAGIC.IPCC;
			AllTheTweaks.ATM = cfgMAGIC.ATM;
			AllTheTweaks.DISPLAY = cfgMAGIC.DISPLAY;
			AllTheTweaks.BACKGROUND = cfgMAGIC.BACKGROUND;
			return;
		}
		int EXPERT = Configuration.COMMON.mainmode.get();
		if (EXPERT == 4) {
			AllTheTweaks.IPCC = cfgExpert.IPCC;
			AllTheTweaks.ATM = cfgExpert.ATM;
			AllTheTweaks.DISPLAY = cfgExpert.DISPLAY;
			AllTheTweaks.BACKGROUND = cfgExpert.BACKGROUND;
			return;
		}
		int VB = Configuration.COMMON.mainmode.get();
		if (VB == 5) {
			AllTheTweaks.IPCC = cfgVB.IPCC;
			AllTheTweaks.ATM = cfgVB.ATM;
			AllTheTweaks.DISPLAY = cfgVB.DISPLAY;
			AllTheTweaks.BACKGROUND = cfgVB.BACKGROUND;
			return;
		}
		else {
        	AllTheTweaks.IPCC = cfgMain.IPCC;
        	AllTheTweaks.ATM = cfgMain.ATM;
        	AllTheTweaks.DISPLAY = cfgMain.DISPLAY;
        	AllTheTweaks.BACKGROUND = cfgMain.BACKGROUND;
        }

	}
}
