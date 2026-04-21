package net.allthemods.allthetweaks.core;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;

import net.allthemods.allthetweaks.api.ATT;
import net.allthemods.allthetweaks.common.blocks.MiniPortalBlock;
import net.allthemods.allthetweaks.common.items.ShinyItem;
import net.allthemods.allthetweaks.common.items.WitherCompassItem;

import java.util.Locale;
import java.util.function.Function;

public class ATTRegistry {
    
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.createBlocks(ATT.MOD_ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.createItems(ATT.MOD_ID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ATT.MOD_ID);
    
    public static final DeferredHolder<Block, Block> ENDERPEARL_BLOCK = ATTRegistry.registerBlock("ender_pearl_block", p -> new Block(p.strength(0.85f, 1.0f).sound(SoundType.GLASS)));
    public static final DeferredHolder<Block, Block> NETHERSTAR_BLOCK = ATTRegistry.registerBlock("nether_star_block", p -> new Block(p.strength(0.85f, 1.0f).sound(SoundType.GLASS)));
    public static final DeferredHolder<Block, Block> ATMSTAR_BLOCK = ATTRegistry.registerBlock("atm_star_block", p -> new Block(p.strength(0.85f, 1.0f).sound(SoundType.METAL)));
    public static final DeferredHolder<Block, Block> GREGSTAR_BLOCK = ATTRegistry.registerBlock("greg_star_block", p -> new Block(p.strength(0.85f, 1.0f).sound(SoundType.METAL)));
    public static final DeferredHolder<Block, Block> MINI_END_BLOCK = ATTRegistry.registerBlock("mini_end", p -> new MiniPortalBlock(p.strength(0.75f).sound(SoundType.STONE).noOcclusion()));
    public static final DeferredHolder<Block, Block> MINI_EXIT_BLOCK = ATTRegistry.registerBlock("mini_exit", p -> new MiniPortalBlock(p.strength(0.75f).sound(SoundType.STONE).noOcclusion()));
    public static final DeferredHolder<Block, Block> MINI_NETHER_BLOCK = ATTRegistry.registerBlock("mini_nether", p -> new MiniPortalBlock(p.strength(0.75f).sound(SoundType.NETHERRACK).noOcclusion()));
    
    public static final DeferredHolder<Block, Block> ATM_TROPHY = ATTRegistry.registerBlock("trophy_atm", p -> new Block(p.strength(0.75f).sound(SoundType.NETHERRACK).noOcclusion()));
    
    public static final DeferredHolder<Item, Item> ATMSTAR = ATTRegistry.registerItem("atm_star", ShinyItem::new);
    public static final DeferredHolder<Item, Item> GREGSTAR = ATTRegistry.registerItem("greg_star", ShinyItem::new);
    public static final DeferredHolder<Item, Item> ATMSTAR_SHARD = ATTRegistry.registerItem("atm_star_shard", ShinyItem::new);
    public static final DeferredHolder<Item, Item> CATALYST = ATTRegistry.registerItem("allthecatalystium", ShinyItem::new);
    public static final DeferredHolder<Item, Item> DIM_SEED = ATTRegistry.registerItem("dimensional_seed", Item::new);
    public static final DeferredHolder<Item, Item> DRAGON_SOUL = ATTRegistry.registerItem("dragon_soul", Item::new);
    public static final DeferredHolder<Item, Item> IMPROBABLE_PROBABILITY_DEVICE = ATTRegistry.registerItem("improbable_probability_device", Item::new);
    public static final DeferredHolder<Item, Item> NEXIUM_EMITTER = ATTRegistry.registerItem("nexium_emitter", Item::new);
    public static final DeferredHolder<Item, Item> OBLIVION_SHARD = ATTRegistry.registerItem("oblivion_shard", Item::new);
    public static final DeferredHolder<Item, Item> PATRICK_STAR = ATTRegistry.registerItem("patrick_star", Item::new);
    public static final DeferredHolder<Item, Item> PHIL_FUEL = ATTRegistry.registerItem("philosophers_fuel", Item::new);
    public static final DeferredHolder<Item, Item> PULSE_BLACK_HOLE = ATTRegistry.registerItem("pulsating_black_hole", Item::new);
    public static final DeferredHolder<Item, Item> WITHER_COMPASS = ATTRegistry.registerItem("withers_compass", WitherCompassItem::new);
    
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> CREATIVE_TAB = ATTRegistry.CREATIVE_TABS.register("creative_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable(String.format(Locale.ROOT, "creative_tab.%s", ATT.MOD_ID)))
            .icon(() -> ATTRegistry.ATMSTAR.get().getDefaultInstance())
            .displayItems((parameters, output) -> ATTRegistry.ITEMS.getEntries().stream()
                    .map(DeferredHolder::get)
                    .map(Item::getDefaultInstance)
                    .forEach(output::accept))
            .build()
    );
    
    private static <T extends Block> DeferredHolder<Block, T> registerBlock(String name, Function<BlockBehaviour.Properties, T> factory) {
        DeferredHolder<Block, T> holder = ATTRegistry.BLOCKS.register(name, k -> factory.apply(BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, k))));
        ATTRegistry.ITEMS.register(name, k -> new BlockItem(holder.get(), new Item.Properties().setId(ResourceKey.create(Registries.ITEM, k)).useBlockDescriptionPrefix()));
        return holder;
    }
    
    private static <T extends Item> DeferredHolder<Item, T> registerItem(String name, Function<Item.Properties, T> factory) {
        return ATTRegistry.ITEMS.register(name, p -> factory.apply(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, p))));
    }
    
    public static void register(final IEventBus bus) {
        ATTRegistry.BLOCKS.register(bus);
        ATTRegistry.ITEMS.register(bus);
        ATTRegistry.CREATIVE_TABS.register(bus);
    }
}
