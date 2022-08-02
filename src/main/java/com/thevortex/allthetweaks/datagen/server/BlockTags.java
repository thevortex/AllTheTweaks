package com.thevortex.allthetweaks.datagen.server;

import com.thevortex.allthetweaks.blocks.TweakBlocks;
import com.thevortex.allthetweaks.config.Reference;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import javax.annotation.Nullable;

public class BlockTags extends BlockTagsProvider {

    public BlockTags(DataGenerator generator, @Nullable ExistingFileHelper existingFileHelper) {
        super(generator, Reference.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        tag(Reference.ENDERPEARL_BLOCK).add(TweakBlocks.ENDERPEARL_BLOCK.get());
        tag(Reference.NETHERSTAR_BLOCK).add(TweakBlocks.NETHERSTAR_BLOCK.get());
        tag(Reference.ATMSTAR_BLOCK).add(TweakBlocks.ATMSTAR_BLOCK.get());

       }
}
