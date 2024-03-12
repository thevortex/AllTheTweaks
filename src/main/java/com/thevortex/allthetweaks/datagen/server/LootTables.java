package com.thevortex.allthetweaks.datagen.server;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import com.thevortex.allthetweaks.blocks.TweakBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.loot.packs.VanillaBlockLoot;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class LootTables extends VanillaBlockLoot
{
    @Override
    public void generate() {
        getKnownBlocks().forEach(this::dropSelf);
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return TweakBlocks.BLOCKS.getEntries()
                .stream().map(RegistryObject::get)
                .filter(block -> !(block instanceof LiquidBlock))
                .collect(Collectors.toList());
    }
}