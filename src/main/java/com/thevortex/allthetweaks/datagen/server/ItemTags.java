package com.thevortex.allthetweaks.datagen.server;


import com.thevortex.allthetweaks.blocks.TweakBlocks;
import com.thevortex.allthetweaks.config.Reference;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ItemTags extends ItemTagsProvider {
    public ItemTags(DataGenerator generator, BlockTagsProvider blockTagsProvider, ExistingFileHelper existingFileHelper) {
        super(generator, blockTagsProvider, Reference.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags() {
            tag(Reference.ENDERPEARL_BLOCK_ITEM).add(TweakBlocks.ENDERPEARL_BLOCK_ITEM.get());
            tag(Reference.NETHERSTAR_BLOCK_ITEM).add(TweakBlocks.NETHERSTAR_BLOCK_ITEM.get());
            tag(Reference.ATMSTAR_BLOCK_ITEM).add(TweakBlocks.ATMSTAR_BLOCK_ITEM.get());
            tag(Reference.ATMSTAR).add(TweakBlocks.ATMSTAR.get());
    }
}
