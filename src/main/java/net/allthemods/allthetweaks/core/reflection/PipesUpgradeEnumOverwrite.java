package net.allthemods.allthetweaks.core.reflection;

import net.allthemods.allthetweaks.AllTheTweaks;

import de.maxhenkel.pipez.Upgrade;

import java.lang.reflect.Field;

public class PipesUpgradeEnumOverwrite {
    
    public static void overrideEnumEntries() {
        for (Upgrade upgrade : Upgrade.values()) {
            try {
                Field canChangeRedstoneModeField = Upgrade.class.getDeclaredField("canChangeRedstoneMode");
                Field canChangeFilterField = Upgrade.class.getDeclaredField("canChangeFilter");
                Field canChangeDistributionModeField = Upgrade.class.getDeclaredField("canChangeDistributionMode");
                
                canChangeRedstoneModeField.setAccessible(true);
                canChangeFilterField.setAccessible(true);
                canChangeDistributionModeField.setAccessible(true);
                
                canChangeRedstoneModeField.set(upgrade, true);
                canChangeFilterField.set(upgrade, true);
                canChangeDistributionModeField.set(upgrade, true);
                
            } catch (NoSuchFieldException | IllegalAccessException e) {
                AllTheTweaks.LOGGER.error("Failed to override enum entries for pipez upgrades: ", e);
            }
        }
    }
}