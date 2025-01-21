
package org.sennaton.sennaton_additions.SennatonMob.dice;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.sennaton.sennaton_additions.SennatonMob.Dice.DiceEntity;
import org.sennaton.sennaton_additions.SennatonMob.model.Dice.DiceModel;
import org.sennaton.sennaton_additions.Sennaton_Additions_Client;
import software.bernie.geckolib.renderer.GeoEntityRenderer;


public class DiceRenderer extends GeoEntityRenderer<DiceEntity> {


	public DiceRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager, new DiceModel());
		addRenderLayer(new GlowLayerDice(this, new ResourceLocation(Sennaton_Additions_Client.MODID, "textures/entity/nyna/dice.png")));
	}

	@Override
	public ResourceLocation getTextureLocation(DiceEntity animatable) {
		return new ResourceLocation(Sennaton_Additions_Client.MODID, "textures/entity/nyna/dice.png");
	}

	@Override
	public void render(DiceEntity entity, float entityYaw, float partialTick, PoseStack poseStack,
					   MultiBufferSource bufferSource, int packedLight) {

		super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
	}

	}

