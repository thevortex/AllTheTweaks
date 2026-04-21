package net.allthemods.allthetweaks.data.provider.tags;

import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ItemTagsProvider;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;

import net.allthemods.allthetweaks.api.ATT;
import net.allthemods.allthetweaks.core.ATTRegistry;
import net.allthemods.allthetweaks.core.ATTTags;

import java.util.concurrent.CompletableFuture;

public class ATTItemTagsProvider extends ItemTagsProvider {
    
    public ATTItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, ATT.MOD_ID);
    }
    
    @Override
    protected void addTags(HolderLookup.Provider registries) {
        this.tag(ATTTags.Items.STORAGE_BLOCKS_ENDER_PEARL).add(ATTRegistry.ENDERPEARL_BLOCK.get().asItem());
        this.tag(ATTTags.Items.STORAGE_BLOCKS_NETHER_STAR).add(ATTRegistry.NETHERSTAR_BLOCK.get().asItem());
        this.tag(ATTTags.Items.STORAGE_BLOCKS_ATM_STAR).add(ATTRegistry.ATMSTAR_BLOCK.get().asItem());
        this.tag(ATTTags.Items.STORAGE_BLOCKS_GREG_STAR).add(ATTRegistry.GREGSTAR_BLOCK.get().asItem());
        
        this.tag(Tags.Items.STORAGE_BLOCKS)
                .addTag(ATTTags.Items.STORAGE_BLOCKS_ENDER_PEARL)
                .addTag(ATTTags.Items.STORAGE_BLOCKS_NETHER_STAR)
                .addTag(ATTTags.Items.STORAGE_BLOCKS_ATM_STAR)
                .addTag(ATTTags.Items.STORAGE_BLOCKS_GREG_STAR);
        
        this.tag(ATTTags.Items.ATM_STAR).add(ATTRegistry.ATMSTAR.get());
        this.tag(ATTTags.Items.GREG_STAR).add(ATTRegistry.GREGSTAR.get());
    }
}
