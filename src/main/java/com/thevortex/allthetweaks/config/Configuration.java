package com.thevortex.allthetweaks.config;

import java.util.Map;

import java.nio.file.Path;
import org.apache.commons.lang3.tuple.Pair;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import com.thevortex.allthetweaks.AllTheTweaks;
import com.thevortex.allthetweaks.proxy.ClientProxy;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.config.IConfigEvent.ConfigConfig;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

public class Configuration {
	public static final ModConfigSpec COMMON_SPEC;
	public static final Common COMMON;
	static {
		final Pair<Common, ModConfigSpec> specPair = new ModConfigSpec.Builder().configure(Common::new);
		COMMON_SPEC = specPair.getRight();
		COMMON = specPair.getLeft();
	}
	public static class Common {
		public final ModConfigSpec.IntValue mainmode;
		public final ModConfigSpec.BooleanValue discord;
		public final ModConfigSpec.IntValue majorver;
		public final ModConfigSpec.IntValue minorver;
		public final ModConfigSpec.IntValue minorrevver;
		//public final ForgeConfigSpec.IntValue tickSpeed;
		
		public Common(ModConfigSpec.Builder BUILDER) {
			BUILDER.push("packmode");
			mainmode = BUILDER.comment("ATM = 0 SLOP = 1 SKY = 2 MAGIC = 3 EXPERT = 4 GRAVITAS = 5").defineInRange("enable",
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
	public static void loadConfig(ModConfigSpec spec, Path path) {
        final CommentedFileConfig configData =
                CommentedFileConfig.builder(path)
                        .sync()
                        .autosave()
                        .writingMode(WritingMode.REPLACE)
                        .build();

        configData.load();
        spec.setConfig(configData);
    }

	public static void bakeConfigs() {

		
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
		int GRAV = Configuration.COMMON.mainmode.get();
		if (GRAV == 5) {
			AllTheTweaks.IPCC = cfgGrav.IPCC;
			AllTheTweaks.ATM = cfgGrav.ATM;
			AllTheTweaks.DISPLAY = cfgGrav.DISPLAY;
			AllTheTweaks.BACKGROUND = cfgGrav.BACKGROUND;
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
