package com.thevortex.allthetweaks.datagen.server;

import com.thevortex.allthetweaks.config.Reference;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Consumer;

public class BlastingRecipes extends RecipeProvider {
    public BlastingRecipes(PackOutput packOutput) {
        super(packOutput);
    }
    private ResourceLocation recipeDir(String typeIn, String typeOut) {
        return new ResourceLocation(Reference.MOD_ID,typeIn + "_from_" + typeOut + "_blasting");
    }
    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {

        final String hasCondition = "has_item";

       /* SimpleCookingRecipeBuilder
                .blasting(Ingredient.of(BlockList.INVAR_DUST.get()),BlockList.INVAR_INGOT.get(),0.15f,100)
                .unlockedBy(hasCondition,RecipeProvider.has(BlockList.INVAR_DUST.get()))
                .save(consumer,recipeDir("invar_ingot","dust"));
*/
    }
}
