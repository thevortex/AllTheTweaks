package com.thevortex.allthetweaks.datagen.server;


import com.thevortex.allthetweaks.blocks.TweakBlocks;
import com.thevortex.allthetweaks.config.Reference;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.Tags;

import java.util.function.Consumer;

public class CraftingRecipes extends RecipeProvider {
    public CraftingRecipes(DataGenerator generatorIn) {
        super(generatorIn);
    }

    private ShapedRecipeBuilder shaped(ItemLike provider) {
        return ShapedRecipeBuilder.shaped(provider)
            .group(Reference.MOD_ID);
    }


    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
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
        final String hasCondition = "has_item";

        shaped(TweakBlocks.ENDERPEARL_BLOCK.get())
                .pattern("ppp")
                .pattern("ppp")
                .pattern("ppp")
                .define('p',Tags.Items.ENDER_PEARLS)
                .unlockedBy("has_ender_pearl",RecipeProvider.inventoryTrigger(ItemPredicate.Builder.item().of(Tags.Items.ENDER_PEARLS).build()))
                .save(consumer);

        shaped(TweakBlocks.NETHERSTAR_BLOCK.get())
                .pattern("sss")
                .pattern("sss")
                .pattern("sss")
                .define('s',Tags.Items.NETHER_STARS)
                .unlockedBy("has_nether_star",RecipeProvider.inventoryTrigger(ItemPredicate.Builder.item().of(Tags.Items.NETHER_STARS).build()))
                .save(consumer);

        shaped(TweakBlocks.ATMSTAR_BLOCK.get())
                .pattern("aaa")
                .pattern("aaa")
                .pattern("aaa")
                .define('a',Reference.ATMSTAR)
                .unlockedBy("has_atm_star",RecipeProvider.inventoryTrigger(ItemPredicate.Builder.item().of(Reference.ATMSTAR).build()))
                .save(consumer);
    }
}
