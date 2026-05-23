package com.thevortex.allthetweaks.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Pseudo
@Mixin(targets = "blusunrize.immersiveengineering.common.util.SpawnInterdictionHandler", remap = false)
public abstract class MixinSpawnInterdictionHandler
{
    @Inject(method = "checkForLeadedBlocks", at = @At("HEAD"), cancellable = true)
    private static void allthetweaks$disableLeadedBlockCheck(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(false);
    }
}