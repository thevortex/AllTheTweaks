package com.thevortex.allthetweaks.proxy;

import net.neoforged.fml.ModList;
import org.embeddedt.modernfix.core.ModernFixMixinPlugin;

public class MFProxy {
    public static boolean brandingEnabled() {

        return ModernFixMixinPlugin.instance.isOptionEnabled("feature.branding.BrandingControlMixin");
    }
}
