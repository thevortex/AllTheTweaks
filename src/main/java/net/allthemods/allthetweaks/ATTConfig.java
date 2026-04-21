package net.allthemods.allthetweaks;

import net.neoforged.fml.ModContainer;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.ModConfigSpec;

import net.allthemods.allthetweaks.client.discord.PackMode;

public final class ATTConfig {
    
    private static final ModConfigSpec.Builder CLIENT_BUILDER = new ModConfigSpec.Builder();
    
    public static final ModConfigSpec.BooleanValue RPC_ENABLED = ATTConfig.CLIENT_BUILDER
            .comment("Enable Discord Rich Presence")
            .define("rpc_enabled", true);
    
    public static final ModConfigSpec.EnumValue<PackMode> PACK_MODE = ATTConfig.CLIENT_BUILDER.defineEnum("pack_mode", PackMode.ATM11);
    
    public static final ModConfigSpec CLIENT = ATTConfig.CLIENT_BUILDER.build();
    
    private ATTConfig() { }
    
    public static void register(ModContainer container) {
        container.registerConfig(ModConfig.Type.CLIENT, ATTConfig.CLIENT);
    }
}