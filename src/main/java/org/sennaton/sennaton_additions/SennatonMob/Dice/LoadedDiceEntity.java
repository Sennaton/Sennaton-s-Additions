
package org.sennaton.sennaton_additions.SennatonMob.Dice;

import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.network.PlayMessages;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;


import org.sennaton.sennaton_additions.Sennaton_Additions;
import software.bernie.geckolib.util.GeckoLibUtil;

import software.bernie.geckolib.core.object.PlayState;
import net.minecraft.world.level.block.Blocks;
import software.bernie.geckolib.core.animation.RawAnimation;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.core.animation.AnimationState;
import net.minecraft.world.item.ItemStack;
import software.bernie.geckolib.core.animation.AnimationController;
import net.minecraft.world.entity.projectile.ItemSupplier;
import software.bernie.geckolib.core.animation.AnimatableManager;
import net.minecraft.world.entity.projectile.AbstractArrow;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.util.RandomSource;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.Packet;

import org.sennaton.sennaton_additions.SennatonMob.MobInit;
import software.bernie.geckolib.core.animatable.GeoAnimatable;

import java.util.Collection;


@OnlyIn(value = Dist.CLIENT, _interface = ItemSupplier.class)
public class LoadedDiceEntity extends AbstractArrow implements GeoAnimatable {
	public static final EntityDataAccessor<String> ANIMATION = SynchedEntityData.defineId(LoadedDiceEntity.class, EntityDataSerializers.STRING);
	public static final EntityDataAccessor<String> TEXTURE = SynchedEntityData.defineId(LoadedDiceEntity.class, EntityDataSerializers.STRING);
	private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
	protected IntOpenHashSet piercingIgnoreEntityIds;
	protected Collection<Entity> piercedAndKilledEntities;
	protected SoundEvent soundEvent;
	private boolean swinging;
	private boolean lastloop;
	private long lastSwing;
	public String animationprocedure = "empty";
	public static final ItemStack PROJECTILE_ITEM = new ItemStack(Blocks.BARRIER);
	/*private static final EntityDataAccessor<String> DATA_ID_TYPE_VARIANT =
			SynchedEntityData.defineId(LoadedDiceEntity.class, EntityDataSerializers.STRING);
 */
	//public String variantP = DATA_ID_TYPE_VARIANT.toString();

	/*public LoadedDiceEntity(PlayMessages.SpawnEntity packet, Level world) {
		super(MobInit.LOADED_DICE.get(), world);
	}*/

	public LoadedDiceEntity(EntityType<? extends LoadedDiceEntity> type, Level world) {
		super(type, world);
	}

	public LoadedDiceEntity(EntityType<? extends LoadedDiceEntity> type, double x, double y, double z, Level world) {
		super(type, x, y, z, world);
	}




	/*@Override
	protected void defineSynchedData() {
		this.entityData.define(DATA_ID_TYPE_VARIANT, "NYNA");
	}

	@Override
	public void addAdditionalSaveData(CompoundTag tag) {
		super.addAdditionalSaveData(tag);
		tag.putString("variant", this.getVariant().toString());

		//this.entityData.set(DATA_ID_TYPE_VARIANT, tag.getInt("Variant"));
	}

	@Override
	public void readAdditionalSaveData(CompoundTag tag) {
		super.readAdditionalSaveData(tag);
		variantP = (tag.getString("variant"));
		if (variantP != null) {
			this.setVariant(variantP);
		}
		//this.entityData.set(DATA_ID_TYPE_VARIANT, tag.getInt("Variant"));
	}
*/
	public LoadedDiceEntity(EntityType<? extends LoadedDiceEntity> type, LivingEntity entity, Level world) {
		super(type, entity, world);
	}

	@Override
	public Packet<ClientGamePacketListener> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}




	@Override
	protected ItemStack getPickupItem() {
		return PROJECTILE_ITEM;
	}

	@Override
	protected void doPostHurtEffects(LivingEntity entity) {
		super.doPostHurtEffects(entity);
		entity.setArrowCount(entity.getArrowCount() - 1);
	}

	@Override
	public void tick() {
		super.tick();
		if (this.inGround)
			this.discard();
	}







	public static AttributeSupplier.Builder createAttributes() {
		AttributeSupplier.Builder builder = Mob.createMobAttributes();
		builder = builder.add(Attributes.MOVEMENT_SPEED, 0.3);
		builder = builder.add(Attributes.MAX_HEALTH, 20);
		builder = builder.add(Attributes.ARMOR, 0);
		builder = builder.add(Attributes.ATTACK_DAMAGE, 3);
		builder = builder.add(Attributes.FOLLOW_RANGE, 16);
		builder = builder.add(Attributes.ATTACK_KNOCKBACK, 1);
		return builder;
	}

	private PlayState movementPredicate(AnimationState event) {
		if (this.animationprocedure.equals("empty")) {
			if ((event.isMoving() || !(event.getLimbSwingAmount() > -0.15F && event.getLimbSwingAmount() < 0.15F))

			) {
				return event.setAndContinue(RawAnimation.begin().thenLoop("animation.loaded_dice.walk"));
			}
			return event.setAndContinue(RawAnimation.begin().thenLoop("animation.loaded_dice.idle"));
		}
		return PlayState.STOP;
	}

	private PlayState procedurePredicate(AnimationState event) {
		if (!animationprocedure.equals("empty") && event.getController().getAnimationState() == AnimationController.State.STOPPED) {
			event.getController().setAnimation(RawAnimation.begin().thenPlay(this.animationprocedure));
			if (event.getController().getAnimationState() == AnimationController.State.STOPPED) {
				this.animationprocedure = "empty";
				event.getController().forceAnimationReset();
			}
		} else if (animationprocedure.equals("empty")) {
			return PlayState.STOP;
		}
		return PlayState.CONTINUE;
	}
	
	public String getSyncedAnimation() {
		return this.entityData.get(ANIMATION);
	}

	public void setAnimation(String animation) {
		this.entityData.set(ANIMATION, animation);
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar data) {
		data.add(new AnimationController<>(this, "movement", 4, this::movementPredicate));
		data.add(new AnimationController<>(this, "procedure", 4, this::procedurePredicate));
	}

	@Override
	public AnimatableInstanceCache getAnimatableInstanceCache() {
		return this.cache;
	}

	@Override
	public double getTick(Object o) {
		return 0;
	}




	/* VARIANTS */


	/*public String getVariant() {
		return variantP;
	}

	private int getTypeVariant() {
		return this.entityData.get(DATA_ID_TYPE_VARIANT);
	}

	private void setVariant(String variant) {
		variantP = variant;

	}*/
}
