package com.thevortex.allthetweaks.datagen.server;

import com.thevortex.allthetweaks.blocks.TweakBlocks;
import com.thevortex.allthetweaks.config.Reference;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;

import java.util.function.Consumer;

public class ShapelessCrafting extends RecipeProvider {

private ResourceLocation recipeDir(String typeIn, String typeOut) {
    return new ResourceLocation(Reference.MOD_ID,typeIn + "_from_" + typeOut);
}
    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {

        final String hasCondition = "has_item";


        /*ShapelessRecipeBuilder
                .shapeless(BlockList.STEEL_NUGGET.get(),9)
                .requires(ItemTagRegistry.STEEL_INGOT)
                .unlockedBy(hasCondition,RecipeProvider.has(BlockList.STEEL_INGOT.get()))
                .save(consumer,recipeDir("steel_nugget","ingot"));
*/      ShapelessRecipeBuilder
                .shapeless(TweakBlocks.ATMSTAR.get(),9)
                .requires(Reference.ATMSTAR_BLOCK_ITEM)
                .unlockedBy(hasCondition,RecipeProvider.inventoryTrigger(ItemPredicate.Builder.item().of(Reference.ATMSTAR_BLOCK_ITEM).build()))
                .save(consumer,recipeDir("atm_star","atmstar_block"));
        ShapelessRecipeBuilder
                .shapeless(Items.ENDER_PEARL,9)
                .requires(Reference.ENDERPEARL_BLOCK_ITEM)
                .unlockedBy(hasCondition,RecipeProvider.inventoryTrigger(ItemPredicate.Builder.item().of(Reference.ENDERPEARL_BLOCK_ITEM).build()))
                .save(consumer,recipeDir("ender_pearl","ender_pearl_block"));
        ShapelessRecipeBuilder
                .shapeless(Items.NETHER_STAR,9)
                .requires(Reference.NETHERSTAR_BLOCK_ITEM)
                .unlockedBy(hasCondition,RecipeProvider.inventoryTrigger(ItemPredicate.Builder.item().of(Reference.NETHERSTAR_BLOCK_ITEM).build()))
                .save(consumer,recipeDir("nether_star","nether_star_block"));
    }

    public ShapelessCrafting(DataGenerator generatorIn) {
        super(generatorIn);
    }
}
