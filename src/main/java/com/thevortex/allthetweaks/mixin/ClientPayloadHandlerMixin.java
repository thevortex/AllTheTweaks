package com.thevortex.allthetweaks.mixin;

import net.neoforged.neoforge.network.handlers.ClientPayloadHandler;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.neoforge.network.payload.FrozenRegistrySyncCompletedPayload;
import net.neoforged.neoforge.registries.RegistryManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPayloadHandler.class)
public class ClientPayloadHandlerMixin {
    // we inject on all 3 IPayloadContext#disconnect
    // because the Minecraft#disconnect is too late
    // for some errors like Neoforge Issue #2806
    @Inject(method = "handle(Lnet/neoforged/neoforge/network/payload/FrozenRegistrySyncCompletedPayload;Lnet/neoforged/neoforge/network/handling/IPayloadContext;)V", at = @At(value = "INVOKE", target = "Lnet/neoforged/neoforge/network/handling/IPayloadContext;disconnect(Lnet/minecraft/network/chat/Component;)V"))
    private static void revertToFrozenIfDisconnected(FrozenRegistrySyncCompletedPayload payload, IPayloadContext context, CallbackInfo ci){
        RegistryManager.revertToFrozen();
    }
}