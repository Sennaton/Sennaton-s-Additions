package com.sennaton.sennaton_additions.SennatonMob.model;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import com.sennaton.sennaton_additions.SennatonMob.NynaEntity;
import com.sennaton.sennaton_additions.Sennaton_Additions_Client;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

import static com.sennaton.sennaton_additions.SennatonMob.NynaRenderer.LOCATION_BY_VARIANT;


public class NynaModel extends GeoModel<NynaEntity> {
	@Override
	public ResourceLocation getModelResource(NynaEntity animatable) {
		return new ResourceLocation(Sennaton_Additions_Client.MODID, "geo/nyna.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(NynaEntity animatable) {
		return LOCATION_BY_VARIANT.get(animatable.getVariant());
	}

	@Override
	public ResourceLocation getAnimationResource(NynaEntity animatable) {
		return new ResourceLocation(Sennaton_Additions_Client.MODID, "animations/nyna.animation.json");
	}

	@Override
	public void setCustomAnimations(NynaEntity animatable, long instanceId, AnimationState<NynaEntity> animationState) {
		CoreGeoBone head = getAnimationProcessor().getBone("Head");

		if (head != null) {
			EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

			head.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
			head.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
		}
	}
}