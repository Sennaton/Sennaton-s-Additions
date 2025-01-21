package org.sennaton.sennaton_additions;

import com.mojang.logging.LogUtils;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.kyrptonaught.customportalapi.CustomPortalBlock;
import net.kyrptonaught.customportalapi.api.CustomPortalBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.SpawnData;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;
import org.sennaton.sennaton_additions.SennatonBlocks.SennatonBlocks;
import org.sennaton.sennaton_additions.SennatonItems.SennatonItems;
import org.sennaton.sennaton_additions.SennatonMob.MobInit;
import org.sennaton.sennaton_additions.SennatonMob.Spawns.NynaSpawnConditions;
import org.sennaton.sennaton_additions.Sound.Sound;
//import org.sennaton.sennaton_additions.SennatonMob.
import org.slf4j.Logger;

import static net.minecraft.world.item.CreativeModeTabs.*;

// The value here should match an entry in the META-INF/mods.toml file
//@Mod(Sennaton_Additions.MODID)
public class Sennaton_Additions implements ModInitializer {

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

    public void onInitialize() {
        SennatonItems.initiate();
        SennatonBlocks.initiate();
        MobInit.initiate();
        Sound.initiate();
        creativeModeTabsLoad();
        load();
    }

    public void creativeModeTabsLoad(){
        CreativeTabHelper.register(SennatonItems.crystalEdge, COMBAT);
        CreativeTabHelper.register(SennatonItems.wrappedCoal, TOOLS_AND_UTILITIES);
        CreativeTabHelper.register(SennatonItems.hardenedDiamond, INGREDIENTS);
        CreativeTabHelper.register(SennatonItems.sharpenedCrystal, INGREDIENTS);
        CreativeTabHelper.register(SennatonItems.nynaSpawnEgg, SPAWN_EGGS);
    }

    public void load() {
        LOGGER.info("HELLO FROM COMMON SETUP");
        //LOGGER.info("DIRT BLOCK >> {}", ForgeRegistries.BLOCKS.getKey(Blocks.DIRT));
        CustomPortalBuilder.beginPortal()
                .frameBlock(Blocks.CHISELED_STONE_BRICKS)
                .lightWithItem(BuiltInRegistries.ITEM.get(new ResourceLocation("sennaton_additions","wrapped_coal"))  )
                .destDimID(new ResourceLocation("sennaton_additions","floating_islands"))
                //.tintColor(100,100,255)
                .customPortalBlock((CustomPortalBlock) BuiltInRegistries.BLOCK.get(new ResourceLocation("sennaton_additions","floating_portal")) )
                .registerPortal();

        if (Config.logDirtBlock)
            LOGGER.info("DIRT BLOCK >> {}", BuiltInRegistries.BLOCK.getKey(Blocks.DIRT));

        LOGGER.info(Config.magicNumberIntroduction + Config.magicNumber);

        SpawnPlacements.register(MobInit.NYNA, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, NynaSpawnConditions::ShouldSpawn);
        BiomeModifications.addSpawn(BiomeSelectors.foundInOverworld(), MobCategory.AMBIENT, MobInit.NYNA, 5, 1, 5);
        BiomeModifications.addSpawn(BiomeSelectors.foundInTheEnd(), MobCategory.MONSTER, MobInit.NYNA, 5, 1, 5);
        BiomeModifications.addSpawn(BiomeSelectors.foundInTheNether(), MobCategory.MONSTER, MobInit.NYNA, 1, 1, 1);
    }
}

