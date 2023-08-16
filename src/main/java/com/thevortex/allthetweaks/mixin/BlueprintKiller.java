package com.thevortex.allthetweaks.mixin;

import blusunrize.immersiveengineering.client.render.tile.BlueprintRenderer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlueprintRenderer.class)
public class BlueprintKiller {

        @Inject(method = "getBlueprintDrawable(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/level/Level;)Lblusunrize/immersiveengineering/client/render/tile/BlueprintRenderer$BlueprintLines;", at = @At("HEAD"), cancellable = true, remap = false)
        private static void killIt(ItemStack stack, Level world, CallbackInfoReturnable<BlueprintRenderer.BlueprintLines> cir) {
            cir.cancel();
        }
    }

