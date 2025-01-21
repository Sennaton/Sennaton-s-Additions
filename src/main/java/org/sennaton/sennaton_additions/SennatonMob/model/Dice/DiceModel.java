package org.sennaton.sennaton_additions.SennatonMob.model.Dice;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.sennaton.sennaton_additions.SennatonMob.Dice.DiceEntity;
import org.sennaton.sennaton_additions.Sennaton_Additions_Client;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class DiceModel extends GeoModel<DiceEntity> {
	@Override
	public ResourceLocation getModelResource(DiceEntity animatable) {
		return new ResourceLocation(Sennaton_Additions_Client.MODID, "geo/dice.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(DiceEntity animatable) {
		return new ResourceLocation(Sennaton_Additions_Client.MODID, "textures/entity/nyna/dice.png");
	}

	@Override
	public ResourceLocation getAnimationResource(DiceEntity animatable) {
		return new ResourceLocation(Sennaton_Additions_Client.MODID, "animations/dice.animation.json");
	}

	@Override
	public void setCustomAnimations(DiceEntity animatable, long instanceId, AnimationState<DiceEntity> animationState) {
		CoreGeoBone head = getAnimationProcessor().getBone("head");

		if (head != null) {
			EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

			head.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
			head.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
		}
	}
}