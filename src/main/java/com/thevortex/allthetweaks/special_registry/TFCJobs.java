package com.thevortex.allthetweaks.special_registry;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

import net.dries007.tfc.common.blocks.TFCBlocks;
import net.dries007.tfc.common.blocks.wood.Wood;
import net.dries007.tfc.util.Metal;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.ai.village.poi.PoiTypes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class TFCJobs {

    public static DeferredRegister<PoiType> POI_TYPES = DeferredRegister.create(ForgeRegistries.POI_TYPES, "minecraft");

    private static final HashSet<BlockState> FISH_STATES = new HashSet<>(ForgeRegistries.POI_TYPES.getDelegateOrThrow(PoiTypes.FISHERMAN).get().matchingStates());
    private static final HashSet<BlockState> ARMOR_STATES = new HashSet<>(ForgeRegistries.POI_TYPES.getDelegateOrThrow(PoiTypes.ARMORER).get().matchingStates());
    private static final HashSet<BlockState> BUTCHER_STATES = new HashSet<>(ForgeRegistries.POI_TYPES.getDelegateOrThrow(PoiTypes.BUTCHER).get().matchingStates());
    private static final HashSet<BlockState> CARTOGRAPHER_STATES = new HashSet<>(ForgeRegistries.POI_TYPES.getDelegateOrThrow(PoiTypes.CARTOGRAPHER).get().matchingStates());
    private static final HashSet<BlockState> CLERIC_STATES = new HashSet<>(ForgeRegistries.POI_TYPES.getDelegateOrThrow(PoiTypes.CLERIC).get().matchingStates());
    private static final HashSet<BlockState> FARMER_STATES = new HashSet<>(ForgeRegistries.POI_TYPES.getDelegateOrThrow(PoiTypes.FARMER).get().matchingStates());
    private static final HashSet<BlockState> FLETCHER_STATES = new HashSet<>(ForgeRegistries.POI_TYPES.getDelegateOrThrow(PoiTypes.FLETCHER).get().matchingStates());
    private static final HashSet<BlockState> LEATHER_STATES = new HashSet<>(ForgeRegistries.POI_TYPES.getDelegateOrThrow(PoiTypes.LEATHERWORKER).get().matchingStates());
    private static final HashSet<BlockState> LIBRARY_STATES = new HashSet<>(ForgeRegistries.POI_TYPES.getDelegateOrThrow(PoiTypes.LIBRARIAN).get().matchingStates());
    private static final HashSet<BlockState> MASON_STATES = new HashSet<>(ForgeRegistries.POI_TYPES.getDelegateOrThrow(PoiTypes.MASON).get().matchingStates());
    private static final HashSet<BlockState> SHEPHERD_STATES = new HashSet<>(ForgeRegistries.POI_TYPES.getDelegateOrThrow(PoiTypes.SHEPHERD).get().matchingStates());
    private static final HashSet<BlockState> TOOLSMITH_STATES = new HashSet<>(ForgeRegistries.POI_TYPES.getDelegateOrThrow(PoiTypes.TOOLSMITH).get().matchingStates());
    private static final HashSet<BlockState> WEAPON_STATES = new HashSet<>(ForgeRegistries.POI_TYPES.getDelegateOrThrow(PoiTypes.WEAPONSMITH).get().matchingStates());

    private static final RegistryObject<Block>[] COMPOSTER = new RegistryObject[]{
            TFCBlocks.COMPOSTER
    };
    private static final RegistryObject<Block>[] TOOLS = new RegistryObject[]{
            TFCBlocks.CHARCOAL_FORGE
    };
    private static final RegistryObject<Block>[] CLERICAL = new RegistryObject[]{
            TFCBlocks.POT
    };
    private static final RegistryObject<Block>[] BUTCHERY = new RegistryObject[]{
            TFCBlocks.GRILL
    };
    private static final RegistryObject<Block>[] SCRAPING = new RegistryObject[]{
            TFCBlocks.SCRAPING
    };
    private static final RegistryObject<Block>[] MASONRY = new RegistryObject[]{
            TFCBlocks.QUERN
    };
    public static final RegistryObject<PoiType> FARMER = POI_TYPES.register("farmer", () -> {
        FARMER_STATES.removeAll(Blocks.COMPOSTER.getStateDefinition().getPossibleStates());
        for (RegistryObject<Block> block : COMPOSTER) {
            FARMER_STATES.addAll(block.get().getStateDefinition().getPossibleStates());
        }
        return new PoiType(FARMER_STATES, 1, 1);
    });

    public static final RegistryObject<PoiType> TOOLSMITH = POI_TYPES.register("toolsmith", () -> {
        TOOLSMITH_STATES.removeAll(Blocks.SMITHING_TABLE.getStateDefinition().getPossibleStates());
        ArrayList<Block> METALANVILS = new ArrayList<>();
        TFCBlocks.METALS.forEach((metal, types) -> {
            if (((metal.metalTier() == Metal.Tier.TIER_I)) && metal.hasTools()) {
                METALANVILS.add(types.get(Metal.BlockType.ANVIL).get());
            }
        });
        for (Block block : METALANVILS) {
            TOOLSMITH_STATES.addAll(block.getStateDefinition().getPossibleStates());
        }
        return new PoiType(TOOLSMITH_STATES, 1, 1);
    });

    public static final RegistryObject<PoiType> WEAPONSMITH = POI_TYPES.register("weaponsmith", () -> {
        WEAPON_STATES.removeAll(Blocks.GRINDSTONE.getStateDefinition().getPossibleStates());
        ArrayList<Block> METALANVILS = new ArrayList<>();
        TFCBlocks.METALS.forEach((metal, types) -> {
            if (((metal.metalTier() == Metal.Tier.TIER_II) || (metal.metalTier() == Metal.Tier.TIER_III)) && metal.hasTools()) {
                METALANVILS.add(types.get(Metal.BlockType.ANVIL).get());
            }
        });
        for (Block block : METALANVILS) {
            WEAPON_STATES.addAll(block.getStateDefinition().getPossibleStates());
        }
        return new PoiType(WEAPON_STATES, 1, 1);
    });
    public static final RegistryObject<PoiType> CLERIC = POI_TYPES.register("cleric", () -> {
        CLERIC_STATES.removeAll(Blocks.BREWING_STAND.getStateDefinition().getPossibleStates());
        for (RegistryObject<Block> block : CLERICAL) {
            CLERIC_STATES.addAll(block.get().getStateDefinition().getPossibleStates());
        }
        return new PoiType(CLERIC_STATES, 1, 1);
    });
    public static final RegistryObject<PoiType> BUTCHER = POI_TYPES.register("butcher", () -> {
        BUTCHER_STATES.removeAll(Blocks.SMOKER.getStateDefinition().getPossibleStates());
        for (RegistryObject<Block> block : BUTCHERY) {
            BUTCHER_STATES.addAll(block.get().getStateDefinition().getPossibleStates());
        }
        return new PoiType(BUTCHER_STATES, 1, 1);
    });
    public static final RegistryObject<PoiType> LEATHERWORKER = POI_TYPES.register("leatherworker", () -> {
        LEATHER_STATES.removeAll(ImmutableList.of(Blocks.CAULDRON, Blocks.LAVA_CAULDRON, Blocks.WATER_CAULDRON, Blocks.POWDER_SNOW_CAULDRON).stream().flatMap((p_218093_) -> {
            return p_218093_.getStateDefinition().getPossibleStates().stream();
        }).collect(ImmutableSet.toImmutableSet()));
        for (RegistryObject<Block> block : SCRAPING) {
            LEATHER_STATES.addAll(block.get().getStateDefinition().getPossibleStates());
        }
        return new PoiType(LEATHER_STATES, 1, 1);
    });
    public static final RegistryObject<PoiType> SHEPHERD = POI_TYPES.register("shepherd", () -> {
        SHEPHERD_STATES.remove(Blocks.LOOM);
        ArrayList<Block> SHEEPS = new ArrayList<>();
        TFCBlocks.WOODS.forEach((wood, types) -> {
             SHEEPS.add(types.get(Wood.BlockType.LOOM).get());
        });
        for (Block block : SHEEPS) {
            SHEPHERD_STATES.addAll(block.getStateDefinition().getPossibleStates());
        }
        return new PoiType(SHEPHERD_STATES, 1, 1);
    });
    public static final RegistryObject<PoiType> LIBRARIAN = POI_TYPES.register("librarian", () -> {
        LIBRARY_STATES.remove(Blocks.LECTERN);
        ArrayList<Block> LECTERNS = new ArrayList<>();
        TFCBlocks.WOODS.forEach((wood, types) -> {
            LECTERNS.add(types.get(Wood.BlockType.LECTERN).get());
        });
        for (Block block : LECTERNS) {
            LIBRARY_STATES.addAll(block.getStateDefinition().getPossibleStates());
        }
        return new PoiType(LIBRARY_STATES, 1, 1);
    });
    public static final RegistryObject<PoiType> FISHERMAN = POI_TYPES.register("fisherman", () -> {
        FISH_STATES.clear();
        ArrayList<Block> BARRELS = new ArrayList<>();
        TFCBlocks.WOODS.forEach((wood, types) -> {
            BARRELS.add(types.get(Wood.BlockType.BARREL).get());
        });
        for (Block block : BARRELS) {
            FISH_STATES.addAll(block.getStateDefinition().getPossibleStates());
        }
        return new PoiType(FISH_STATES, 1, 1);
    });
    public static final RegistryObject<PoiType> CARTOGRAPHER = POI_TYPES.register("cartographer", () -> {
        CARTOGRAPHER_STATES.clear();
        ArrayList<Block> SCRIBING = new ArrayList<>();
        TFCBlocks.WOODS.forEach((wood, types) -> {
            SCRIBING.add(types.get(Wood.BlockType.SCRIBING_TABLE).get());
        });
        for (Block block : SCRIBING) {
            CARTOGRAPHER_STATES.addAll(block.getStateDefinition().getPossibleStates());
        }
        return new PoiType(CARTOGRAPHER_STATES, 1, 1);
    });
    public static final RegistryObject<PoiType> ARMORSMITH = POI_TYPES.register("armorer", () -> {
        ARMOR_STATES.removeAll(Blocks.BLAST_FURNACE.getStateDefinition().getPossibleStates());
        ArrayList<Block> METALANVILS = new ArrayList<>();
        TFCBlocks.METALS.forEach((metal, types) -> {
            if(((metal.metalTier() == Metal.Tier.TIER_IV) || (metal.metalTier() == Metal.Tier.TIER_V) ) && metal.hasTools() ) {
                METALANVILS.add(types.get(Metal.BlockType.ANVIL).get());
            }
        });
        for (Block block : METALANVILS) {
            ARMOR_STATES.addAll(block.getStateDefinition().getPossibleStates());
        }
        return new PoiType(ARMOR_STATES, 1, 1);
    });
    public static final RegistryObject<PoiType> MASON = POI_TYPES.register("mason", () -> {
        MASON_STATES.clear();
        for (RegistryObject<Block> block : MASONRY) {
            MASON_STATES.addAll(block.get().getStateDefinition().getPossibleStates());
        }
        return new PoiType(MASON_STATES, 1, 1);
    });
    public static final RegistryObject<PoiType> FLETCHER = POI_TYPES.register("fletcher", () -> {
        FLETCHER_STATES.clear();
        ArrayList<Block> CRAFTING = new ArrayList<>();
        TFCBlocks.WOODS.forEach((wood, types) -> {
            CRAFTING.add(types.get(Wood.BlockType.WORKBENCH).get());
        });
        for (Block block : CRAFTING) {
            FLETCHER_STATES.addAll(block.getStateDefinition().getPossibleStates());
        }
        return new PoiType(FLETCHER_STATES, 1, 1);
    });
}


