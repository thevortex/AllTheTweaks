package com.thevortex.allthetweaks.blocks;

import com.thevortex.allthetweaks.config.Reference;

import net.minecraft.client.renderer.block.model.ItemOverride.Predicate;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class TweakBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.createBlocks(Reference.MOD_ID);
        
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.createItems(Reference.MOD_ID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Reference.MOD_ID);

    public static final DeferredHolder<Block,Block> ENDERPEARL_BLOCK = BLOCKS.register("ender_pearl_block",() -> new Block(BlockBehaviour.Properties.of().strength(0.85f,1.0f).sound(SoundType.GLASS)));
    public static final DeferredHolder<Block,Block> NETHERSTAR_BLOCK = BLOCKS.register("nether_star_block",() -> new Block(BlockBehaviour.Properties.of().strength(0.85f,1.0f).sound(SoundType.GLASS)));
    public static final DeferredHolder<Block,Block> ATMSTAR_BLOCK = BLOCKS.register("atm_star_block",() -> new Block(BlockBehaviour.Properties.of().strength(0.85f,1.0f).sound(SoundType.METAL)));
    public static final DeferredHolder<Block,Block> GREGSTAR_BLOCK = BLOCKS.register("greg_star_block",() -> new Block(BlockBehaviour.Properties.of().strength(0.85f,1.0f).sound(SoundType.METAL)));
    public static final DeferredHolder<Block,Block> MINI_END_BLOCK = BLOCKS.register("mini_end",() -> new MiniPortalBlock(BlockBehaviour.Properties.of().strength(0.75f).sound(SoundType.STONE).noOcclusion()));
    public static final DeferredHolder<Block,Block> MINI_EXIT_BLOCK = BLOCKS.register("mini_exit",() -> new MiniPortalBlock(BlockBehaviour.Properties.of().strength(0.75f).sound(SoundType.STONE).noOcclusion()));
    public static final DeferredHolder<Block,Block> MINI_NETHER_BLOCK = BLOCKS.register("mini_nether",() -> new MiniPortalBlock(BlockBehaviour.Properties.of().strength(0.75f).sound(SoundType.NETHERRACK).noOcclusion()));

    public static final DeferredHolder<Block,Block> ATM_TROPHY = BLOCKS.register("trophy_atm",() -> new Block(Block.Properties.of().strength(0.75f).sound(SoundType.NETHERRACK).noOcclusion()));

    public static final DeferredHolder<Item,Item> ENDERPEARL_BLOCK_ITEM = ITEMS.register("ender_pearl_block",
            () -> new BlockItem(ENDERPEARL_BLOCK.get(), new Item.Properties()));

   public static final DeferredHolder<Item,Item> NETHERSTAR_BLOCK_ITEM = ITEMS.register("nether_star_block",
        () -> new BlockItem(NETHERSTAR_BLOCK.get(), new Item.Properties()));

    public static final DeferredHolder<Item,Item> ATMSTAR_BLOCK_ITEM = ITEMS.register("atm_star_block",
            () -> new BlockItem(ATMSTAR_BLOCK.get(), new Item.Properties()));

    public static final DeferredHolder<Item,Item> GREGSTAR_BLOCK_ITEM = ITEMS.register("greg_star_block",
            () -> new BlockItem(GREGSTAR_BLOCK.get(), new Item.Properties()));

    public static final DeferredHolder<Item,Item> MINI_END_BLOCK_ITEM = ITEMS.register("mini_end",
    () -> new BlockItem(MINI_END_BLOCK.get(), new Item.Properties()));
    
    public static final DeferredHolder<Item,Item> MINI_EXIT_BLOCK_ITEM = ITEMS.register("mini_exit",
            () -> new BlockItem(MINI_EXIT_BLOCK.get(), new Item.Properties()));
            
    public static final DeferredHolder<Item,Item> MINI_NETHER_BLOCK_ITEM = ITEMS.register("mini_nether",
    () -> new BlockItem(MINI_NETHER_BLOCK.get(), new Item.Properties()));
    public static final DeferredHolder<Item,Item> TROPHY_ATM = ITEMS.register("trophy_atm",
            () -> new BlockItem(ATM_TROPHY.get(), new Item.Properties()));

    public static final DeferredHolder<Item,Item> ATMSTAR = ITEMS.register("atm_star", () -> new Shiny(new Item.Properties()));
    public static final DeferredHolder<Item,Item> GREGSTAR = ITEMS.register("greg_star", () -> new Shiny(new Item.Properties()));
    public static final DeferredHolder<Item,Item> ATMSTAR_SHARD = ITEMS.register("atm_star_shard", () -> new Shiny(new Item.Properties()));
    public static final DeferredHolder<Item,Item> CATALYST = ITEMS.register("allthecatalystium",() -> new Shiny(new Item.Properties()));
    public static final DeferredHolder<Item,Item> DIM_SEED = ITEMS.register("dimensional_seed", () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item,Item> DRAGON_SOUL = ITEMS.register("dragon_soul", () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item,Item> IMPPD = ITEMS.register("improbable_probability_device", () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item,Item> NEXIUM_EMITTER = ITEMS.register("nexium_emitter", () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item,Item> OBLIV_SHARD = ITEMS.register("oblivion_shard", () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item,Item> PATRICK_STAR = ITEMS.register("patrick_star", () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item,Item> PHIL_FUEL = ITEMS.register("philosophers_fuel", () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item,Item> PULSE_BLACK_HOLE = ITEMS.register("pulsating_black_hole", () -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item,Item> WITHER_COMPASS = ITEMS.register("withers_compass", () -> new WitherCompass(new Item.Properties()));

    public static final DeferredHolder<CreativeModeTab,CreativeModeTab> CREATIVE_TAB = CREATIVE_TABS.register("creative_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable(Reference.tab()))
            .icon(() -> ATMSTAR.get().getDefaultInstance())
            .displayItems((parameters, output) -> ITEMS.getEntries().stream()
                    .map(DeferredHolder::get)
                    .map(Item::getDefaultInstance)
                    .forEach(output::accept))
            .build()
    );
    
}
