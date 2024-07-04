package com.thevortex.allthetweaks.datagen.client;

import com.thevortex.allthetweaks.blocks.TweakBlocks;
import com.thevortex.allthetweaks.config.Reference;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;

import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ItemModels extends ItemModelProvider {

    public ItemModels(DataGenerator generator, ExistingFileHelper fileHelper) {
        super(generator.getPackOutput(), Reference.MOD_ID, fileHelper);
    }

    private ResourceLocation res(String name) {
        return ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, ITEM_FOLDER + "/" + name);
    }

    @Override
    protected void registerModels() {
        ResourceLocation generated = ResourceLocation.withDefaultNamespace("item/generated");

        TweakBlocks.ITEMS.getEntries().stream()
            .filter(item -> !(item.get() instanceof BlockItem))
            .forEach(item -> {
                String name = item.getId().getPath();
                if(!name.contains("bucket")){
                withExistingParent(name, generated)
                    .texture("layer0", res(name));

            }});
    }
}
