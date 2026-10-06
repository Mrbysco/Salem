package com.buuz135.salem.data;

import com.buuz135.salem.SalemContent;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.data.PackOutput;

public class SalemModelProvider extends ModelProvider {

    public SalemModelProvider(PackOutput output) {
        super(output, "salem");
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        itemModels.generateFlatItem(SalemContent.DEATHLY_CHARGERS_FEET.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(SalemContent.BONE_SHIELD_HANDS.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(SalemContent.CHILLING_AURA_NECKLACE.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(SalemContent.ETERNAL_FEAST_BELT.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(SalemContent.NIGHTMARISH_WINGS_BACK.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(SalemContent.SCORCHING_AURA_RING.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(SalemContent.HELLISH_BARGAIN_RING.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(SalemContent.TOME_OF_THE_DAMNED_CHARM.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(SalemContent.UNHALLOWED_CROSS.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(SalemContent.WITHERING_TOUCH_HAND.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(SalemContent.COMMON_RAID_SUMMONER.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(SalemContent.RARE_RAID_SUMMONER.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(SalemContent.EPIC_RAID_SUMMONER.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(SalemContent.LEGENDARY_RAID_SUMMONER.get(), ModelTemplates.FLAT_ITEM);
    }
}
