
package org.sennaton.sennaton_additions.client.renderer.dice;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.sennaton.sennaton_additions.SennatonMob.Dice.FrozenDiceEntity;
import org.sennaton.sennaton_additions.SennatonMob.model.Dice.FrozenDiceModel;
import org.sennaton.sennaton_additions.Sennaton_Additions;
import software.bernie.geckolib.renderer.GeoEntityRenderer;


public class FrozenDiceRenderer extends GeoEntityRenderer<FrozenDiceEntity> {


    public FrozenDiceRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new FrozenDiceModel());
    }

    @Override
    public ResourceLocation getTextureLocation(FrozenDiceEntity animatable) {
        return new ResourceLocation(Sennaton_Additions.MODID, "textures/entity/nyna/frozen_dice.png");
    }

    @Override
    public void render(FrozenDiceEntity entity, float entityYaw, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight) {

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }

}

