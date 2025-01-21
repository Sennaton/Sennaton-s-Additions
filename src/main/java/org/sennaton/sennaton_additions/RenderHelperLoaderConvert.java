package org.sennaton.sennaton_additions;

//import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
//import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderBuffers;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.entity.Display;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import javax.swing.*;

public class RenderHelperLoaderConvert {
    //<E extends Entity> public static void register(EntityType<Entity> type, EntityRenderer<Entity> renderer) {
    //    net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry.INSTANCE.register(type,renderer);
    //}

    //Forge Version
    public static void register(EntityType type, EntityRendererProvider renderer) {
        EntityRenderers.register(type,renderer);
    }

    public static <B extends Block> void registerBlock(RegistryObject<B> block, RenderType renderType) {
        ItemBlockRenderTypes.setRenderLayer(block.get(),renderType);
    }
}
