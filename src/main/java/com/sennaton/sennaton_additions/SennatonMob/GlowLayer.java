
package com.sennaton.sennaton_additions.SennatonMob;

import net.minecraft.client.renderer.RenderType;
import software.bernie.geckolib.cache.texture.AutoGlowingTexture;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer;

public class GlowLayer extends AutoGlowingGeoLayer<NynaEntity>{

    public GlowLayer(GeoRenderer<NynaEntity> renderer) {
        super(renderer);
    }

        @Override
        public RenderType getRenderType(NynaEntity animatable){
               return RenderType.eyes(  AutoGlowingTexture.appendToPath(NynaRenderer.LOCATION_BY_VARIANT.get(animatable.getVariant()), "_glowmask"));
        }

}