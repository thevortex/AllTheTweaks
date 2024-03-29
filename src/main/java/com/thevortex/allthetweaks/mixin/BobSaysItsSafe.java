package com.thevortex.allthetweaks.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.screens.worldselection.WorldSelectionList;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.util.FormattedCharSequence;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.List;

@Mixin(value = WorldSelectionList.WorldListEntry.class, remap = false)
public class BobSaysItsSafe {
    @Overwrite
    private void renderExperimentalWarning(PoseStack stack, int mouseX, int mouseY, int top, int left) {

    }
}
