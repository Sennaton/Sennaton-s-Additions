
package org.sennaton.sennaton_additions.SennatonMob;

import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.*;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.extensions.IForgeLivingEntity;
import org.sennaton.sennaton_additions.SennatonMob.Dice.*;
import org.sennaton.sennaton_additions.SennatonMob.Spawns.NynaSpawnConditions;
import software.bernie.geckolib.constant.DefaultAnimations;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.GeoEntity;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.network.PlayMessages;
import net.minecraftforge.network.NetworkHooks;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.util.Mth;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.Packet;
import net.minecraft.nbt.CompoundTag;


import javax.annotation.Nullable;

import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.EnumSet;

import static org.sennaton.sennaton_additions.SennatonMob.Spawns.NynaSpawnConditions.BiomeType;


public class NynaEntity extends PathfinderMob implements   RangedAttackMob, GeoEntity {
	public static final EntityDataAccessor<Boolean> SHOOT = SynchedEntityData.defineId(NynaEntity.class, EntityDataSerializers.BOOLEAN);
	public static final EntityDataAccessor<String> ANIMATION = SynchedEntityData.defineId(NynaEntity.class, EntityDataSerializers.STRING);
	public static final EntityDataAccessor<String> TEXTURE = SynchedEntityData.defineId(NynaEntity.class, EntityDataSerializers.STRING);
	private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
	private boolean swinging;
	private boolean lastloop;
	private long lastSwing;
	public String animationprocedure = "empty";
	public String nynatype = "nyna";
	private static final EntityDataAccessor<Integer> DATA_ID_TYPE_VARIANT =
			SynchedEntityData.defineId(NynaEntity.class, EntityDataSerializers.INT);


	@Override
	public double getBoneResetTime() {
		return 4;
	}

	public NynaEntity(PlayMessages.SpawnEntity packet, Level world) {
		this(MobInit.NYNA.get(), world);
	}

	public NynaVariant variant;

