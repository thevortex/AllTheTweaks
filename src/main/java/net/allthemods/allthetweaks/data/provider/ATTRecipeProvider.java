package net.allthemods.allthetweaks.data.provider;

import net.neoforged.neoforge.common.Tags;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;

import net.allthemods.allthetweaks.api.ATT;
import net.allthemods.allthetweaks.core.ATTRegistry;
import net.allthemods.allthetweaks.core.ATTTags;

import java.util.concurrent.CompletableFuture;

public class ATTRecipeProvider extends RecipeProvider {
    private final HolderGetter<Item> items;
    private final RecipeOutput output;
    
    public ATTRecipeProvider(HolderLookup.Provider lookupProvider, RecipeOutput output) {
        super(lookupProvider, output);
        this.items = lookupProvider.lookupOrThrow(Registries.ITEM);
        this.output = output;
    }
    
    @Override
    protected void buildRecipes() {
        this.compress();
        this.decompress();
    }
    
    private void compress() {
        this.compression(ATTRegistry.ENDERPEARL_BLOCK.get(), Tags.Items.ENDER_PEARLS, "ender_pearl");
        this.compression(ATTRegistry.NETHERSTAR_BLOCK.get(), Tags.Items.NETHER_STARS, "nether_star");
        this.compression(ATTRegistry.ATMSTAR_BLOCK.get(), ATTTags.Items.ATM_STAR, "atm_star");
        this.compression(ATTRegistry.GREGSTAR_BLOCK.get(), ATTTags.Items.GREG_STAR, "greg_star");
    }
    
    private void compression(Block output, TagKey<Item> input, String unlockName) {
        ShapedRecipeBuilder.shaped(this.items, RecipeCategory.MISC, output)
                .group(ATT.MOD_ID)
                .pattern("aaa")
                .pattern("aaa")
                .pattern("aaa")
                .define('a', input)
                .unlockedBy("has_" + unlockName, this.has(input))
                .save(this.output);
    }
    
    private void decompress() {
        this.decompression(ATTRegistry.ATMSTAR.get(), 9, ATTTags.Items.STORAGE_BLOCKS_ATM_STAR, "atm_star_block", "atm_star_from_atmstar_block");
        this.decompression(ATTRegistry.GREGSTAR.get(), 9, ATTTags.Items.STORAGE_BLOCKS_GREG_STAR, "greg_star_block", "greg_star_from_gregstar_block");
        this.decompression(Items.ENDER_PEARL, 9, ATTTags.Items.STORAGE_BLOCKS_ENDER_PEARL, "ender_pearl_block", "ender_pearl_from_ender_pearl_block");
        this.decompression(Items.NETHER_STAR, 9, ATTTags.Items.STORAGE_BLOCKS_NETHER_STAR, "nether_star_block", "nether_star_from_nether_star_block");
    }
    
    private void decompression(Item output, int count, TagKey<Item> input, String unlockName, String path) {
        ShapelessRecipeBuilder.shapeless(this.items, RecipeCategory.MISC, output, count)
                .requires(input)
                .unlockedBy("has_" + unlockName, this.has(input))
                .save(this.output, ATT.id(path).toString());
    }
    
    public static class Runner extends RecipeProvider.Runner {
        public Runner(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
            super(output, lookupProvider);
        }
        
        @Override
        protected RecipeProvider createRecipeProvider(HolderLookup.Provider lookupProvider, RecipeOutput output) {
            return new ATTRecipeProvider(lookupProvider, output);
        }
        
        @Override
        public String getName() {
            return "AllTheTweaks recipes";
        }
    }
}
