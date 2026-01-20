package com.thevortex.allthetweaks.proxy;

import dev.wuffs.bcc.Config;

import static com.thevortex.allthetweaks.AllTheTweaks.BCC;

public class BCCProxy {


    public static String getVersion() {
        if (BCC) {
            return Config.modpackVersion.get();
        } else {
            return "N/A";
        }
    }
}
