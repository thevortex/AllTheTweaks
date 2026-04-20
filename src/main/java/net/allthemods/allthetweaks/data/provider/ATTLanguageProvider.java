package net.allthemods.allthetweaks.data.provider;

import net.neoforged.neoforge.common.data.LanguageProvider;

import net.minecraft.data.PackOutput;

import net.allthemods.allthetweaks.api.ATT;
import net.allthemods.allthetweaks.core.ATTRegistry;

import java.util.Locale;

public class ATTLanguageProvider extends LanguageProvider {
    
    public ATTLanguageProvider(PackOutput output) {
        super(output, ATT.MOD_ID, "en_us");
    }
    
    @Override
    protected void addTranslations() {
        this.add(String.format(Locale.ROOT, "creative_tab.%s", ATT.MOD_ID), "AllTheTweaks");
        
        this.add(ATTRegistry.ATM_TROPHY.get(), "All The Mods Trophy");
        this.add(ATTRegistry.ENDERPEARL_BLOCK.get(), "Ender Pearl Block");
        this.add(ATTRegistry.ATMSTAR_BLOCK.get(), "ATM Star Block");
        this.add(ATTRegistry.GREGSTAR_BLOCK.get(), "GregStar Block");
        this.add(ATTRegistry.NETHERSTAR_BLOCK.get(), "Nether Star Block");
        this.add(ATTRegistry.MINI_END_BLOCK.get(), "Miniature End Portal");
        this.add(ATTRegistry.MINI_EXIT_BLOCK.get(), "Miniature Exit Portal");
        this.add(ATTRegistry.MINI_NETHER_BLOCK.get(), "Miniature Nether Portal");
        
        this.add(ATTRegistry.ATMSTAR.get(), "ATM Star");
        this.add(ATTRegistry.GREGSTAR.get(), "GregStar");
        this.add(ATTRegistry.CATALYST.get(), "AllTheCatalystium");
        this.add(ATTRegistry.ATMSTAR_SHARD.get(), "ATM Star Shard");
        this.add(ATTRegistry.PHIL_FUEL.get(), "Philosopher's Fuel");
        this.add(ATTRegistry.NEXIUM_EMITTER.get(), "Nexium Emitter");
        this.add(ATTRegistry.DRAGON_SOUL.get(), "Dragon Soul");
        this.add(ATTRegistry.WITHER_COMPASS.get(), "Wither's Compass");
        this.add(ATTRegistry.PULSE_BLACK_HOLE.get(), "Pulsating Black Hole");
        this.add(ATTRegistry.OBLIVION_SHARD.get(), "Oblivion Shard");
        this.add(ATTRegistry.IMPROBABLE_PROBABILITY_DEVICE.get(), "Improbable Probability Device");
        this.add(ATTRegistry.DIM_SEED.get(), "Dimensional Seed");
        this.add(ATTRegistry.PATRICK_STAR.get(), "Patrick Star");
        
        this.add("allthetweaks.valhelsia_core.cosmeticsWardrobe", "Valhelsia Team is selling Capes, violating Commercial Usage Guidelines!");
        this.add("allthetweaks.valhelsia_core.dontbuyCosmetics", "Until this ends, ALL Valhelsia cosmetic features will be disabled in ATM ModPacks.");
        this.add("travelerstitles.allthemodium.the_other", "The Other");
        this.add("travelerstitles.allthemodium.the_other.color", "153333");
    }
}
