package net.bartek.extraaxemod.block.entity;

import net.bartek.extraaxemod.ExtraAxeMod;
import net.bartek.extraaxemod.block.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, ExtraAxeMod.MOD_ID);

    public static final RegistryObject<BlockEntityType<RuneInfiusionStationBlockEntity>> RUNE_INFUSING_BE = BLOCK_ENTITIES.register("rune_infusing_be", () ->
            BlockEntityType.Builder.of(RuneInfiusionStationBlockEntity::new, ModBlocks.RUNE_INFUSION_STATION.get()).build(null));

    public static void register(IEventBus eventBus){
        BLOCK_ENTITIES.register(eventBus);
    }
}
