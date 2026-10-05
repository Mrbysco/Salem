package com.buuz135.salem.item;

import com.buuz135.salem.util.SalemRaidTier;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.MultimapBuilder;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;

import java.util.function.Consumer;

public class TrinketItem extends SalemItem{

    public static ListMultimap<SalemRaidTier, TrinketItem> TRINKETS = MultimapBuilder.hashKeys().arrayListValues().build();

    private final SalemRaidTier tier;

    public TrinketItem(Properties properties, Rarity rarity, SalemRaidTier tier) {
        super(properties.stacksTo(1).rarity(rarity));
        this.tier = tier;
        TRINKETS.put(tier, this);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
        super.appendHoverText(itemStack, context, display, builder, tooltipFlag);
        builder.accept(Component.translatable(getDescriptionId()+".tooltip").withStyle(ChatFormatting.GRAY));
    }

}
