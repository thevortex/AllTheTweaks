package com.thevortex.allthetweaks.datagen.server;

import com.thevortex.allthetweaks.config.Reference;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Consumer;

public class SmeltingRecipes extends RecipeProvider {
    public SmeltingRecipes(DataGenerator generator) {
        super(generator);
    }
    private ResourceLocation recipeDir(String typeIn, String typeOut) {
        return new ResourceLocation(Reference.MOD_ID,typeIn + "_from_" + typeOut);
    }
    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {

        final String hasCondition = "has_item";

       /* SimpleCookingRecipeBuilder
                .smelting(Ingredient.of(BlockList.BRONZE_DUST.get()),BlockList.BRONZE_INGOT.get(),0.15f,200)
                .unlockedBy(hasCondition,RecipeProvider.has(BlockList.BRONZE_DUST.get()))
                .save(consumer,recipeDir("bronze_ingot","dust"));
*/


    }
}
