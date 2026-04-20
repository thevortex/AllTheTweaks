package net.allthemods.allthetweaks.data.provider.tags;

import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;

import net.allthemods.allthetweaks.api.ATT;
import net.allthemods.allthetweaks.core.ATTRegistry;
import net.allthemods.allthetweaks.core.ATTTags;

import java.util.concurrent.CompletableFuture;

public class ATTBlockTagsProvider extends BlockTagsProvider {
    
    public ATTBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, ATT.MOD_ID);
    }
    
    @Override
    protected void addTags(HolderLookup.Provider registries) {
        this.tag(ATTTags.Blocks.STORAGE_BLOCKS_ENDER_PEARL).add(ATTRegistry.ENDERPEARL_BLOCK.get());
        this.tag(ATTTags.Blocks.STORAGE_BLOCKS_NETHER_STAR).add(ATTRegistry.NETHERSTAR_BLOCK.get());
        this.tag(ATTTags.Blocks.STORAGE_BLOCKS_ATM_STAR).add(ATTRegistry.ATMSTAR_BLOCK.get());
        this.tag(ATTTags.Blocks.STORAGE_BLOCKS_GREG_STAR).add(ATTRegistry.GREGSTAR_BLOCK.get());
        
        this.tag(Tags.Blocks.STORAGE_BLOCKS)
                .addTag(ATTTags.Blocks.STORAGE_BLOCKS_ENDER_PEARL)
                .addTag(ATTTags.Blocks.STORAGE_BLOCKS_NETHER_STAR)
                .addTag(ATTTags.Blocks.STORAGE_BLOCKS_ATM_STAR)
                .addTag(ATTTags.Blocks.STORAGE_BLOCKS_GREG_STAR);
    }
}
