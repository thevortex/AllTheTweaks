package com.thevortex.allthetweaks.mixin;

import net.minecraft.client.gui.screens.LoadingOverlay;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(LoadingOverlay.class)
public class AlphaDog {

    @ModifyArg(method = { "render" }, at = @At(value = "INVOKE", ordinal = 0, target = "Lcom/mojang/blaze3d/systems/RenderSystem;setShaderColor(FFFF)V" ), index = 0)
    private float RedLeader(float color) {
        return 1.0F;//return 1.0F;
    }


    @ModifyArg(method = { "render" }, at = @At(value = "INVOKE", ordinal = 0, target = "Lcom/mojang/blaze3d/systems/RenderSystem;setShaderColor(FFFF)V" ), index = 1)
    private float GreenDay(float color) {
        return 1.0F;//return 0.937F;
    }


    @ModifyArg(method = { "render" }, at = @At(value = "INVOKE", ordinal = 0, target = "Lcom/mojang/blaze3d/systems/RenderSystem;setShaderColor(FFFF)V" ), index = 2)
    private float Bluedabadeedabadaa(float color) {
        return 1.0F;//return 0.055F;
    }

    @ModifyArg(method = { "render" }, at = @At(value = "INVOKE", ordinal = 0, target = "Lcom/mojang/blaze3d/systems/RenderSystem;setShaderColor(FFFF)V" ), index = 3)
    private float noAlpha(float color) {
        return 0.05F;
    }
}
