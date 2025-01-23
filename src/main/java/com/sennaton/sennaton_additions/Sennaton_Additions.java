package com.sennaton.sennaton_additions;

import com.mojang.logging.LogUtils;
//import net.fabricmc.api.ModInitializer;
//import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
//import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import com.sennaton.sennaton_additions.world.PortalLoad;
import net.kyrptonaught.customportalapi.CustomPortalBlock;
import net.kyrptonaught.customportalapi.api.CustomPortalBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
//import org.intellij.lang.annotations.Identifier;
import com.sennaton.sennaton_additions.SennatonBlocks.SennatonBlocks;
import com.sennaton.sennaton_additions.SennatonItems.SennatonItems;
import com.sennaton.sennaton_additions.SennatonMob.MobInit;
import com.sennaton.sennaton_additions.SennatonMob.Spawns.NynaSpawnConditions;
import com.sennaton.sennaton_additions.Sound.Sound;
//import org.sennaton.sennaton_additions.SennatonMob.
import org.slf4j.Logger;

import static net.minecraft.world.item.CreativeModeTabs.*;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Sennaton_Additions.MODID)
//@Mod.EventBusSubscriber
public class Sennaton_Additions /*implements ModInitializer*/ {

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
    public static DeferredRegister<Block> PORTALBLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);

    public static RegistryObject<CustomPortalBlock> PORTAL_BLOCK = PORTALBLOCKS.register("floating_portal", () -> new CustomPortalBlock(BlockBehaviour.Properties.of().noCollission().randomTicks().pushReaction(PushReaction.BLOCK).strength(-1.0F).sound(SoundType.GLASS).lightLevel(s -> 11).noLootTable()));

    public static IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
    public Sennaton_Additions() {
        //MinecraftForge.EVENT_BUS.addListener(this::commonSetup);
        SennatonItems.initiate(modEventBus);
        SennatonBlocks.initiate(modEventBus);
        PortalLoad.initiate(modEventBus);
        MobInit.initiate(modEventBus);
        Sound.initiate();
        creativeModeTabsLoad();
        //load();
        MinecraftForge.EVENT_BUS.register(this);


        //PORTALBLOCKS.register(modEventBus);
        }

    //public void portalLoad(){

    //}

    public void creativeModeTabsLoad(){
        CreativeTabHelper.register(SennatonItems.crystalEdge, COMBAT);
        CreativeTabHelper.register(SennatonItems.wrappedCoal, TOOLS_AND_UTILITIES);
        CreativeTabHelper.register(SennatonItems.hardenedDiamond, INGREDIENTS);
        CreativeTabHelper.register(SennatonItems.sharpenedCrystal, INGREDIENTS);
        CreativeTabHelper.register(SennatonItems.nynaSpawnEgg, SPAWN_EGGS);
    }

   // public void load() {
        //LOGGER.info("HELLO FROM COMMON SETUP");

        //if (Config.logDirtBlock)
        //    LOGGER.info("DIRT BLOCK >> {}", BuiltInRegistries.BLOCK.getKey(Blocks.DIRT));

        //LOGGER.info(Config.magicNumberIntroduction + Config.magicNumber);

        //BiomeModifications.addSpawn(BiomeSelectors.foundInOverworld(), MobCategory.AMBIENT, MobInit.NYNA, 5, 1, 5);
        //BiomeModifications.addSpawn(BiomeSelectors.foundInTheEnd(), MobCategory.AMBIENT, MobInit.NYNA, 5, 1, 5);
        //BiomeModifications.addSpawn(BiomeSelectors.foundInTheNether(), MobCategory.AMBIENT, MobInit.NYNA, 25, 1, 5);

    //}
    @SubscribeEvent
    public static void commonSetup(final FMLCommonSetupEvent event){

        SpawnPlacements.register(MobInit.get(MobInit.NYNA), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, NynaSpawnConditions::ShouldSpawn);
        LOGGER.info("Test");

        //Sennaton_Additions_Client.RenderLoad();
        }


    //@SubscribeEvent
    //public static void commonSetup(final FMLLoadCompleteEvent event){
   //      }

    public static void onClientSetup(FMLClientSetupEvent event) {
        //Sennaton_Additions_Client();
    }
}

