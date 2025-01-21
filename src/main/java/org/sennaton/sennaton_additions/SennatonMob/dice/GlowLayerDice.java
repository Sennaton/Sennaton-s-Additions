
package org.sennaton.sennaton_additions.SennatonMob.dice;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import org.sennaton.sennaton_additions.SennatonMob.Dice.LoadedDiceEntity;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.cache.texture.AutoGlowingTexture;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer;

public class GlowLayerDice<T extends LoadedDiceEntity> extends AutoGlowingGeoLayer<T> {

    public GlowLayerDice(GeoRenderer<T> renderer, ResourceLocation Resource) {
        super(renderer);

    }


    @Override
    public void render(PoseStack poseStack, T animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        ResourceLocation Resource = renderer.getTextureLocation(animatable);
        RenderType emissiveRenderType = getRenderType(animatable, Resource);

        getRenderer().reRender(bakedModel, poseStack, bufferSource, animatable, emissiveRenderType,
                bufferSource.getBuffer(emissiveRenderType), partialTick, 15728640, OverlayTexture.NO_OVERLAY,
                1, 1, 1, 1);
    }




    public RenderType getRenderType(T animatable, ResourceLocation Resource){
        return RenderType.eyes(  AutoGlowingTexture.appendToPath(renderer.getTextureLocation(animatable), "_glowmask"));
    }

}