package com.thevortex.allthetweaks.blocks;

import com.thevortex.allthetweaks.config.Reference;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class TweakBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,
            Reference.MOD_ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Reference.MOD_ID);

    public static final RegistryObject<Block> ENDERPEARL_BLOCK = BLOCKS.register("ender_pearl_block",() -> new Block(Block.Properties.of(Material.METAL).strength(0.85f,1.0f).sound(SoundType.GLASS)));
    public static final RegistryObject<Block> NETHERSTAR_BLOCK = BLOCKS.register("nether_star_block",() -> new Block(Block.Properties.of(Material.METAL).strength(0.85f,1.0f).sound(SoundType.GLASS)));
    public static final RegistryObject<Block> ATMSTAR_BLOCK = BLOCKS.register("atm_star_block",() -> new Block(Block.Properties.of(Material.METAL).strength(0.85f,1.0f).sound(SoundType.METAL)));


    public static final RegistryObject<Item> ENDERPEARL_BLOCK_ITEM = ITEMS.register("ender_pearl_block",
            () -> new BlockItem(ENDERPEARL_BLOCK.get(), new Item.Properties().tab(Reference.GROUP)));

   public static final RegistryObject<Item> NETHERSTAR_BLOCK_ITEM = ITEMS.register("nether_star_block",
        () -> new BlockItem(NETHERSTAR_BLOCK.get(), new Item.Properties().tab(Reference.GROUP)));

    public static final RegistryObject<Item> ATMSTAR_BLOCK_ITEM = ITEMS.register("atm_star_block",
            () -> new BlockItem(ATMSTAR_BLOCK.get(), new Item.Properties().tab(Reference.GROUP)));

    public static final RegistryObject<Item> ATMSTAR = ITEMS.register("atm_star", () -> new Item(new Item.Properties().tab(Reference.GROUP)));

}
