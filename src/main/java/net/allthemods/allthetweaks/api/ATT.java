package net.allthemods.allthetweaks.api;

import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;

public class ATT {
    
    public static final String MOD_ID = "allthetweaks";
    
    public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(ATT.MOD_ID, path);
    }
    
    public static Identifier c(String path) {
        return Identifier.fromNamespaceAndPath("c", path);
    }
    
    public static <T> ResourceKey<T> key(ResourceKey<? extends Registry<T>> registryKey, String path) {
        return ResourceKey.create(registryKey, ATT.id(path));
    }
    
}
