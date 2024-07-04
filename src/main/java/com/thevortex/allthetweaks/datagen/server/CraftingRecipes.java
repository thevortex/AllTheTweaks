package com.thevortex.allthetweaks.datagen.server;


import com.thevortex.allthetweaks.blocks.TweakBlocks;
import com.thevortex.allthetweaks.config.Reference;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.Tags;

import net.minecraft.core.HolderLookup.Provider;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class CraftingRecipes extends RecipeProvider {
    public CraftingRecipes(PackOutput packOutput, CompletableFuture<Provider> provider) {
        super(packOutput,provider);
    }

    private ShapedRecipeBuilder shaped(ItemLike provider) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.MISC,provider)
            .group(Reference.MOD_ID);
    }


    @Override
    protected void buildRecipes(RecipeOutput consumer) {
/*
        shaped(TheGuide.TIER_1_CORE.get())
            .pattern("g g")
            .pattern("ppp")
            .pattern("g g")
            .define('g', Tags.Items.INGOTS_GOLD)
            .define('p', ItemTagRegistry.PLATINUM_INGOT)
            .unlockedBy("has_platinum_ingot", has(ItemTagRegistry.PLATINUM_INGOT))
            .save(consumer);
*/
        shaped(TweakBlocks.ENDERPEARL_BLOCK.get())
                .pattern("ppp")
                .pattern("ppp")
                .pattern("ppp")
                .define('p',Tags.Items.ENDER_PEARLS)
                .unlockedBy("has_ender_pearl", has(Tags.Items.ENDER_PEARLS))
                .save(consumer);

        shaped(TweakBlocks.NETHERSTAR_BLOCK.get())
                .pattern("sss")
                .pattern("sss")
                .pattern("sss")
                .define('s',Tags.Items.NETHER_STARS)
                .unlockedBy("has_nether_star", has(Tags.Items.NETHER_STARS))
                .save(consumer);

        shaped(TweakBlocks.ATMSTAR_BLOCK.get())
                .pattern("aaa")
                .pattern("aaa")
                .pattern("aaa")
                .define('a',Reference.ATMSTAR)
                .unlockedBy("has_atm_star", has(Reference.ATMSTAR))
                .save(consumer);

        shaped(TweakBlocks.GREGSTAR_BLOCK.get())
                .pattern("aaa")
                .pattern("aaa")
                .pattern("aaa")
                .define('a', Reference.GREGSTAR)
                .unlockedBy(getHasName(TweakBlocks.GREGSTAR_BLOCK.get()), has(Reference.GREGSTAR))
                .save(consumer);
    }

    
}
