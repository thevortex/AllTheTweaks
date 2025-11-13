package com.thevortex.allthetweaks.proxy;

import dev.wuffs.bcc.Config;

public class BCCProxy {


    public static String getVersion() {
        return Config.modpackVersion.get();
    }
}
