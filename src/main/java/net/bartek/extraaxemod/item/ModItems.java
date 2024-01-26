package net.bartek.extraaxemod.item;

import net.bartek.extraaxemod.ExtraAxeMod;
import net.bartek.extraaxemod.item.custom.ChargedAxeItem;
import net.bartek.extraaxemod.item.custom.ModToolTiers;
import net.minecraft.world.item.*;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ExtraAxeMod.MOD_ID);

    public static final RegistryObject<Item> SHUNGITE_INGOT = ITEMS.register("shungite_ingot", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_SHUNGITE = ITEMS.register("raw_shungite", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SHUNGITE_AXE = ITEMS.register("shungite_axe",
            () -> new AxeItem(ModToolTiers.SHUNGITE, 7, -3, new Item.Properties().durability(2500)));

    public static final RegistryObject<Item> SHUNGITE_AXE_CHARGED = ITEMS.register("shungite_axe_charged",
            () -> new ChargedAxeItem(ModToolTiers.SHUNGITE, 9, -3, new Item.Properties().durability(2500)));

    public static final RegistryObject<Item> LIGHTNING_RUNE = ITEMS.register("lightning_rune", () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }

}
