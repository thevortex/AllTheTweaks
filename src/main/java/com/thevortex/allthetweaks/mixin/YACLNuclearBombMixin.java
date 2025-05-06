package com.thevortex.allthetweaks.mixin;

import net.minecraft.server.packs.resources.ResourceManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@Pseudo
@Mixin(targets = "dev.isxander.yacl3.gui.image.YACLImageReloadListener", remap = false)
public class YACLNuclearBombMixin {

    @Overwrite
    private CompletableFuture<List<Optional<Void>>> prepare(
            ResourceManager manager,
            Executor executor) {
        return CompletableFuture.completedFuture(List.of());
    }
}
