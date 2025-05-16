package com.sennaton.sennaton_additions.SennatonMob.Dice;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.PlayMessages;
import com.sennaton.sennaton_additions.SennatonMob.MobInit;

import java.util.Objects;

public class FrozenDiceEntity extends LoadedDiceEntity {

    public FrozenDiceEntity(PlayMessages.SpawnEntity packet, Level world) {
        super(MobInit.get(MobInit.FROZEN_DICE), world);
    }
    public FrozenDiceEntity(EntityType type, Level world) {
        super(type, world);
    }

    public FrozenDiceEntity(EntityType<? extends FrozenDiceEntity> type, double x, double y, double z, Level world) {
        super(type, x, y, z, world);
    }

    public FrozenDiceEntity(EntityType type, LivingEntity entity, Level world) {
        super(type, entity, world);
    }
    public static FrozenDiceEntity shoot(Level world, LivingEntity entity, RandomSource random, float power, double damage, int knockback) {
        FrozenDiceEntity entityarrow = new FrozenDiceEntity(MobInit.get(MobInit.FROZEN_DICE), entity, world);
        entityarrow.shoot(entity.getViewVector(1).x, entity.getViewVector(1).y, entity.getViewVector(1).z, power * 2, 0);
        entityarrow.setSilent(true);
        entityarrow.setCritArrow(critTest());
        entityarrow.setBaseDamage(damage);
        entityarrow.setKnockback(knockback);
        world.addFreshEntity(entityarrow);
        world.playSound(null, entity.getX(), entity.getY(), entity.getZ(), Objects.requireNonNull(BuiltInRegistries.SOUND_EVENT.get(new ResourceLocation("entity.arrow.shoot"))), SoundSource.PLAYERS, 1, 1f / (random.nextFloat() * 0.5f + 1) + (power / 2));
        return entityarrow;
    }
    public static FrozenDiceEntity shoot(Level world, LivingEntity entity, RandomSource source) {
        return shoot(world, entity, source, 0.5f, 5, 2);
    }

    public static FrozenDiceEntity shoot(LivingEntity entity, LivingEntity target, String variantP) {
        //Sennaton_Additions.LOGGER.info(variantP.toString()+" FrozenDice");


        FrozenDiceEntity entityarrow = new FrozenDiceEntity(MobInit.get(MobInit.FROZEN_DICE)  , entity, entity.level());

        double dx = target.getX() - entity.getX();
        double dy = target.getY() + target.getEyeHeight() - 1.1;
        double dz = target.getZ() - entity.getZ();
        entityarrow.shoot(dx, dy - entityarrow.getY() + Math.hypot(dx, dz) * 0.2F, dz, 0.5f * 2, 12.0F);
        entityarrow.setSilent(true);
        entityarrow.setBaseDamage(10);
        entityarrow.setKnockback(2);
        entityarrow.setCritArrow(critTest());
        entity.level().addFreshEntity(entityarrow);
        entity.level().playSound(null, entity.getX(), entity.getY(), entity.getZ(), Objects.requireNonNull(BuiltInRegistries.SOUND_EVENT.get(new ResourceLocation("entity.arrow.shoot"))), SoundSource.PLAYERS, 1, 1f / (RandomSource.create().nextFloat() * 0.5f + 1));
        return entityarrow;
    }

    @Override
    protected float getWaterInertia() {
        return 1F;
    }

    @Override
    protected void doPostHurtEffects(LivingEntity pLiving) {
        super.doPostHurtEffects(pLiving);
        Entity entity = this.getEffectSource();
        pLiving.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 140));
    }

}
