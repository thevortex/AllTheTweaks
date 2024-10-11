package com.thevortex.allthetweaks.mixin_squared.canceller;

import com.bawnorton.mixinsquared.api.MixinCanceller;
import com.thevortex.allthetweaks.AllTheTweaks;

import java.util.List;
import java.util.Set;

public class ATTMixinCanceller implements MixinCanceller {
    private static final Set<String> mixinsToCancel = Set.of(
        "com.aizistral.nochatreports.neoforge.mixins.client.MixinServerStatusPinger$1",
        "com.aizistral.nochatreports.common.mixins.client.MixinServerStatusPinger$1"
    );

    @Override
    public boolean shouldCancel(List<String> targetClassNames, String mixinClassName) {
        return mixinsToCancel.contains(mixinClassName);
    }
}
