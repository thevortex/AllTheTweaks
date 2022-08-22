package com.thevortex.allthetweaks.events;

import com.thevortex.allthetweaks.AllTheTweaks;
import com.thevortex.allthetweaks.config.Configuration;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.concurrent.ThreadLocalRandom;

@Mod.EventBusSubscriber(modid = AllTheTweaks.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ServerEvents {
    private ServerEvents() {
        // nothing to do
    }

    @SubscribeEvent
    public static void addFeatherShedding(LivingEvent.LivingUpdateEvent event) {
        if (!event.getEntity().getLevel().isClientSide
                && Boolean.TRUE.equals(Configuration.COMMON.chickensShedFeathers.get())
                && event.getEntity() instanceof Chicken chicken
                && chicken.isAlive()
                && !chicken.isBaby()
                && !chicken.isChickenJockey()
                && (ThreadLocalRandom.current().nextInt(20000) == 0)
        ) {
            chicken.spawnAtLocation(Items.FEATHER);
        }
    }
}
