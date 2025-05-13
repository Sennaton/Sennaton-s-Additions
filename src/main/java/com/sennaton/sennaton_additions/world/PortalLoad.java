package com.sennaton.sennaton_additions.world;

import com.mojang.logging.LogUtils;
import net.kyrptonaught.customportalapi.CustomPortalBlock;
import net.kyrptonaught.customportalapi.api.CustomPortalBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class PortalLoad {
    public PortalLoad(){}
    public static void initiate(IEventBus eventBus){}

    @SubscribeEvent
    public static void portalLoader(FMLCommonSetupEvent event) {
        CustomPortalBuilder.beginPortal()
                .frameBlock(Blocks.CHISELED_STONE_BRICKS)
                .lightWithItem(ForgeRegistries.ITEMS.getValue(new ResourceLocation("sennaton_additions","wrapped_coal")))
                //.lightWithWater()
                .destDimID(new ResourceLocation("sennaton_additions","floating_islands"))
                //.tintColor(100,100,255)
                .customPortalBlock((CustomPortalBlock) BuiltInRegistries.BLOCK.get(new ResourceLocation("sennaton_additions","floating_portal")))
                //.flatPortal(false)
                .registerPortal();
        LogUtils.getLogger().info("Portal Setup Complete");
    }
}
