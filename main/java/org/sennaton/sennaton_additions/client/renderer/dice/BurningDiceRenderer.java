
package org.sennaton.sennaton_additions.client.renderer.dice;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.sennaton.sennaton_additions.SennatonMob.Dice.BurningDiceEntity;
import org.sennaton.sennaton_additions.SennatonMob.model.Dice.BurningDiceModel;
import org.sennaton.sennaton_additions.Sennaton_Additions;
import software.bernie.geckolib.renderer.GeoEntityRenderer;


public class BurningDiceRenderer extends GeoEntityRenderer<BurningDiceEntity> {


    public BurningDiceRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new BurningDiceModel());
    }

    @Override
    public ResourceLocation getTextureLocation(BurningDiceEntity animatable) {
        return new ResourceLocation(Sennaton_Additions.MODID, "textures/entity/nyna/burning_dice.png");
    }

    @Override
    public void render(BurningDiceEntity entity, float entityYaw, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight) {

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }

}

