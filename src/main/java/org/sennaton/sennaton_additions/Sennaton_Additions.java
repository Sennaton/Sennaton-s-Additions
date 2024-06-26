package org.sennaton.sennaton_additions;

import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.sennaton.sennaton_additions.SennatonBlocks.SennatonBlocks;
import org.sennaton.sennaton_additions.SennatonItems.SennatonItems;
import org.sennaton.sennaton_additions.SennatonMob.MobInit;
import org.sennaton.sennaton_additions.SennatonMob.NynaEntity;
import org.sennaton.sennaton_additions.SennatonMob.Spawns.NynaSpawnConditions;
import org.sennaton.sennaton_additions.Sound.Sound;
import org.sennaton.sennaton_additions.client.renderer.dice.DiceRenderer;
import org.sennaton.sennaton_additions.client.renderer.dice.FrozenDiceRenderer;
import org.sennaton.sennaton_additions.client.renderer.dice.WarpedDiceRenderer;
import org.sennaton.sennaton_additions.client.renderer.dice.BurningDiceRenderer;
import org.sennaton.sennaton_additions.client.renderer.dice.HauntedDiceRenderer;
import org.sennaton.sennaton_additions.client.renderer.NynaRenderer;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Sennaton_Additions.MODID)
public class Sennaton_Additions {

    // Define mod id in a common place for everything to reference
    public static final String MODID = "sennaton_additions";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();
    // Create a Deferred Register to hold Blocks which will all be registered under the "sennaton_additions" namespace
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    // Create a Deferred Register to hold Items which will all be registered under the "sennaton_additions" namespace
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    // Create a Deferred Register to hold CreativeModeTabs which will all be registered under the "examplemod" namespace
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);


    public Sennaton_Additions() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        SennatonItems.register(modEventBus);
        SennatonBlocks.register(modEventBus);
        MobInit.REGISTRY.register(modEventBus);
       // SpawnPlacements.register(MobInit.NYNA.get(),
         //       SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
        Sound.register(modEventBus);


        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register the Deferred Register to the mod event bus so blocks get registered
        BLOCKS.register(modEventBus);
        // Register the Deferred Register to the mod event bus so items get registered
        ITEMS.register(modEventBus);
        // Register the Deferred Register to the mod event bus so tabs get registered
        CREATIVE_MODE_TABS.register(modEventBus);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");
        LOGGER.info("DIRT BLOCK >> {}", ForgeRegistries.BLOCKS.getKey(Blocks.DIRT));

        if (Config.logDirtBlock)
            LOGGER.info("DIRT BLOCK >> {}", ForgeRegistries.BLOCKS.getKey(Blocks.DIRT));

        LOGGER.info(Config.magicNumberIntroduction + Config.magicNumber);

        Config.items.forEach((item) -> LOGGER.info("ITEM >> {}", item.toString()));

        SpawnPlacements.register(MobInit.NYNA.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (entityType, world, reason, pos, random) -> {
            int x = pos.getX();
            int y = pos.getY();
            int z = pos.getZ();
            return NynaSpawnConditions.ShouldSpawn(world, x, y, z);
        });

    }

    @SubscribeEvent
    public void onEntityJoinWorld(EntityJoinLevelEvent event) {
        if (event.getEntity() instanceof Creeper ) {
            Creeper entity = (Creeper) event.getEntity(); entity.goalSelector.addGoal(1, new AvoidEntityGoal<>(entity, NynaEntity.class, 6.0F, 1.0D, 1.2D));
        }
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
        if (event.getTabKey() == CreativeModeTabs.COMBAT) {
            event.accept(SennatonItems.CRYSTAL_EDGE);
        }
        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.accept(SennatonItems.WRAPPED_COAL);
        }
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(SennatonItems.SHARPENED_CRYSTAL);
            event.accept(SennatonItems.HARDENED_DIAMOND);
        }
        if (event.getTabKey() == CreativeModeTabs.SPAWN_EGGS) {
            event.accept(SennatonItems.NYNA_SPAWN_EGG);
        }
    }
    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }



    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            EntityRenderers.register(MobInit.NYNA.get(), NynaRenderer::new);
            EntityRenderers.register(MobInit.DICE.get(), DiceRenderer::new);
            EntityRenderers.register(MobInit.FROZEN_DICE.get(), FrozenDiceRenderer::new);
            EntityRenderers.register(MobInit.WARPED_DICE.get(), WarpedDiceRenderer::new);
            EntityRenderers.register(MobInit.BURNING_DICE.get(), BurningDiceRenderer::new);
            EntityRenderers.register(MobInit.HAUNTED_DICE.get(), HauntedDiceRenderer::new);

            // Some client setup code
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        }
    }
}

