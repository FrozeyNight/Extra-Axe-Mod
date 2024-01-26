package net.bartek.extraaxemod.event;

import net.bartek.extraaxemod.ExtraAxeMod;
import net.bartek.extraaxemod.effect.ModEffects;
import net.bartek.extraaxemod.item.custom.ChargedAxeItem;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemCooldowns;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = ExtraAxeMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModEvents {
    @SubscribeEvent
    public static void onRightClick(PlayerInteractEvent.RightClickItem event) {
        ItemStack heldItem = event.getItemStack();
        Player player = event.getEntity();

        if (heldItem.getItem() instanceof ChargedAxeItem) {
            ItemCooldowns cooldownTracker = player.getCooldowns();
            if (!cooldownTracker.isOnCooldown(heldItem.getItem())) {
                Level world = player.level();

                // Get the position of the player
                BlockPos playerPos = player.blockPosition();

                // Spawn particles in a radius around the player
                spawnParticles(world, playerPos);
                player.playSound(SoundType.ANVIL.getPlaceSound(), 1 ,0.7F);
                ItemStack heldItemForDurability = player.getItemInHand(player.getUsedItemHand());
                heldItemForDurability.hurtAndBreak(1, player, p -> p.broadcastBreakEvent(player.getUsedItemHand()));

                // Check for nearby entities and make them leap into the air
                List<LivingEntity> nearbyEntities = world.getEntitiesOfClass(LivingEntity.class,
                        new AABB(player.getX() - 5.0, player.getY() - 2.0, player.getZ() - 5.0,
                                player.getX() + 5.0, player.getY() + 2.0, player.getZ() + 5.0));

                for (LivingEntity entity : nearbyEntities) {
                    // Exclude the player from the leap effect
                    if (entity != player) {
                        double leapHeight = 1.0;
                        entity.setDeltaMovement(entity.getDeltaMovement().add(0, leapHeight, 0));
                        applyCustomEffect(entity);
                    }
                }

                // Set the cooldown (in ticks)
                cooldownTracker.addCooldown(heldItem.getItem(), 60);  // 100 ticks = 5 seconds
            }
        }
    }

    private static void spawnParticles(Level world, BlockPos center) {
        double radius = 5.0;

        for (double x = center.getX() - radius; x <= center.getX() + radius; x++) {
            for (double z = center.getZ() - radius; z <= center.getZ() + radius; z++) {
                double distanceSq = (x - center.getX()) * (x - center.getX()) + (z - center.getZ()) * (z - center.getZ());

                if (distanceSq <= radius * radius) {
                    world.addParticle(new BlockParticleOption(ParticleTypes.BLOCK, Blocks.STONE.defaultBlockState()), x + 0.5, center.getY(), z + 0.5, 0.0, 0.0, 0.0);
                }
            }
        }
    }

    private static void applyCustomEffect(LivingEntity entity) {
        MobEffectInstance effectInstance = new MobEffectInstance(ModEffects.LIGHTNING_PRONE.get(), 100, 0);
        entity.addEffect(effectInstance);
    }

    @SubscribeEvent
    public static void onLeftClick(AttackEntityEvent event) {
        Player player = event.getEntity();
        Entity target = event.getTarget();
        ItemStack heldItem = player.getItemInHand(player.getUsedItemHand());

        if (target instanceof LivingEntity && heldItem.getItem() instanceof ChargedAxeItem) {
            ItemCooldowns cooldownTracker = player.getCooldowns();
            if (!cooldownTracker.isOnCooldown(heldItem.getItem())) {
                // Check if the target entity has the custom effect
                LivingEntity livingTarget = (LivingEntity) target;
                Level world = livingTarget.level();
                if (livingTarget.hasEffect(ModEffects.LIGHTNING_PRONE.get())) {
                    double x = livingTarget.getX();
                    double y = livingTarget.getY();
                    double z = livingTarget.getZ();
                    LightningBolt lightningBolt = EntityType.LIGHTNING_BOLT.create(world);
                    assert lightningBolt != null;
                    lightningBolt.moveTo(x, y, z);
                    world.addFreshEntity(lightningBolt);
                    target.hurt(lightningBolt.damageSources().lightningBolt(), 6);
                    ((LivingEntity) target).removeEffect(ModEffects.LIGHTNING_PRONE.get());

                    // Set the cooldown (in ticks)
                    cooldownTracker.addCooldown(heldItem.getItem(), 20);  // 100 ticks = 5 seconds
                }
            }
        }
    }
}
