package net.allthemods.allthetweaks.data;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import net.allthemods.allthetweaks.api.ATT;
import net.allthemods.allthetweaks.data.provider.ATTLanguageProvider;
import net.allthemods.allthetweaks.data.provider.ATTModelProvider;
import net.allthemods.allthetweaks.data.provider.ATTRecipeProvider;
import net.allthemods.allthetweaks.data.provider.loot.ATTLootProvider;
import net.allthemods.allthetweaks.data.provider.tags.ATTBlockTagsProvider;
import net.allthemods.allthetweaks.data.provider.tags.ATTItemTagsProvider;

@EventBusSubscriber(modid = ATT.MOD_ID)
public class ATTDataGenerator {
    
    @SubscribeEvent
    public static void gatherData(GatherDataEvent.Client event) {
        if (!ATT.MOD_ID.equalsIgnoreCase(event.getModContainer().getModId())) return;
        
        event.createProvider(ATTModelProvider::new);
        event.createProvider(ATTLanguageProvider::new);
        event.createProvider(ATTLootProvider::create);
        event.createProvider(ATTBlockTagsProvider::new);
        event.createProvider(ATTItemTagsProvider::new);
        event.createProvider(ATTRecipeProvider.Runner::new);
    }
}
