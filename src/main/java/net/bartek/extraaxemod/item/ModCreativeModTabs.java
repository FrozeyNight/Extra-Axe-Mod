package net.bartek.extraaxemod.item;

import net.bartek.extraaxemod.ExtraAxeMod;
import net.bartek.extraaxemod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ExtraAxeMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> TUTORIAL_TAB = CREATIVE_MODE_TABS.register("extraaxemod_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.SHUNGITE_INGOT.get()))
                    .title(Component.translatable("creativetab.extraaxemod_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        //pOutput.accept(ModItems.SAPPHIRE.get());
                        pOutput.accept(ModItems.SHUNGITE_INGOT.get());
                        pOutput.accept(ModItems.RAW_SHUNGITE.get());
                        pOutput.accept(ModItems.SHUNGITE_AXE.get());
                        pOutput.accept(ModBlocks.SHUNGITE_ORE.get());
                        pOutput.accept(ModItems.SHUNGITE_AXE_CHARGED.get());
                        pOutput.accept(ModBlocks.RUNE_INFUSION_STATION.get());
                        pOutput.accept(ModItems.LIGHTNING_RUNE.get());
                    })
                    .build());
    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
