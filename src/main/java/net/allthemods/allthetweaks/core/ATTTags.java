package net.allthemods.allthetweaks.core;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import net.allthemods.allthetweaks.api.ATT;

public class ATTTags {
    
    public static final class Blocks {
        
        public static final TagKey<Block> STORAGE_BLOCKS_ENDER_PEARL = Blocks.neo("storage_blocks/ender_pearl");
        public static final TagKey<Block> STORAGE_BLOCKS_NETHER_STAR = Blocks.neo("storage_blocks/nether_star");
        public static final TagKey<Block> STORAGE_BLOCKS_ATM_STAR = Blocks.neo("storage_blocks/atm_star");
        public static final TagKey<Block> STORAGE_BLOCKS_GREG_STAR = Blocks.neo("storage_blocks/greg_star");
        
        private static TagKey<Block> create(String path) {
            return BlockTags.create(ATT.id(path));
        }
        
        private static TagKey<Block> neo(String path) {
            return BlockTags.create(ATT.c(path));
        }
    }
    
    public static final class Items {
        
        public static final TagKey<Item> STORAGE_BLOCKS = Items.neo("storage_blocks");
        public static final TagKey<Item> STORAGE_BLOCKS_ENDER_PEARL = Items.neo("storage_blocks/ender_pearl");
        public static final TagKey<Item> STORAGE_BLOCKS_NETHER_STAR = Items.neo("storage_blocks/nether_star");
        public static final TagKey<Item> STORAGE_BLOCKS_ATM_STAR = Items.neo("storage_blocks/atm_star");
        public static final TagKey<Item> STORAGE_BLOCKS_GREG_STAR = Items.neo("storage_blocks/greg_star");
        
        public static final TagKey<Item> ATM_STAR = Items.create("atm_star");
        public static final TagKey<Item> GREG_STAR = Items.create("greg_star");
        
        private static TagKey<Item> create(String path) {
            return ItemTags.create(ATT.id(path));
        }
        
        private static TagKey<Item> neo(String path) {
            return ItemTags.create(ATT.c(path));
        }
    }
}
