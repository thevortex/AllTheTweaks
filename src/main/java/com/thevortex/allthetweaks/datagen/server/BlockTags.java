package com.thevortex.allthetweaks.datagen.server;

import com.thevortex.allthetweaks.blocks.TweakBlocks;
import com.thevortex.allthetweaks.config.Reference;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class BlockTags extends BlockTagsProvider
{
    public BlockTags(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(packOutput, lookupProvider, Reference.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(Reference.ENDERPEARL_BLOCK).add(TweakBlocks.ENDERPEARL_BLOCK.get());
        tag(Reference.NETHERSTAR_BLOCK).add(TweakBlocks.NETHERSTAR_BLOCK.get());
        tag(Reference.ATMSTAR_BLOCK).add(TweakBlocks.ATMSTAR_BLOCK.get());
        tag(Reference.GREGSTAR_BLOCK).add(TweakBlocks.GREGSTAR_BLOCK.get());
    }
}
