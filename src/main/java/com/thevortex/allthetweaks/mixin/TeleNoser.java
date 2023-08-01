package com.thevortex.allthetweaks.mixin;

import net.minecraft.commands.CommandSource;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import wayoftime.bloodmagic.common.item.ITeleposerFocus;
import wayoftime.bloodmagic.common.tile.TileInventory;
import wayoftime.bloodmagic.common.tile.TileTeleposer;

@Mixin(value = TileTeleposer.class, remap = false)
public abstract class TeleNoser extends TileInventory implements MenuProvider, CommandSource {
    public TeleNoser(BlockEntityType<?> type, int size, String name, BlockPos pos, BlockState state) {
        super(type, size, name, pos, state);
    }

    @Inject(method = "initiateTeleport", at = @At(value = "HEAD"))
    private void papersPlease(CallbackInfo ci) {
        if(this.level instanceof ServerLevel){
            ServerLevel serverWorld = (ServerLevel)this.level;
            ItemStack focusStack = this.getItem(0);
            ITeleposerFocus focusItem = (ITeleposerFocus)focusStack.getItem();
            Level linkedWorld = focusItem.getStoredWorld(focusStack, this.level);
            if(!(serverWorld.dimension() == linkedWorld.dimension())) { ci.cancel(); }
        }
    }
}
