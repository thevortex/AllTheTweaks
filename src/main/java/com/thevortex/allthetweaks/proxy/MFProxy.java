package com.thevortex.allthetweaks.proxy;


import org.embeddedt.modernfix.core.ModernFixMixinPlugin;

import static com.thevortex.allthetweaks.AllTheTweaks.mfContainer;

public class MFProxy {
    public static boolean brandingEnabled() {
        if(mfContainer.isEmpty() ){ return false; }

            return ModernFixMixinPlugin.instance.isOptionEnabled("feature.branding.BrandingControlMixin");

    }
}
