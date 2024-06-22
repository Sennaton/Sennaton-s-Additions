package org.sennaton.sennaton_additions.SennatonMob.model.Dice;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.sennaton.sennaton_additions.SennatonMob.Dice.BurningDiceEntity;
import org.sennaton.sennaton_additions.Sennaton_Additions;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class BurningDiceModel extends GeoModel<BurningDiceEntity> {
	@Override
	public ResourceLocation getModelResource(BurningDiceEntity animatable) {
		return new ResourceLocation(Sennaton_Additions.MODID, "geo/dice.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(BurningDiceEntity animatable) {
		return new ResourceLocation(Sennaton_Additions.MODID, "textures/entity/nyna/burning_dice.png");
	}

	@Override
	public ResourceLocation getAnimationResource(BurningDiceEntity animatable) {
		return new ResourceLocation(Sennaton_Additions.MODID, "animations/dice.animation.json");
	}

	@Override
	public void setCustomAnimations(BurningDiceEntity animatable, long instanceId, AnimationState<BurningDiceEntity> animationState) {
		CoreGeoBone head = getAnimationProcessor().getBone("head");

		if (head != null) {
			EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

			head.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
			head.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
		}
	}
}