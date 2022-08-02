package com.thevortex.allthetweaks.events;

import com.thevortex.allthetweaks.AllTheTweaks;
import com.thevortex.allthetweaks.proxy.MyCons;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;


@Mod.EventBusSubscriber(modid = AllTheTweaks.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Events {
    @SubscribeEvent
    public static void setup(FMLClientSetupEvent event) {
        MyCons.setWindowIcon();
}

}
