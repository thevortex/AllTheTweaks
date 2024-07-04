package com.thevortex.allthetweaks.blocks;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class Shiny extends Item {

    public Shiny(Item.Properties properties)
    {
        super(properties);
    }

    @Override
    public boolean isFoil(ItemStack stack)
    {
        return true;
    }
}
