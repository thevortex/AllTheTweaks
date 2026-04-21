package net.allthemods.allthetweaks.core.mixin;

import net.minecraft.client.Minecraft;

import net.allthemods.allthetweaks.ATTConfig;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Minecraft.class)
public class MinecraftMixin {
    
    @Inject(
            method = "createTitle",
            at = @At("RETURN"),
            cancellable = true
    )
    private void createTitle(CallbackInfoReturnable<String> cir) {
        cir.setReturnValue(ATTConfig.PACK_MODE.get().getWindowTitle());
    }
}
