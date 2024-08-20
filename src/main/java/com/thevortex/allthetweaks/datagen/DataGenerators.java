package com.thevortex.allthetweaks.datagen;


import com.thevortex.allthetweaks.AllTheTweaks;
import com.thevortex.allthetweaks.datagen.client.BlockStates;
import com.thevortex.allthetweaks.datagen.client.ItemModels;
import com.thevortex.allthetweaks.datagen.server.*;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;


import java.io.IOException;
import java.util.Collections;
import java.util.List;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = AllTheTweaks.MODID)
public final class DataGenerators
{
    private DataGenerators() {
    }

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) throws IOException {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper fileHelper = event.getExistingFileHelper();

        generator.addProvider(event.includeClient(), new BlockStates(generator, fileHelper));
        generator.addProvider(event.includeClient(), new ItemModels(generator, fileHelper));

        BlockTags blockTags = new BlockTags(packOutput, event.getLookupProvider(), fileHelper);
        generator.addProvider(event.includeServer(), blockTags);
        generator.addProvider(event.includeServer(), new ItemTags(packOutput, event.getLookupProvider(), blockTags.contentsGetter(), fileHelper));
        generator.addProvider(true,new LootTableProvider(packOutput, Collections.emptySet(),
                    List.of(new LootTableProvider.SubProviderEntry(LootTables::new, LootContextParamSets.BLOCK)),event.getLookupProvider()));

        generator.addProvider(event.includeClient(), new CraftingRecipes(packOutput,event.getLookupProvider()));
    }
}
