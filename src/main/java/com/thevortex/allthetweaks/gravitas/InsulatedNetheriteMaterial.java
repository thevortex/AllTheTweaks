package com.thevortex.allthetweaks.gravitas;

import com.lumintorious.tfcambiental.item.TemperatureAlteringMaterial;
import com.lumintorious.tfcambiental.modifier.TempModifier;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.mrscauthd.beyond_earth.armormaterials.NetheriteSpaceSuitMaterial;
import net.mrscauthd.beyond_earth.armormaterials.SpaceSuitMaterial;

public class InsulatedNetheriteMaterial implements ArmorMaterial, TemperatureAlteringMaterial {
    public static ArmorMaterial ArmorMaterial = NetheriteSpaceSuitMaterial.ARMOR_MATERIAL;


    @Override
    public TempModifier getTempModifier(ItemStack itemStack) {
        return new TempModifier(itemStack.getItem().getRegistryName().toString(), 0f, 1f);
    }

    @Override
    public int getDurabilityForSlot(EquipmentSlot pSlot) {
        return ArmorMaterial.getDurabilityForSlot(pSlot);
    }

    @Override
    public int getDefenseForSlot(EquipmentSlot pSlot) {
        return ArmorMaterial.getDefenseForSlot(pSlot);
    }

    @Override
    public int getEnchantmentValue() {
        return ArmorMaterial.getEnchantmentValue();
    }

    @Override
    public SoundEvent getEquipSound() {
        return ArmorMaterial.getEquipSound();
    }

    @Override
    public Ingredient getRepairIngredient() {
        return ArmorMaterial.getRepairIngredient();
    }

    @Override
    public String getName() {
        return ArmorMaterial.getName();
    }

    @Override
    public float getToughness() {
        return ArmorMaterial.getToughness();
    }

    @Override
    public float getKnockbackResistance() {
        return ArmorMaterial.getKnockbackResistance();
    }
}
