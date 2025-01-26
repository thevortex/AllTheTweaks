package com.thevortex.allthetweaks.mixin;

import javax.annotation.Nullable;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.core.Direction;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.wrapper.SidedInvWrapper;

@Mixin(value = SidedInvWrapper.class, remap = false)
public class SlideInWrapper {
    @Final
    @Shadow(remap = false)
    protected final WorldlyContainer inv;

    @Nullable
    @Shadow(remap = false)
    private Direction side;

    public SlideInWrapper(WorldlyContainer inv) {
        this.inv = inv;
    }

    @Overwrite(remap = false)
    public static int getSlot(WorldlyContainer inv, int slot, @Nullable Direction side) {
        if (side == null) {
            return slot;
        }
        return inv.getSlotsForFace(side)[slot];
    }

   @Overwrite(remap = false)
   public int getSlots() {
        if (side == null) {
            return inv.getContainerSize();
        }
        return inv.getSlotsForFace(side).length;
    }

    @Overwrite(remap = false)
    public ItemStack extractItem(int slot, int amount, boolean simulate) {
        if (amount == 0)
            return ItemStack.EMPTY;

        int slot1 = getSlot(inv, slot, side);

        if (slot1 == -1)
            return ItemStack.EMPTY;

        ItemStack stackInSlot = inv.getItem(slot1);

        if (stackInSlot.isEmpty())
            return ItemStack.EMPTY;

        if (side != null && !inv.canTakeItemThroughFace(slot1, stackInSlot, side))
            return ItemStack.EMPTY;

        if (simulate) {
            if (stackInSlot.getCount() < amount) {
                return stackInSlot.copy();
            } else {
                ItemStack copy = stackInSlot.copy();
                copy.setCount(amount);
                return copy;
            }
        } else {
            int m = Math.min(stackInSlot.getCount(), amount);
            ItemStack ret = inv.removeItem(slot1, m);
            inv.setChanged();
            return ret;
        }
    }
}
