package net.bartek.extraaxemod.effect;

import net.bartek.extraaxemod.ExtraAxeMod;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEffects {

    public static final DeferredRegister<MobEffect> MOB_EFFECTS
            = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, ExtraAxeMod.MOD_ID);


    public static final RegistryObject<MobEffect> LIGHTNING_PRONE = MOB_EFFECTS.register("lightning_prone",
            () -> new CustomEffect(MobEffectCategory.HARMFUL, 1268330));

    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }

}
