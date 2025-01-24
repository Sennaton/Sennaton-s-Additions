package com.sennaton.sennaton_additions;

import com.mojang.logging.LogUtils;
import com.sennaton.sennaton_additions.SennatonMob.dice.*;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.renderer.RenderType;
import com.sennaton.sennaton_additions.SennatonBlocks.SennatonBlocks;
import com.sennaton.sennaton_additions.SennatonMob.dice.*;
import com.sennaton.sennaton_additions.SennatonMob.MobInit;
import com.sennaton.sennaton_additions.SennatonMob.NynaRenderer;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods_client.toml file
//@Mod(Sennaton_Additions.MODID)
public class Sennaton_Additions_Client implements ClientModInitializer {

    // Define mod id in a common place for everything to reference
    public static final String MODID = "sennaton_additions";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();
    // Create a Deferred Register to hold Blocks which will all be registered under the "sennaton_additions" namespace
    //public static final Block BLOCKS = Registries.create(ForgeRegistries.BLOCKS, MODID);
    // Create a Deferred Register to hold Items which will all be registered under the "sennaton_additions" namespace
    //public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    // Create a Deferred Register to hold CreativeModeTabs which will all be registered under the "examplemod" namespace
    //public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public void onInitializeClient() {
        RenderLoad();
    }

    public void RenderLoad(){
        RenderHelperLoaderConvert.register(MobInit.NYNA, NynaRenderer::new);
        RenderHelperLoaderConvert.register(MobInit.DICE, DiceRenderer::new);
        RenderHelperLoaderConvert.register(MobInit.FROZEN_DICE, FrozenDiceRenderer::new);
        RenderHelperLoaderConvert.register(MobInit.BURNING_DICE, BurningDiceRenderer::new);
        RenderHelperLoaderConvert.register(MobInit.HAUNTED_DICE, HauntedDiceRenderer::new);
        RenderHelperLoaderConvert.register(MobInit.WARPED_DICE, WarpedDiceRenderer::new);
        RenderHelperLoaderConvert.registerBlock(SennatonBlocks.floatingPortal, RenderType.translucent());
        //EntityRendererRegistry.register()
    }
}

