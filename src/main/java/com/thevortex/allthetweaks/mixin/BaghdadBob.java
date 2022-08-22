package com.thevortex.allthetweaks.mixin;

import com.mojang.serialization.Lifecycle;
import net.minecraft.world.level.storage.PrimaryLevelData;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = Lifecycle.class, remap = false)
public class BaghdadBob {
    @Final
    @Shadow
    private static final Lifecycle STABLE = Lifecycle.stable();
    @Overwrite
    public static Lifecycle experimental() {
        return STABLE;
    }

    }

