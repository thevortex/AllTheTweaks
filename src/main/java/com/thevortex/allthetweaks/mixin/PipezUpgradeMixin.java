package com.thevortex.allthetweaks.mixin;

import com.thevortex.allthetweaks.AllTheTweaks;
import de.maxhenkel.pipez.Upgrade;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import java.lang.reflect.Field;

@Mixin(Upgrade.class)
public class PipezUpgradeMixin {

    static {
        AllTheTweaks.setRunnableCallback(PipezUpgradeMixin::allTheTweaks$overrideEnumEntries);
    }

    @Unique
    private static void allTheTweaks$overrideEnumEntries() {
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
