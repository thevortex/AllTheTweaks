package net.allthemods.allthetweaks.proxy;

import static net.allthemods.allthetweaks.AllTheTweaks.BCC;
import dev.wuffs.bcc.Config;
public class BCCProxy {


    public static String getVersion() {
        if (BCC) {
            return Config.data().modpackVersion().value();
        } else {
            return "N/A";
        }
    }
}
