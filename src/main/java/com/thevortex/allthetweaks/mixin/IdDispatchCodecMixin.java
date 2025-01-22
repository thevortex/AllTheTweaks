package com.thevortex.allthetweaks.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Cancellable;
import com.thevortex.allthetweaks.AllTheTweaks;
import net.minecraft.network.codec.IdDispatchCodec;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(IdDispatchCodec.class)
public class IdDispatchCodecMixin {
    @ModifyExpressionValue(method = "encode(Lio/netty/buffer/ByteBuf;Ljava/lang/Object;)V", at = @At(value = "INVOKE", target = "Lit/unimi/dsi/fastutil/objects/Object2IntMap;getOrDefault(Ljava/lang/Object;I)I"))
    private int cancelIfMinusOne(int id, @Cancellable CallbackInfo ci){
        if (id == -1) {
            ci.cancel();
            AllTheTweaks.LOGGER.debug("Supressing disconnect error message...");
        }
        return id;
    }
}
