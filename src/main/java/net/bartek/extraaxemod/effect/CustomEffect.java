package net.bartek.extraaxemod.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;

public class CustomEffect extends MobEffect {
    public CustomEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        // Implement the effect's behavior here
        // This method is called every tick the effect is active on the entity
        System.out.println("CustomEffect is active on entity: " + entity.getName().getString());
    }

    // Additional customization can be done here if needed
}