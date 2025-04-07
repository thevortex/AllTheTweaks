package com.thevortex.allthetweaks.mixin;

import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import net.minecraft.world.item.CreativeModeTab;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CreativeModeInventoryScreen.class)
public class CreativeModeInventoryScreenMixin {
    @Shadow
    private EditBox searchBox;

    @Inject(method = "selectTab", at = @At("TAIL"))
    private void selectTabOverwrite(CreativeModeTab tab, CallbackInfo ci) {
        searchBox.setCanLoseFocus(true);
        searchBox.setFocused(false);
    }
}