	public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_34297_, DifficultyInstance p_34298_, MobSpawnType p_34299_, @Nullable SpawnGroupData p_34300_, @Nullable CompoundTag p_34301_) {
		RandomSource randomsource = p_34297_.getRandom();
		float f = p_34298_.getSpecialMultiplier();
		this.setCanPickUpLoot(randomsource.nextFloat() < 0.55F * f);
		boolean warped = (randomsource.nextInt(100)>75);
		boolean isDark = NynaSpawnConditions.isDark( p_34297_,this.getX(),  this.getY(),  this.getZ());
		switch (BiomeType(p_34297_,this.getX(),  this.getY(),  this.getZ())){
            case "Overworld" -> {
                if (warped || !isDark){
                    this.setVariant(NynaVariant.UN_NYNA);}
                else {
                    this.setVariant(NynaVariant.NYNA);}
            }
            case "Cold/Ocean" ->{
				if (warped || !isDark){
					this.setVariant(NynaVariant.UN_NYNA);}
				else {
					this.setVariant(NynaVariant.FRIGID_NYNA);}
			}
			case "End" ->
					this.setVariant(NynaVariant.UN_NYNA);
			case "Nether" ->
					this.setVariant(NynaVariant.FIREY_NYNA);
			case "Haunting" ->
					this.setVariant(NynaVariant.HAUNTED_NYNA);
		}






		this.populateDefaultEquipmentSlots(randomsource, p_34298_);
			this.populateDefaultEquipmentEnchantments(randomsource, p_34298_);


		if (this.getItemBySlot(EquipmentSlot.HEAD).isEmpty()) {
			LocalDate localdate = LocalDate.now();
			int i = localdate.get(ChronoField.DAY_OF_MONTH);
			int j = localdate.get(ChronoField.MONTH_OF_YEAR);
			if (j == 10 && i == 31 && randomsource.nextFloat() < 0.25F) {
				this.setItemSlot(EquipmentSlot.HEAD, new ItemStack(randomsource.nextFloat() < 0.1F ? Blocks.JACK_O_LANTERN : Blocks.CARVED_PUMPKIN));
				this.armorDropChances[EquipmentSlot.HEAD.getIndex()] = 0.0F;
			}
		}

		return (SpawnGroupData)p_34300_;
	}



	public NynaEntity(EntityType<NynaEntity> type, Level world) {
		super(type, world);
		xpReward = 0;
		setNoAi(false);
		setMaxUpStep(0.6f);
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(SHOOT, false);
		this.entityData.define(ANIMATION, "undefined");
		this.entityData.define(TEXTURE, "nyna");
		this.entityData.define(DATA_ID_TYPE_VARIANT, 0);
	}

	public boolean isInvulnerableTo(DamageSource pSource) {
		return this.isRemoved() || this.isInvulnerable() && !pSource.is(DamageTypeTags.BYPASSES_INVULNERABILITY) && !pSource.isCreativePlayer() || (pSource.is(DamageTypeTags.IS_FIRE) && this.fireytype()) || pSource.is(DamageTypeTags.IS_FALL) && this.getType().is(EntityTypeTags.FALL_DAMAGE_IMMUNE);
	}

	private boolean fireytype() {
		if (this.fireImmune() || variant == NynaVariant.FIREY_NYNA || variant == NynaVariant.HAUNTED_NYNA){
			return true;
		} else
			return  false;
	}



	public void setTexture(String texture) {
		this.entityData.set(TEXTURE, texture);
	}

	public String getTexture() {
		return this.entityData.get(TEXTURE);
	}

	@Override
	public Packet<ClientGamePacketListener> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	@Override
	protected void registerGoals() {

		super.registerGoals();
		this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.3, true) {
			@Override
			protected double getAttackReachSqr(LivingEntity entity) {
				return 2;
			}
		});
		this.goalSelector.addGoal(1, new RangedAttackGoal(this, 1.25, 20, 6f) {
			@Override
			public boolean canContinueToUse() {
				return this.canUse();

			}

		});
		this.goalSelector.addGoal(0, new AvoidEntityGoal<>(this, Creeper.class, 6.0F, 1.0D, 1.2D));
		this.targetSelector.addGoal(3, new HurtByTargetGoal(this, NynaEntity.class).setAlertOthers());
		this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Monster.class, true));
		this.targetSelector.addGoal(4,	new NearestAttackableTargetGoal<>(this, TropicalFish.class, true, false));
		this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Cod.class, true, false));
		this.targetSelector.addGoal(6, new NearestAttackableTargetGoal<>(this, Salmon.class, true, false));
		this.goalSelector.addGoal(7, new OpenDoorGoal(this, true));
		this.goalSelector.addGoal(8, new MoveBackToVillageGoal(this, 0.6, false));
		this.goalSelector.addGoal(9, new RandomStrollGoal(this, 1));
		this.goalSelector.addGoal(10, new RandomLookAroundGoal(this));
		this.goalSelector.addGoal(11, new NynaFloatGoal(this));
	}


	public static class NynaFloatGoal extends FloatGoal {
		private final NynaEntity mob;

		public NynaFloatGoal(NynaEntity mob) {
			super(mob);
            this.mob = mob;
        }

		@Override
		public boolean canUse()
			{if (this.mob.getTarget() != null) {
				return ((this.mob.getTarget().getY()>this.mob.getY()&& this.mob.getVariant() != NynaVariant.FRIGID_NYNA) || this.mob.getAirSupply() < this.mob.getMaxAirSupply() / 2) && (this.mob.isInWater() && this.mob.getFluidHeight(FluidTags.WATER) > this.mob.getFluidJumpThreshold() || this.mob.isInLava() || this.mob.isInFluidType((fluidType, height) -> this.mob.canSwimInFluidType(fluidType) && height > this.mob.getFluidJumpThreshold()));
			} else return (this.mob.getVariant() != NynaVariant.FRIGID_NYNA || this.mob.getAirSupply() < this.mob.getMaxAirSupply() / 2) && (this.mob.isInWater() && this.mob.getFluidHeight(FluidTags.WATER) > this.mob.getFluidJumpThreshold() || this.mob.isInLava() || this.mob.isInFluidType((fluidType, height) -> this.mob.canSwimInFluidType(fluidType) && height > this.mob.getFluidJumpThreshold()));
		}

		@Override
		public boolean canContinueToUse() {
			if (this.mob.getTarget() != null) {
				return ((this.mob.getTarget().getY()>this.mob.getY() && this.mob.getVariant() != NynaVariant.FRIGID_NYNA) || this.mob.getAirSupply() < this.mob.getMaxAirSupply() / 2) && (this.mob.isInWater() && this.mob.getFluidHeight(FluidTags.WATER) > this.mob.getFluidJumpThreshold() || this.mob.isInLava() || this.mob.isInFluidType((fluidType, height) -> this.mob.canSwimInFluidType(fluidType) && height > this.mob.getFluidJumpThreshold()));
			} else return (this.mob.getVariant() != NynaVariant.FRIGID_NYNA || this.mob.getAirSupply() < this.mob.getMaxAirSupply() / 2) && (this.mob.isInWater() && this.mob.getFluidHeight(FluidTags.WATER) > this.mob.getFluidJumpThreshold() || this.mob.isInLava() || this.mob.isInFluidType((fluidType, height) -> this.mob.canSwimInFluidType(fluidType) && height > this.mob.getFluidJumpThreshold()));

		}
	}

	public class RangedAttackGoal extends Goal {
		private final Mob mob;
		private final RangedAttackMob rangedAttackMob;
		@Nullable
		private LivingEntity target;
		private int attackTime = -1;
		private final double speedModifier;
		private int seeTime;
		private final int attackIntervalMin;
		private final int attackIntervalMax;
		private final float attackRadius;
		private final float attackRadiusSqr;

		public RangedAttackGoal(NynaEntity p_25768_, double p_25769_, int p_25770_, float p_25771_) {
			this(p_25768_, p_25769_, p_25770_, p_25770_, p_25771_);
		}

		public RangedAttackGoal(NynaEntity p_25773_, double p_25774_, int p_25775_, int p_25776_, float p_25777_) {
			if (!(p_25773_ instanceof LivingEntity)) {
				throw new IllegalArgumentException("ArrowAttackGoal requires Mob implements RangedAttackMob");
			} else {
				this.rangedAttackMob = p_25773_;
				this.mob = (Mob) p_25773_;
				NynaEntity user = p_25773_;
				this.speedModifier = p_25774_;
				this.attackIntervalMin = p_25775_;
				this.attackIntervalMax = p_25776_;
				this.attackRadius = p_25777_;
				this.attackRadiusSqr = p_25777_ * p_25777_;
				this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
			}
		}

		public boolean canUse() {
			LivingEntity livingentity = this.mob.getTarget();
			NynaEntity user = (NynaEntity) this.mob;
			if (livingentity != null && livingentity.isAlive() && (livingentity.distanceTo(this.mob) > 3 && ((!livingentity.isUnderWater()) || user.getVariant() == NynaVariant.FRIGID_NYNA))) {
				this.target = livingentity;
				return true;
			} else {
				return false;
			}
		}

		public boolean canContinueToUse() {
			return this.canUse() || this.target.isAlive() && !this.mob.getNavigation().isDone();
		}

		public void stop() {
			this.target = null;
			this.seeTime = 0;
			this.attackTime = -1;
			((NynaEntity) rangedAttackMob).entityData.set(SHOOT, false);
		}

		public boolean requiresUpdateEveryTick() {
			return true;
		}

		public void tick() {
			double d0 = this.mob.distanceToSqr(this.target.getX(), this.target.getY(), this.target.getZ());
			boolean flag = this.mob.getSensing().hasLineOfSight(this.target);
			if (flag) {
				++this.seeTime;
			} else {
				this.seeTime = 0;
			}
			if (!(d0 > (double) this.attackRadiusSqr) && this.seeTime >= 5) {
				this.mob.getNavigation().stop();
			} else {
				this.mob.getNavigation().moveTo(this.target, this.speedModifier);
			}
			this.mob.getLookControl().setLookAt(this.target, 30.0F, 30.0F);
			if (--this.attackTime == 0) {
				if (!flag) {
					((NynaEntity) rangedAttackMob).entityData.set(SHOOT, false);
					return;
				}
				((NynaEntity) rangedAttackMob).entityData.set(SHOOT, true);
				float f = (float) Math.sqrt(d0) / this.attackRadius;
				float f1 = Mth.clamp(f, 0.1F, 1.0F);
				this.rangedAttackMob.performRangedAttack(this.target, f1);
				this.attackTime = Mth.floor(f * (float) (this.attackIntervalMax - this.attackIntervalMin) + (float) this.attackIntervalMin);
			} else if (this.attackTime < 0) {
				this.attackTime = Mth.floor(Mth.lerp(Math.sqrt(d0) / (double) this.attackRadius, (double) this.attackIntervalMin, (double) this.attackIntervalMax));
			} else
				((NynaEntity) rangedAttackMob).entityData.set(SHOOT, false);
		}
	}

	@Override
	public MobType getMobType() {
		return MobType.UNDEFINED;
	}

	public SoundEvent getAmbientSound() {
		ResourceLocation sound = null;
		switch (this.getVariant())
		{
			case NYNA, FRIGID_NYNA ->{
				sound =  new ResourceLocation("sennaton_additions", "nyna_mew");}
			case UN_NYNA ->{
				sound = new ResourceLocation("sennaton_additions", "nyna_un_mew");}
			case FIREY_NYNA,HAUNTED_NYNA ->{
				sound = new ResourceLocation("sennaton_additions", "nyna_mews");}
		}
		return ForgeRegistries.SOUND_EVENTS.getValue(sound);
	}

	@Override
	public SoundEvent getHurtSound(DamageSource ds) {
		ResourceLocation sound = null;
		switch (this.getVariant())
		{
			case NYNA, FRIGID_NYNA ->{
				sound =  new ResourceLocation("sennaton_additions", "nyna_hurt");}
			case UN_NYNA ->{
				sound = new ResourceLocation("sennaton_additions", "nyna_un_hurt");}
			case FIREY_NYNA,HAUNTED_NYNA ->{
				sound = new ResourceLocation("sennaton_additions", "nyna_hurts");}
        }
		return ForgeRegistries.SOUND_EVENTS.getValue(sound);
	}

	@Override
	public SoundEvent getDeathSound() {
		ResourceLocation sound = null;
		switch (this.getVariant())
		{
			case NYNA, FRIGID_NYNA ->{
				sound = new ResourceLocation("sennaton_additions", "nyna_death");}
			case UN_NYNA ->{
				sound = new ResourceLocation("sennaton_additions", "nyna_un_death");}
			case FIREY_NYNA,HAUNTED_NYNA ->{
				sound = new ResourceLocation("sennaton_additions", "nyna_deaths");}
		}
		return ForgeRegistries.SOUND_EVENTS.getValue(sound);
	}

	@Override
	public boolean hurt(DamageSource source, float amount) {
		if (source.is(DamageTypes.FALL))
			return false;
		return super.hurt(source, amount);
	}

	@Override
	public void addAdditionalSaveData(CompoundTag tag) {
		super.addAdditionalSaveData(tag);
		tag.putString("variant", this.getVariant().toString());

		//this.entityData.set(DATA_ID_TYPE_VARIANT, tag.getInt("Variant"));
	}

	public boolean doHurtTarget(Entity pEntity) {
		float f = this.level().getCurrentDifficultyAt(this.blockPosition()).getEffectiveDifficulty();
		boolean flag = super.doHurtTarget(pEntity);
		switch(variant){
			case NYNA->
					getTarget();
			case UN_NYNA->
					((LivingEntity)pEntity).addEffect(new MobEffectInstance(MobEffects.LEVITATION, 140 * (int)f), this);
			case FRIGID_NYNA->
				((LivingEntity)pEntity).addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 140 * (int)f), this);
			case FIREY_NYNA->
					pEntity.setSecondsOnFire(2 * (int)f);
			case HAUNTED_NYNA->
					((LivingEntity)pEntity).addEffect(new MobEffectInstance(MobEffects.WITHER, 140 * (int)f), this);
			default->
					getTarget();
		}




		return flag;
	}



	@Override
	public void readAdditionalSaveData(CompoundTag tag) {
		super.readAdditionalSaveData(tag);
		if (tag.getString("variant") != "")
			variant = NynaVariant.get(tag.getString("variant"));
		else variant =NynaVariant.NYNA;
		if (variant != null) {
			this.setVariant(variant);
		} else this.setVariant(NynaVariant.NYNA);
		//this.entityData.set(DATA_ID_TYPE_VARIANT, tag.getInt("Variant"));
	}

	@Override
	public void baseTick() {
		super.baseTick();
		this.refreshDimensions();
	}

	@Override
	public EntityDimensions getDimensions(Pose p_33597_) {
		return super.getDimensions(p_33597_).scale((float) 1);
	}

	@Override
	public void performRangedAttack(LivingEntity target, float flval) {

		switch(variant){
			case NYNA->
				DiceEntity.shoot(this, target, variant.toString());
			case UN_NYNA->
				WarpedDiceEntity.shoot(this, target, variant.toString());
			case FRIGID_NYNA->
				FrozenDiceEntity.shoot(this, target, variant.toString());
			case FIREY_NYNA->
				BurningDiceEntity.shoot(this, target, variant.toString());
			case HAUNTED_NYNA->
				HauntedDiceEntity.shoot(this, target, variant.toString());
			default->
				DiceEntity.shoot(this, target, variant.toString());
		}

	}

	@Override
	public void aiStep() {
		super.aiStep();
		this.updateSwingTime();
	}


	public static AttributeSupplier.Builder createAttributes() {
		AttributeSupplier.Builder builder = LivingEntity.createLivingAttributes();
		builder = builder.add(Attributes.MOVEMENT_SPEED, 0.3);
		builder = builder.add(Attributes.MAX_HEALTH, 20);
		builder = builder.add(Attributes.ARMOR, 0);
		builder = builder.add(Attributes.ATTACK_DAMAGE, 3);
		builder = builder.add(Attributes.FOLLOW_RANGE, 40);
		builder = builder.add(Attributes.ATTACK_KNOCKBACK, 1);
		//builder = builder.add(,0);
		return builder;
	}

	/*private PlayState movementPredicate(AnimationState event) {
			if (event.isMoving())

			 {
				return event.setAndContinue(RawAnimation.begin().thenLoop("animation.nyna.walk"));
			}
			return event.setAndContinue(RawAnimation.begin().thenLoop("animation.nyna.idle"));
	}*/


	@Override
	protected void tickDeath() {
		++this.deathTime;
		if (this.deathTime == 20) {
			this.remove(RemovalReason.KILLED);
			this.dropExperience();
		}
	}

	public String getSyncedAnimation() {
		return this.entityData.get(ANIMATION);
	}

	public void setAnimation(String animation) {
		this.entityData.set(ANIMATION, animation);
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
		controllers.add(DefaultAnimations.genericWalkIdleController(this));
		controllers.add(DefaultAnimations.genericAttackAnimation(this, RawAnimation.begin().thenPlay("nyna.attack")));

		//data.add(new AnimationController<>(this, "movement", 4, this::movementPredicate));
		DefaultAnimations.genericLivingController(this);
	}

	public AnimatableInstanceCache getAnimatableInstanceCache() {
		return this.cache;
	}


	/* VARIANTS */


	public NynaVariant getVariant() {
		return NynaVariant.byId(this.getTypeVariant() & 255);
	}

	private int getTypeVariant() {
		return this.entityData.get(DATA_ID_TYPE_VARIANT);
	}

	private void setVariant(NynaVariant variants) {
		this.entityData.set(DATA_ID_TYPE_VARIANT, variants.getId() & 255);
		variant=variants;
	}

	public static void setVariant(NynaEntity Nyna, NynaVariant Variant) {
		Nyna.entityData.set(DATA_ID_TYPE_VARIANT, Variant.getId() & 255);
		Nyna.variant = Variant;
	}
}
