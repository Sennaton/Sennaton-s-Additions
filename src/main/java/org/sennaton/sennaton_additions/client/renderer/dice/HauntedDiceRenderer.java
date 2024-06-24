
package org.sennaton.sennaton_additions.client.renderer.dice;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.sennaton.sennaton_additions.SennatonMob.Dice.HauntedDiceEntity;
import org.sennaton.sennaton_additions.SennatonMob.model.Dice.HauntedDiceModel;
import org.sennaton.sennaton_additions.Sennaton_Additions;
import software.bernie.geckolib.renderer.GeoEntityRenderer;


public class HauntedDiceRenderer extends GeoEntityRenderer<HauntedDiceEntity> {


    public HauntedDiceRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new HauntedDiceModel());
        addRenderLayer(new GlowLayerDice(this, new ResourceLocation(Sennaton_Additions.MODID, "textures/entity/nyna/haunted_dice.png")));
    }

    @Override
    public ResourceLocation getTextureLocation(HauntedDiceEntity animatable) {
        return new ResourceLocation(Sennaton_Additions.MODID, "textures/entity/nyna/haunted_dice.png");
    }

    @Override
    public void render(HauntedDiceEntity entity, float entityYaw, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight) {

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }

}

