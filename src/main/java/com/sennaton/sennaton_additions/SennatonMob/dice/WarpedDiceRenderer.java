
package com.sennaton.sennaton_additions.SennatonMob.dice;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import com.sennaton.sennaton_additions.SennatonMob.Dice.WarpedDiceEntity;
import com.sennaton.sennaton_additions.SennatonMob.model.Dice.WarpedDiceModel;
import com.sennaton.sennaton_additions.Sennaton_Additions_Client;
import software.bernie.geckolib.renderer.GeoEntityRenderer;


public class WarpedDiceRenderer extends GeoEntityRenderer<WarpedDiceEntity> {


    public WarpedDiceRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new WarpedDiceModel());
        addRenderLayer(new GlowLayerDice(this, new ResourceLocation(Sennaton_Additions_Client.MODID, "textures/entity/nyna/warped_dice.png")));
    }

    @Override
    public ResourceLocation getTextureLocation(WarpedDiceEntity animatable) {
        return new ResourceLocation(Sennaton_Additions_Client.MODID, "textures/entity/nyna/warped_dice.png");
    }

    @Override
    public void render(WarpedDiceEntity entity, float entityYaw, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight) {

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }

}

