package com.thevortex.allthetweaks.mixin;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import com.llamalad7.mixinextras.sugar.Local;
import com.simibubi.create.content.schematics.SchematicPrinter;

import net.createmod.catnip.levelWrappers.SchematicLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;

@Mixin(value = SchematicPrinter.class, remap = false)
public class SchemPrinterMixin {

    @Shadow
    private SchematicLevel blockReader;

    @ModifyArg(method = "getCurrentRequirement", at = @At(value = "INVOKE", target = "Lcom/simibubi/create/foundation/utility/BlockHelper;prepareBlockEntityData(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/block/entity/BlockEntity;)Lnet/minecraft/nbt/CompoundTag;"), index = 1)
    private BlockEntity fixBlockEntityArgument(BlockEntity original, @Local BlockPos target) {
        return blockReader.getBlockEntity(target);
    }
}
