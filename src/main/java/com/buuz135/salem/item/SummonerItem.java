package com.buuz135.salem.item;

import com.buuz135.salem.util.BlockUtil;
import com.buuz135.salem.util.SalemRaidTier;
import com.buuz135.salem.world.SalemRaidSavedData;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;

import java.util.function.Consumer;

public class SummonerItem extends SalemItem{

    private final SalemRaidTier tier;

    public SummonerItem(Properties properties, Rarity rarity, SalemRaidTier tier) {
        super(properties.rarity(rarity).stacksTo(1));
        this.tier = tier;
    }

    @Override
    public void appendHoverText(ItemStack itemStack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
        super.appendHoverText(itemStack, context, display, builder, tooltipFlag);
        builder.accept(Component.translatable("tooltip.salem.only_night").withStyle(ChatFormatting.GRAY));
        builder.accept(Component.literal(""));
        builder.accept(Component.translatable("tooltip.salem.can_reward").withStyle(ChatFormatting.GRAY));
        for (TrinketItem trinketItem : TrinketItem.TRINKETS.get(this.tier)) {
            builder.accept(Component.literal("- ").append(trinketItem.getName(new ItemStack(trinketItem))).withStyle(ChatFormatting.GRAY));
        }
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand usedHand) {
        if (level.isDarkOutside()){
            ItemStack itemstack = player.getItemInHand(usedHand);
            player.startUsingItem(usedHand);
            return InteractionResult.CONSUME;
        }
        if (level.getLevelData().getGameTime() < 13000) player.playSound(SoundEvents.FIRE_EXTINGUISH, 1, 1);
        return super.use(level, player, usedHand);
    }

    @Override
    public ItemUseAnimation getUseAnimation(ItemStack stack) {
        return ItemUseAnimation.BRUSH;
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return 20*2;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
        if (level instanceof ServerLevel serverLevel) {
            SalemRaidSavedData.getData(serverLevel).startRaid(BlockUtil.getRandomSurfaceNearby(serverLevel, livingEntity.blockPosition(), 16), tier);
        }
        return ItemStack.EMPTY;
    }
}
