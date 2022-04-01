package com.thevortex.allthetweaks.datagen.server;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import com.thevortex.allthetweaks.blocks.TweakBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.data.loot.LootTableProvider;
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

public class LootTables extends LootTableProvider {

    public LootTables(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet>> getTables()
    {
        return ImmutableList.of(Pair.of(Blocks::new, LootContextParamSets.BLOCK));
    }

    public static class Blocks extends BlockLoot
    {
        @Override
        public void addTables()
        {
            getKnownBlocks().forEach(this::dropSelf);

        }





        @Override
        protected Iterable<Block> getKnownBlocks()
        {
            return TweakBlocks.BLOCKS.getEntries()
                .stream().map(RegistryObject::get)
                .filter(block -> !(block instanceof LiquidBlock))
                .collect(Collectors.toList());

        }


    }

    @Override
    protected void validate(Map<ResourceLocation, LootTable> map, ValidationContext validationtracker)
    {
        map.forEach((name, table) -> net.minecraft.world.level.storage.loot.LootTables.validate(validationtracker, name, table));
    }

}
