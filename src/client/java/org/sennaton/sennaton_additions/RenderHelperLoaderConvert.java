package org.sennaton.sennaton_additions;

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.Block;

public class RenderHelperLoaderConvert {
    //<E extends Entity> public static void register(EntityType<Entity> type, EntityRenderer<Entity> renderer) {
    //    net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry.INSTANCE.register(type,renderer);
    //}

    //Fabric Version
    public static void register(EntityType type, EntityRendererProvider renderer) {
        EntityRendererRegistry.register(type,renderer);
    }

    public static <B extends Block> void registerBlock(B block, RenderType renderType) {
        BlockRenderLayerMap.INSTANCE.putBlock(block,renderType);
    }
}
