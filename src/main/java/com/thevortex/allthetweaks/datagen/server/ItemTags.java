package com.thevortex.allthetweaks.datagen.server;


import com.thevortex.allthetweaks.blocks.TweakBlocks;
import com.thevortex.allthetweaks.config.Reference;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class ItemTags extends ItemTagsProvider {
    public ItemTags(PackOutput packOutPut, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagsProvider.TagLookup<Block>> tagLookup, ExistingFileHelper existingFileHelper) {
        super(packOutPut, lookupProvider,tagLookup, Reference.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
            tag(Reference.ENDERPEARL_BLOCK_ITEM).add(TweakBlocks.ENDERPEARL_BLOCK_ITEM.get());
            tag(Reference.NETHERSTAR_BLOCK_ITEM).add(TweakBlocks.NETHERSTAR_BLOCK_ITEM.get());
            tag(Reference.ATMSTAR_BLOCK_ITEM).add(TweakBlocks.ATMSTAR_BLOCK_ITEM.get());
            tag(Reference.ATMSTAR).add(TweakBlocks.ATMSTAR.get());
    }
}
