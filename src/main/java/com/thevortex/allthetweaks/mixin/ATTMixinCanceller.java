package com.thevortex.allthetweaks.mixin;
import com.bawnorton.mixinsquared.api.MixinCanceller;

import java.util.List;
import java.util.Set;

public class ATTMixinCanceller implements MixinCanceller {
    private static final Set<String> mixinsToCancel = Set.of(

            "org.embeddedt.modernfix.forge.mixin.feature.branding.BrandingControlMixin"
    );

    @Override
    public boolean shouldCancel(List<String> targetClassNames, String mixinClassName) {
        return mixinsToCancel.contains(mixinClassName);
    }
}