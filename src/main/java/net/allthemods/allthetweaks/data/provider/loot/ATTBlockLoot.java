package net.allthemods.allthetweaks.data.provider.loot;

import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;

import net.allthemods.allthetweaks.core.ATTRegistry;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;

public class ATTBlockLoot extends BlockLootSubProvider {
    private final Map<ResourceKey<LootTable>, LootTable.Builder> tables = new HashMap<>();
    
    protected ATTBlockLoot(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.DEFAULT_FLAGS, registries);
    }
    
    @Override
    public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> output) {
        this.generate();
        this.tables.forEach(output);
    }
    
    @Override
    protected void generate() {
        ATTRegistry.BLOCKS.getEntries().forEach(this::dropSelf);
    }
    
    private void dropSelf(DeferredHolder<Block, ? extends Block> block) {
        this.dropSelf(block.get());
    }
    
    @Override
    protected void add(Block block, LootTable.Builder builder) {
        block.getLootTable().ifPresent(key -> this.tables.put(key, builder));
    }
}
