package net.bartek.extraaxemod.datagen;

import net.bartek.extraaxemod.ExtraAxeMod;
import net.bartek.extraaxemod.block.ModBlocks;
import net.bartek.extraaxemod.item.ModItems;
import net.bartek.extraaxemod.util.ModTags;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    private static final List<ItemLike> SHUNGITE_SMELTABLES = List.of(ModItems.RAW_SHUNGITE.get(),
            ModBlocks.SHUNGITE_ORE.get());

    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
        oreSmelting(pWriter, SHUNGITE_SMELTABLES, RecipeCategory.MISC, ModItems.SHUNGITE_INGOT.get(), 0.25f, 200, "shungite_ingot");
        oreBlasting(pWriter, SHUNGITE_SMELTABLES, RecipeCategory.MISC, ModItems.SHUNGITE_INGOT.get(), 0.25f, 100, "shungite_ingot");


        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.SHUNGITE_AXE.get())
                .pattern(" SS")
                .pattern(" TS")
                .pattern(" N ")
                .define('S', ModItems.SHUNGITE_INGOT.get())
                .define('T', Items.STICK)
                .define('N', Items.NETHERITE_INGOT)
                .unlockedBy(getHasName(ModItems.SHUNGITE_INGOT.get()), has(ModItems.SHUNGITE_INGOT.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.RUNE_INFUSION_STATION.get())
                .pattern("GNG")
                .pattern("IRI")
                .pattern("T T")
                .define('R', ModItems.LIGHTNING_RUNE.get())
                .define('T', Items.STICK)
                .define('I', Items.IRON_BLOCK)
                .define('G', Items.GLASS)
                .define('N', Items.NETHER_STAR)
                .unlockedBy(getHasName(ModItems.LIGHTNING_RUNE.get()), has(ModItems.LIGHTNING_RUNE.get()))
                .save(pWriter);
    }

    protected static void oreSmelting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.SMELTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.BLASTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static void oreCooking(Consumer<FinishedRecipe> pFinishedRecipeConsumer, RecipeSerializer<? extends AbstractCookingRecipe> pCookingSerializer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer)
                    .group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(pFinishedRecipeConsumer, ExtraAxeMod.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }

    }
}
