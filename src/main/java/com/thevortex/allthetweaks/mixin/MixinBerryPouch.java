package com.thevortex.allthetweaks.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.github.flandre923.berrypouch.ModClientCommon;
import com.github.flandre923.berrypouch.neoforge.client.NeoForgeClient;

import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

@Pseudo
@Mixin(NeoForgeClient.class)
public class MixinBerryPouch {

    @Inject(method = "onClientSetup", at = @org.spongepowered.asm.mixin.injection.At("HEAD"), cancellable = true)
    private static void onClientSetup(final FMLClientSetupEvent event, final CallbackInfo ci) {
       event.enqueueWork(() -> {
        ModClientCommon.init();
       });
        ci.cancel();
    }
}
