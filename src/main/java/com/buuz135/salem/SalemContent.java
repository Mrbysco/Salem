package com.buuz135.salem;

import com.buuz135.salem.item.BoneShieldItem;
import com.buuz135.salem.item.ChillingAuraItem;
import com.buuz135.salem.item.DeathlyChargersFeetItem;
import com.buuz135.salem.item.EternalFeastItem;
import com.buuz135.salem.item.NightmarishWingsItem;
import com.buuz135.salem.item.ScorchingAuraItem;
import com.buuz135.salem.item.SummonerItem;
import com.buuz135.salem.item.TomeOfTheDamnedItem;
import com.buuz135.salem.item.TrinketItem;
import com.buuz135.salem.item.UnhallowedCrossItem;
import com.buuz135.salem.item.WitheringTouchItem;
import com.buuz135.salem.mob_effects.SalemMobEffects;
import com.buuz135.salem.util.SalemRaidTier;
import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class SalemContent {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Salem.MODID);


    public static DeferredItem<Item> DEATHLY_CHARGERS_FEET = ITEMS.registerItem("deathly_chargers_feet", DeathlyChargersFeetItem::new);
    public static DeferredItem<Item> BONE_SHIELD_HANDS = ITEMS.registerItem("bone_shield_hand", BoneShieldItem::new);
    public static DeferredItem<Item> CHILLING_AURA_NECKLACE = ITEMS.registerItem("chilling_aura_necklace", ChillingAuraItem::new);
    public static DeferredItem<Item> ETERNAL_FEAST_BELT = ITEMS.registerItem("eternal_feast_belt", EternalFeastItem::new);
    public static DeferredItem<Item> NIGHTMARISH_WINGS_BACK = ITEMS.registerItem("nightmarish_wings_back", NightmarishWingsItem::new);
    public static DeferredItem<Item> SCORCHING_AURA_RING = ITEMS.registerItem("scorching_aura_ring", ScorchingAuraItem::new);
    public static DeferredItem<Item> HELLISH_BARGAIN_RING = ITEMS.registerItem("hellish_bargain_ring", properties -> new TrinketItem(properties, Rarity.UNCOMMON, SalemRaidTier.RARE));
    public static DeferredItem<Item> TOME_OF_THE_DAMNED_CHARM = ITEMS.registerItem("tome_of_the_damned_charm", TomeOfTheDamnedItem::new);
    public static DeferredItem<Item> UNHALLOWED_CROSS = ITEMS.registerItem("unhallowed_cross_charm", UnhallowedCrossItem::new);
    public static DeferredItem<Item> WITHERING_TOUCH_HAND = ITEMS.registerItem("withering_touch_hand", WitheringTouchItem::new);
    public static DeferredItem<Item> COMMON_RAID_SUMMONER = ITEMS.registerItem("common_raid_summoner", properties -> new SummonerItem(properties, Rarity.COMMON, SalemRaidTier.COMMON));
    public static DeferredItem<Item> RARE_RAID_SUMMONER = ITEMS.registerItem("rare_raid_summoner", properties -> new SummonerItem(properties, Rarity.UNCOMMON, SalemRaidTier.RARE));
    public static DeferredItem<Item> EPIC_RAID_SUMMONER = ITEMS.registerItem("epic_raid_summoner", properties -> new SummonerItem(properties, Rarity.RARE, SalemRaidTier.EPIC));
    public static DeferredItem<Item> LEGENDARY_RAID_SUMMONER = ITEMS.registerItem("legendary_raid_summoner", properties -> new SummonerItem(properties, Rarity.EPIC, SalemRaidTier.LEGENDARY));

    public static class DataComp{

        public static final DeferredRegister<DataComponentType<?>> DATA_COMPONENTS = DeferredRegister.create(Registries.DATA_COMPONENT_TYPE, Salem.MODID);


        public static Supplier<DataComponentType<Long>> TIME = register("time", () -> -1L, op -> op.persistent(Codec.LONG));
        public static Supplier<DataComponentType<Integer>> AMOUNT = register("amount", () -> 0, op -> op.persistent(Codec.INT));

    }

    public static class Effect {

        public static final DeferredRegister<MobEffect> EFFECT = DeferredRegister.create(Registries.MOB_EFFECT, Salem.MODID);
        public static Identifier ENLARGE_ATTRIBUTE = Identifier.fromNamespaceAndPath(Salem.MODID, "enlarge");

        public static DeferredHolder<MobEffect, MobEffect> ENLARGE_EFFECT = EFFECT.register("enlarge", () -> new SalemMobEffects(MobEffectCategory.BENEFICIAL, 3402751)
                .addAttributeModifier(
                        Attributes.SCALE, ENLARGE_ATTRIBUTE, 0.2F, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
                ));
        public static DeferredHolder<MobEffect, MobEffect> SPAWN_EFFECT = EFFECT.register("spawn", () -> new SalemMobEffects(MobEffectCategory.BENEFICIAL, 3402751));
    }


    private static <T> ComponentSupplier<T> register(String name, Supplier<T> defaultVal, UnaryOperator<DataComponentType.Builder<T>> op) {
        var registered = DataComp.DATA_COMPONENTS.register(name, () -> op.apply(DataComponentType.builder()).build());
        return new ComponentSupplier<>(registered, defaultVal);
    }

    public static class ComponentSupplier<T> implements Supplier<DataComponentType<T>> {
        private final Supplier<DataComponentType<T>> type;
        private final Supplier<T> defaultSupplier;

        public ComponentSupplier(Supplier<DataComponentType<T>> type, Supplier<T> defaultSupplier) {
            this.type = type;
            this.defaultSupplier = Suppliers.memoize(defaultSupplier::get);
        }

        public T get(ItemStack stack) {
            return stack.getOrDefault(type, defaultSupplier.get());
        }

        @Override
        public DataComponentType<T> get() {
            return type.get();
        }
    }

}
