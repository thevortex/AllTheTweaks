package com.thevortex.allthetweaks.mixin;

import net.dries007.tfc.common.items.TorchItem;
import net.dries007.tfc.util.events.StartFireEvent;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.StandingAndWallBlockItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.mrscauthd.beyond_earth.events.Methods;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = TorchItem.class, remap = false)
public class TFCTorch extends StandingAndWallBlockItem {


    public TFCTorch(Block pStandingBlock, Block pWallBlock, Properties pProperties) {
        super(pStandingBlock, pWallBlock, pProperties);
    }

    /**
     * @author thevortex
     * @reason someone said i had to
     */
    @Overwrite
    public InteractionResult useOn(UseOnContext context) {
        final Level level = context.getLevel();
        final BlockPos pos = context.getClickedPos();
        if (Methods.isSpaceWorldWithoutOxygen(level)) {
            return InteractionResult.FAIL;
        }
        if (StartFireEvent.startFire(level, pos, level.getBlockState(pos), context.getClickedFace(), context.getPlayer(), context.getItemInHand(), StartFireEvent.FireResult.NEVER, StartFireEvent.FireStrength.WEAK)) {
            return InteractionResult.SUCCESS;
        }
        return super.useOn(context);
    }
}
