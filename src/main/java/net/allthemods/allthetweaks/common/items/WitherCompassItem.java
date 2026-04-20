package net.allthemods.allthetweaks.common.items;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import org.jspecify.annotations.Nullable;

public class WitherCompassItem extends Item {
    
    public WitherCompassItem(Properties properties) {
        super(properties);
    }
    
    @Override
    public void inventoryTick(ItemStack stack, ServerLevel level, Entity owner, @Nullable EquipmentSlot slot) {
        if (slot == EquipmentSlot.MAINHAND && owner instanceof LivingEntity entity) {
            entity.addEffect(new MobEffectInstance(MobEffects.WITHER, 200));
        }
    }
}
