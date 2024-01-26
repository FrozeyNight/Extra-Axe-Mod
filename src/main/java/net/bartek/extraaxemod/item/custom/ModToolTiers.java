package net.bartek.extraaxemod.item.custom;

import net.bartek.extraaxemod.ExtraAxeMod;
import net.bartek.extraaxemod.item.ModItems;
import net.bartek.extraaxemod.util.ModTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;

import java.util.List;

public class ModToolTiers {
    public static final Tier SHUNGITE = TierSortingRegistry.registerTier(
            new ForgeTier(5, 1500, 5f, 4f, 25,
                ModTags.Blocks.NEEDS_SHUNGITE_TOOL, () -> Ingredient.of(ModItems.SHUNGITE_INGOT.get())),
            new ResourceLocation(ExtraAxeMod.MOD_ID, "shungite"), List.of(Tiers.NETHERITE), List.of());
}
