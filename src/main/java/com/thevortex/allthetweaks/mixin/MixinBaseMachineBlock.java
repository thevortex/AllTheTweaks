package com.thevortex.allthetweaks.mixin;

import com.direwolf20.justdirethings.common.blocks.baseblocks.BaseMachineBlock;
import net.minecraft.nbt.CompoundTag;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(value = BaseMachineBlock.class, remap = false)
public abstract class MixinBaseMachineBlock
{
    @ModifyVariable(
            method = "getDrops",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/component/CustomData;of(Lnet/minecraft/nbt/CompoundTag;)Lnet/minecraft/world/item/component/CustomData;",
                    remap = true
            ),
            name = "compoundTag"
    )
    private CompoundTag allthemons$modifyCompoundTag(CompoundTag compoundTag) {
        if (compoundTag.contains("neoforge:attachments")) {
            CompoundTag attachments = compoundTag.getCompound("neoforge:attachments");
            attachments.remove("justdirethings:machine_handler");
            compoundTag.put("neoforge:attachments", attachments);
        }
        return compoundTag;
    }
}