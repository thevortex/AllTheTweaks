package com.thevortex.allthetweaks.mixin;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import net.irisshaders.iris.gui.screen.ShaderPackScreen;

import com.mojang.blaze3d.systems.RenderSystem;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(ShaderPackScreen.class)
public abstract class ShaderPackScreenMixin extends Screen {
    
    protected ShaderPackScreenMixin(Component title) {
        super(title);
    }
    
    /**
     * @author Satherov
     * @reason Revert to default since iris override breaks it on neoforge
     */
    @Overwrite
    protected void renderBlurredBackground(float partialTick) {
        RenderSystem.disableDepthTest();
        this.minecraft.gameRenderer.processBlurEffect(partialTick);
        this.minecraft.getMainRenderTarget().bindWrite(false);
    }
}