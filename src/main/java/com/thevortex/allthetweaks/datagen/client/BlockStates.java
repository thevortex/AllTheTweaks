package com.thevortex.allthetweaks.datagen.client;

import com.thevortex.allthetweaks.blocks.MiniPortalBlock;
import com.thevortex.allthetweaks.blocks.TweakBlocks;
import com.thevortex.allthetweaks.config.Reference;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.Block;

import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class BlockStates extends BlockStateProvider {
    public BlockStates(DataGenerator generator, ExistingFileHelper fileHelper) {
        super(generator.getPackOutput(), Reference.MOD_ID, fileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        List<DeferredHolder<Block,? extends Block>> entries = TweakBlocks.BLOCKS.getEntries()
                .stream()
                .filter(block -> !(block.get() instanceof MiniPortalBlock) && !block.get().defaultBlockState().is(TweakBlocks.ATM_TROPHY.get()))
                .toList();

        entries.forEach(block -> simpleBlockWithItem(block.get(), cubeAll(block.get())));
    }
}
