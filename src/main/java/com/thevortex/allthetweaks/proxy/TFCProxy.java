package com.thevortex.allthetweaks.proxy;

import com.thevortex.allthetweaks.AllTheTweaks;
import net.dries007.tfc.common.entities.livestock.TFCAnimal;
import net.dries007.tfc.common.entities.predator.Predator;
import net.dries007.tfc.common.entities.prey.Prey;
import net.minecraft.world.entity.Entity;

public class TFCProxy {

    public static boolean isTFCAnimal(Entity entity) {
        return ((entity instanceof TFCAnimal) || (entity instanceof Prey));
    }
}
