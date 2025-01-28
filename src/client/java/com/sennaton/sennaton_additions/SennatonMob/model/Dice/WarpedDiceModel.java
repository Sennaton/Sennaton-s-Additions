package com.sennaton.sennaton_additions.SennatonMob.model.Dice;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import com.sennaton.sennaton_additions.SennatonMob.Dice.WarpedDiceEntity;
import com.sennaton.sennaton_additions.Sennaton_Additions_Client;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class WarpedDiceModel extends GeoModel<WarpedDiceEntity> {
	@Override
	public ResourceLocation getModelResource(WarpedDiceEntity animatable) {
		return new ResourceLocation(Sennaton_Additions_Client.MODID, "geo/dice.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(WarpedDiceEntity animatable) {
		return new ResourceLocation(Sennaton_Additions_Client.MODID, "textures/entity/warped_dice.png");
	}

	@Override
	public ResourceLocation getAnimationResource(WarpedDiceEntity animatable) {
		return new ResourceLocation(Sennaton_Additions_Client.MODID, "animations/dice.animation.json");
	}

	@Override
	public void setCustomAnimations(WarpedDiceEntity animatable, long instanceId, AnimationState<WarpedDiceEntity> animationState) {
		CoreGeoBone head = getAnimationProcessor().getBone("head");

		if (head != null) {
			EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

			head.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
			head.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
		}
	}
}