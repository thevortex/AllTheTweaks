package com.thevortex.allthetweaks.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import tv.soaryn.xycraft.machines.client.EnergyTooltipHelper;

import java.util.ArrayList;
import java.util.List;

@Mixin(EnergyTooltipHelper.class)
public class EnergyTooltipHelperMixin {
    @Redirect(method = "renderTooltip", at = @At(value = "INVOKE", target = "Ljava/util/List;add(Ljava/lang/Object;)Z", ordinal = 0))
    private static boolean skipIfNull(List instance, Object e){
        if (e == null) return false;
        return instance.add(e);
    }

    @Redirect(method = "renderTooltip", at = @At(value = "INVOKE", target = "Ljava/util/ArrayList;removeFirst()Ljava/lang/Object;"))
    private static Object NullIfEmpty(ArrayList instance){
        if (instance.isEmpty()) return null;
        return instance.removeFirst();
    }
}