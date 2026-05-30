package com.github.stellarwind23.template.fabric.datagen.provider;

import com.github.stellarwind23.template.util.MBlock;
import com.github.stellarwind23.template.util.MItem;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;
import org.jspecify.annotations.NonNull;

public class MModelProvider extends FabricModelProvider {

    public MModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(@NonNull BlockModelGenerators blockStateModelGenerator) {
        for(MBlock mBlock : MBlock.MOD_BLOCKS) {
            switch (mBlock.modelType()) {
                default -> blockStateModelGenerator.createTrivialCube(MBlock.REGISTERED_MOD_BLOCKS.get(mBlock));
            }
        }
    }

    @Override
    public void generateItemModels(@NonNull ItemModelGenerators itemModelGenerator) {
        for(MItem mItem : MItem.MOD_ITEMS) {
            switch (mItem.modelType()) {
                case "handheld" -> itemModelGenerator.generateFlatItem(MItem.REGISTERED_MOD_ITEMS.get(mItem), ModelTemplates.FLAT_HANDHELD_ITEM);
                default -> itemModelGenerator.createFlatItemModel(MItem.REGISTERED_MOD_ITEMS.get(mItem), ModelTemplates.FLAT_ITEM);
            }
        }
    }
}
