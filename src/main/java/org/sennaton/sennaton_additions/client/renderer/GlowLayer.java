
package org.sennaton.sennaton_additions.client.renderer;

import net.minecraft.client.renderer.RenderType;
import org.sennaton.sennaton_additions.SennatonMob.NynaEntity;
import org.sennaton.sennaton_additions.SennatonMob.model.NynaModel;
import software.bernie.geckolib.cache.texture.AutoGlowingTexture;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer;

import static com.ibm.icu.lang.UCharacter.GraphemeClusterBreak.T;

public class GlowLayer extends AutoGlowingGeoLayer<NynaEntity>{

    public GlowLayer(GeoRenderer<NynaEntity> renderer) {
        super(renderer);
    }

        @Override
        public RenderType getRenderType(NynaEntity animatable){
               return RenderType.eyes(  AutoGlowingTexture.appendToPath(NynaRenderer.LOCATION_BY_VARIANT.get(animatable.getVariant()), "_glowmask"));
        }

}