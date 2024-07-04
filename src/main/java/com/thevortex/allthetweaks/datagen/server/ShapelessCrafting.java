package com.thevortex.allthetweaks.datagen.server;

import com.thevortex.allthetweaks.blocks.TweakBlocks;
import com.thevortex.allthetweaks.config.Reference;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;

import net.minecraft.core.HolderLookup.Provider;
import java.util.concurrent.CompletableFuture;

public class ShapelessCrafting extends RecipeProvider {

private ResourceLocation recipeDir(String typeIn, String typeOut) {
    return ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID,typeIn + "_from_" + typeOut);
}
    @Override
    protected void buildRecipes(RecipeOutput consumer) {

        final String hasCondition = "has_item";


        /*ShapelessRecipeBuilder
                .shapeless(BlockList.STEEL_NUGGET.get(),9)
                .requires(ItemTagRegistry.STEEL_INGOT)
                .unlockedBy(hasCondition,RecipeProvider.has(BlockList.STEEL_INGOT.get()))
                .save(consumer,recipeDir("steel_nugget","ingot"));
*/      ShapelessRecipeBuilder
                .shapeless(RecipeCategory.MISC,TweakBlocks.ATMSTAR.get(),9)
                .requires(Reference.ATMSTAR_BLOCK_ITEM)
                .unlockedBy(getHasName(TweakBlocks.ATMSTAR_BLOCK_ITEM.get()), has(Reference.ATMSTAR_BLOCK_ITEM))
                .save(consumer,recipeDir("atm_star","atmstar_block"));
        ShapelessRecipeBuilder
                .shapeless(RecipeCategory.MISC,TweakBlocks.GREGSTAR.get(),9)
                .requires(Reference.GREGSTAR_BLOCK_ITEM)
                .unlockedBy(getHasName(TweakBlocks.GREGSTAR_BLOCK_ITEM.get()), has(Reference.GREGSTAR_BLOCK_ITEM))
                .save(consumer,recipeDir("greg_star","gregstar_block"));
        ShapelessRecipeBuilder
                .shapeless(RecipeCategory.MISC,Items.ENDER_PEARL,9)
                .requires(Reference.ENDERPEARL_BLOCK_ITEM)
                .unlockedBy(getHasName(TweakBlocks.ENDERPEARL_BLOCK_ITEM.get()), has(Reference.ENDERPEARL_BLOCK_ITEM))
                .save(consumer,recipeDir("ender_pearl","ender_pearl_block"));
        ShapelessRecipeBuilder
                .shapeless(RecipeCategory.MISC,Items.NETHER_STAR,9)
                .requires(Reference.NETHERSTAR_BLOCK_ITEM)
                .unlockedBy(getHasName(TweakBlocks.NETHERSTAR_BLOCK_ITEM.get()), has(Reference.NETHERSTAR_BLOCK_ITEM))
                .save(consumer,recipeDir("nether_star","nether_star_block"));
    }

    public ShapelessCrafting(PackOutput packOutput,CompletableFuture<Provider> provider) {
        super(packOutput,provider);
    }


}
