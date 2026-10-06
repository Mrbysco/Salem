package com.buuz135.salem.data;

import com.buuz135.salem.SalemContent;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Items;

import java.util.concurrent.CompletableFuture;

public class SalemRecipeProvider extends RecipeProvider {
    public SalemRecipeProvider(HolderLookup.Provider provider, RecipeOutput recipeOutput) {
        super(provider, recipeOutput);
    }

    @Override
    protected void buildRecipes() {
        shaped(RecipeCategory.MISC, SalemContent.COMMON_RAID_SUMMONER)
                .pattern("BAB")
                .pattern("ACA")
                .pattern("BAB")
                .define('B', Items.BLAZE_ROD)
                .define('C', Items.IRON_INGOT)
                .define('A', Items.AMETHYST_SHARD)
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                .unlockedBy("has_blaze_rod", has(Items.BLAZE_ROD))
                .save(this.output);
        shaped(RecipeCategory.MISC, SalemContent.EPIC_RAID_SUMMONER)
                .pattern("BAB")
                .pattern("ACA")
                .pattern("BAB")
                .define('B', Items.BLAZE_ROD)
                .define('C', Items.DIAMOND)
                .define('A', Items.AMETHYST_SHARD)
                .unlockedBy("has_diamond", has(Items.DIAMOND))
                .unlockedBy("has_blaze_rod", has(Items.BLAZE_ROD))
                .save(this.output);
        shaped(RecipeCategory.MISC, SalemContent.LEGENDARY_RAID_SUMMONER)
                .pattern("BAB")
                .pattern("ACA")
                .pattern("BAB")
                .define('B', Items.BLAZE_ROD)
                .define('C', Items.NETHERITE_INGOT)
                .define('A', Items.AMETHYST_SHARD)
                .unlockedBy("has_netherite_ingot", has(Items.NETHERITE_INGOT))
                .unlockedBy("has_blaze_rod", has(Items.BLAZE_ROD))
                .save(this.output);
        shaped(RecipeCategory.MISC, SalemContent.RARE_RAID_SUMMONER)
                .pattern("BAB")
                .pattern("ACA")
                .pattern("BAB")
                .define('B', Items.BLAZE_ROD)
                .define('C', Items.GOLD_INGOT)
                .define('A', Items.AMETHYST_SHARD)
                .unlockedBy("has_gold_ingot", has(Items.GOLD_INGOT))
                .unlockedBy("has_blaze_rod", has(Items.BLAZE_ROD))
                .save(this.output);
    }

    public static class Runner extends RecipeProvider.Runner {
        public Runner(PackOutput output, CompletableFuture<HolderLookup.Provider> completableFuture) {
            super(output, completableFuture);
        }

        @Override
        protected RecipeProvider createRecipeProvider(HolderLookup.Provider provider, RecipeOutput recipeOutput) {
            return new SalemRecipeProvider(provider, recipeOutput);
        }

        @Override
        public String getName() {
            return "Salem Recipes";
        }
    }
}
