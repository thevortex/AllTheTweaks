package com.thevortex.allthetweaks.mixin;

import net.minecraft.world.level.levelgen.feature.VillageFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(VillageFeature.class)
public class HouseRules {
    @Inject(at = @At("HEAD"), method = "Lnet/minecraft/world/level/levelgen/feature/VillageFeature;<init>(Lcom/mojang/serialization/Codec;)V")
    private void falseSky(CallbackInfo info) {

    }
}
