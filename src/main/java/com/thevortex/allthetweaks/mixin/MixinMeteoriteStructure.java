package com.thevortex.allthetweaks.mixin;

import appeng.worldgen.meteorite.CraterType;
import appeng.worldgen.meteorite.MeteoriteStructure;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Pseudo
@Mixin(value = MeteoriteStructure.class)
public class MixinMeteoriteStructure
{
    @Redirect(at = @At(value = "INVOKE", target = "Lappeng/worldgen/meteorite/MeteoriteStructure;determineCraterType(Lnet/minecraft/core/BlockPos;Lnet/minecraft/core/Holder;Lnet/minecraft/world/level/levelgen/WorldgenRandom;)Lappeng/worldgen/meteorite/CraterType;"), method = {"generatePieces"})
    private static CraterType forceCraterType(BlockPos pos, Holder<Biome> biome, WorldgenRandom lava) {
        return CraterType.NONE;
    }
}
