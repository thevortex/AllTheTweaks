package com.thevortex.allthetweaks.mixin;

import com.thevortex.allthetweaks.config.Reference;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.Tags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import wayoftime.bloodmagic.common.block.BlockTau;

@Mixin(value = BlockTau.class, remap = true)
public class TauNow {

    /**
     * @author thevortex
     * @reason clarity
     */
    @Overwrite
    protected boolean mayPlaceOn(BlockState state, BlockGetter worldIn, BlockPos pos) {
        return state.is(Reference.FARMLAND);
    }
}
