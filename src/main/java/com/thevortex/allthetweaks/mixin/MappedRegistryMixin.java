package com.thevortex.allthetweaks.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.thevortex.allthetweaks.AllTheTweaks;
import net.minecraft.core.MappedRegistry;
import net.neoforged.neoforge.registries.callback.BakeCallback;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.List;
import java.util.function.Consumer;

@Mixin(MappedRegistry.class)
public class MappedRegistryMixin<T> {
    // We catch just to make it not skip the return of
    // RegistryManager#applySnapshot which later
    // properly shows an error screen with missing entries
    @WrapOperation(method = "freeze", at = @At(value = "INVOKE", target = "Ljava/util/List;forEach(Ljava/util/function/Consumer;)V"))
    private void catchIfError(List<BakeCallback<T>> instance, Consumer<BakeCallback<T>> consumer, Operation<Void> original) {
        try {
            original.call(instance,consumer);
        } catch (NullPointerException e) {
            AllTheTweaks.LOGGER.error("Failed to bake mapped registry", e);
        }
    }
}