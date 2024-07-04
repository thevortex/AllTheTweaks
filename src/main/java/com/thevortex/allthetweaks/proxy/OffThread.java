package com.thevortex.allthetweaks.proxy;

import com.thevortex.allthetweaks.DRP;
import com.thevortex.allthetweaks.UpdateDRP;

import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.common.NeoForge;

@OnlyIn(net.neoforged.api.distmarker.Dist.CLIENT)
public class OffThread {
@OnlyIn(net.neoforged.api.distmarker.Dist.CLIENT)
    public static void startDRP() {
            MyCons.setWindowIcon();
            
            DRP.start();        
           
        }
}
