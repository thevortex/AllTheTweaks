package com.thevortex.allthetweaks.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import ovh.corail.tombstone.helper.TimeHelper;

@Mixin(TimeHelper.class)
public class TimeHelperMixin {

    @Inject(method = "init", at = @At("HEAD"), cancellable = true)
    private static void init(CallbackInfo ci) {
        ci.cancel();
    }

    @Inject(method = "setSpecialEvent", at = @At("HEAD"), cancellable = true)
    private static void setSpecialEvent(CallbackInfo ci) {
        ci.cancel();
    }

    @Inject(method = "getSpecialEvent", at = @At("HEAD"), cancellable = true)
    private static void getSpecialEvent(CallbackInfoReturnable<TimeHelper.SpecialEvent> cir) {
        cir.setReturnValue(TimeHelper.SpecialEvent.NONE);
    }

    @Inject(method = {"isAprilFoolsDay"}, at = {@At("HEAD")}, cancellable = true)
    private static void isAprilFoolsDay(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(false);
    }

    @Inject(method = {"isDateAroundHalloween"}, at = {@At("HEAD")}, cancellable = true)
    private static void isDateAroundHalloween(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(false);
    }

    @Inject(method = {"isDateAroundChristmas"}, at = {@At("HEAD")}, cancellable = true)
    private static void iIsDateAroundChristmas(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(false);
    }

    @Inject(method = {"isDateAroundEaster"}, at = {@At("HEAD")}, cancellable = true)
    private static void isDateAroundEaster(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(false);
    }
}
