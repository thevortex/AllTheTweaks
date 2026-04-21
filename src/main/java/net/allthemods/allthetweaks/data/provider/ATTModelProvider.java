package net.allthemods.allthetweaks.data.provider;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.BlockItem;

import net.allthemods.allthetweaks.api.ATT;
import net.allthemods.allthetweaks.core.ATTRegistry;

public class ATTModelProvider extends ModelProvider {
    
    public ATTModelProvider(PackOutput output) {
        super(output, ATT.MOD_ID);
    }
    
    @Override
    protected void registerModels(BlockModelGenerators blocks, ItemModelGenerators items) {
        blocks.createTrivialCube(ATTRegistry.ENDERPEARL_BLOCK.get());
        blocks.createTrivialCube(ATTRegistry.NETHERSTAR_BLOCK.get());
        blocks.createTrivialCube(ATTRegistry.ATMSTAR_BLOCK.get());
        blocks.createTrivialCube(ATTRegistry.GREGSTAR_BLOCK.get());
        this.createHorizontalStaticBlock(blocks, ATTRegistry.MINI_END_BLOCK.get());
        this.createHorizontalStaticBlock(blocks, ATTRegistry.MINI_EXIT_BLOCK.get());
        this.createHorizontalStaticBlock(blocks, ATTRegistry.MINI_NETHER_BLOCK.get());
        blocks.createNonTemplateModelBlock(ATTRegistry.ATM_TROPHY.get());
        
        ATTRegistry.ITEMS.getEntries().forEach(item -> {
            if (!(item.get() instanceof BlockItem)) {
                items.generateFlatItem(item.get(), ModelTemplates.FLAT_ITEM);
            }
        });
    }
    
    private void createHorizontalStaticBlock(BlockModelGenerators blocks, net.minecraft.world.level.block.Block block) {
        blocks.blockStateOutput.accept(
                MultiVariantGenerator.dispatch(block, BlockModelGenerators.plainVariant(ModelLocationUtils.getModelLocation(block)))
                        .with(BlockModelGenerators.ROTATION_HORIZONTAL_FACING)
        );
    }
}
