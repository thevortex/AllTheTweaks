package com.thevortex.allthetweaks.mixin;

import java.util.function.Supplier;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.thevortex.allthetweaks.AllTheTweaks;

import org.spongepowered.asm.mixin.injection.At;

import elucent.eidolon.network.TESyncPacket;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;

@Mixin(TESyncPacket.class)
public class TESyncFix {

    @Inject(method = "consume", at = @At("HEAD"), cancellable = true, remap = false)
    private static void consume2(TESyncPacket packet, Supplier<NetworkEvent.Context> ctx, CallbackInfo info) {
        AllTheTweaks.LOGGER.info("TESyncPacket consume2: " + ctx.get().getSender());
        if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER) {
            info.cancel();
        }
    }

}
