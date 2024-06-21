package org.sennaton.sennaton_additions.SennatonMob.Dice;

import com.google.common.collect.Lists;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.game.ClientboundGameEventPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Endermite;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.PlayMessages;
import net.minecraftforge.registries.ForgeRegistries;
import org.sennaton.sennaton_additions.SennatonMob.MobInit;
import org.sennaton.sennaton_additions.Sennaton_Additions;

import java.util.Arrays;

public class WarpedDiceEntity extends LoadedDiceEntity {

    public WarpedDiceEntity(PlayMessages.SpawnEntity packet, Level world) {
        super(MobInit.WARPED_DICE.get(), world);
    }
    public WarpedDiceEntity(EntityType<? extends WarpedDiceEntity> type, Level world) {
        super(type, world);
    }

    public WarpedDiceEntity(EntityType<? extends WarpedDiceEntity> type, double x, double y, double z, Level world) {
        super(type, x, y, z, world);
    }

    public WarpedDiceEntity(EntityType<? extends LoadedDiceEntity> type, LivingEntity entity, Level world) {
        super(type, entity, world);
    }
    public static WarpedDiceEntity shoot(Level world, LivingEntity entity, RandomSource random, float power, double damage, int knockback) {
        WarpedDiceEntity entityarrow = new WarpedDiceEntity(MobInit.WARPED_DICE.get(), entity, world);
        entityarrow.shoot(entity.getViewVector(1).x, entity.getViewVector(1).y, entity.getViewVector(1).z, power * 2, 0);
        entityarrow.setSilent(true);
        entityarrow.setCritArrow(true);
        entityarrow.setBaseDamage(damage);
        entityarrow.setKnockback(knockback);
        world.addFreshEntity(entityarrow);
        world.playSound(null, entity.getX(), entity.getY(), entity.getZ(), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.arrow.shoot")), SoundSource.PLAYERS, 1, 1f / (random.nextFloat() * 0.5f + 1) + (power / 2));
        return entityarrow;
    }
    public static WarpedDiceEntity shoot(Level world, LivingEntity entity, RandomSource source) {
        return shoot(world, entity, source, 0.5f, 10, 2);
    }

    public static WarpedDiceEntity shoot(LivingEntity entity, LivingEntity target, String variantP) {
        Sennaton_Additions.LOGGER.info(variantP.toString()+" WarpedDice");

        WarpedDiceEntity entityarrow = new WarpedDiceEntity(MobInit.WARPED_DICE.get(), entity, entity.level());
        entityarrow.setOwner(entity);
        double dx = target.getX() - entity.getX();
        double dy = target.getY() + target.getEyeHeight() - 1.1;
        double dz = target.getZ() - entity.getZ();
        entityarrow.shoot(dx, dy - entityarrow.getY() + Math.hypot(dx, dz) * 0.2F, dz, 0.5f * 2, 12.0F);
        entityarrow.setSilent(true);
        entityarrow.setBaseDamage(10);
        entityarrow.setKnockback(2);
        entityarrow.setCritArrow(true);
        entity.level().addFreshEntity(entityarrow);
        entity.level().playSound(null, entity.getX(), entity.getY(), entity.getZ(), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.arrow.shoot")), SoundSource.PLAYERS, 1, 1f / (RandomSource.create().nextFloat() * 0.5f + 1));
        return entityarrow;
    }

    @Override
    protected void doPostHurtEffects(LivingEntity pLiving) {
        super.doPostHurtEffects(pLiving);
        Entity entity = this.getEffectSource();
            pLiving.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 140));
    }

    protected void onHit(HitResult pResult) {
        super.onHit(pResult);

        for(int i = 0; i < 32; ++i) {
            this.level().addParticle(ParticleTypes.PORTAL, this.getX(), this.getY() + this.random.nextDouble() * 2.0D, this.getZ(), this.random.nextGaussian(), 0.0D, this.random.nextGaussian());
        }

            Entity entity = this.getOwner();
            if (entity != null){

                entity.teleportTo(this.getX(), this.getY(), this.getZ());
                entity.resetFallDistance();
            }

            this.discard();


    }

}
