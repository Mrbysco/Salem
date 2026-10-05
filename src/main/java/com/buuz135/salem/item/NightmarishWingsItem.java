package com.buuz135.salem.item;

import com.buuz135.salem.util.SalemRaidTier;
import net.minecraft.core.component.DataComponents;
import net.minecraft.util.Unit;
import net.minecraft.world.item.Rarity;

public class NightmarishWingsItem extends TrinketItem{

    public NightmarishWingsItem(Properties properties) {
        super(properties.component(DataComponents.GLIDER, Unit.INSTANCE), Rarity.RARE, SalemRaidTier.EPIC);
    }

}
