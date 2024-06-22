package org.sennaton.sennaton_additions.SennatonMob.Spawns;

import com.sun.jna.WString;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.data.worldgen.biome.EndBiomes;
import net.minecraft.data.worldgen.biome.NetherBiomes;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biome.Precipitation;
import net.minecraft.world.level.biome.Biomes;
import net.minecraftforge.common.Tags;
import net.minecraft.data.worldgen.biome.BiomeData;
import org.sennaton.sennaton_additions.Sennaton_Additions;
import org.sennaton.sennaton_additions.Tags.CoolNether;

import java.lang.reflect.Field;
import java.util.Random;

import static com.ibm.icu.lang.UCharacter.GraphemeClusterBreak.T;

public class NynaSpawnConditions {
	static Biome.Precipitation Precipitation;
	static Random random = new Random();

	public static boolean isCold(LevelAccessor world, double x, double y, double z) {
		BlockPos Pos = new BlockPos(BlockPos.containing(x, y, z));
		Biome biome = world.getBiome(Pos).value();
		Biome.Precipitation precipitation = biome.getPrecipitationAt(Pos);
		return ((precipitation == Biome.Precipitation.SNOW) || (world.getBiome(Pos).is(BiomeTags.IS_OCEAN)));
	}
	public static boolean isDark(LevelAccessor world, double x, double y, double z){
		BlockPos Pos = new BlockPos(BlockPos.containing(x, y, z));
		return (world.getMaxLocalRawBrightness(Pos) < 7);
	}
	public static boolean isNether(LevelAccessor world, double x, double y, double z){
		BlockPos Pos = new BlockPos(BlockPos.containing(x, y, z));
		return (world.getBiome(Pos).is(BiomeTags.IS_NETHER));
	}
	public static boolean isHaunting(LevelAccessor world, double x, double y, double z){
		BlockPos Pos = new BlockPos(BlockPos.containing(x, y, z));
		return (world.getBiome(Pos).is(org.sennaton.sennaton_additions.Tags.CoolNether.IS_COOL_NETHER));
	}
	public static boolean isEnd(LevelAccessor world, double x, double y, double z){
		BlockPos Pos = new BlockPos(BlockPos.containing(x, y, z));
		return (world.getBiome(Pos).is(BiomeTags.IS_END));
	}

	public static String BiomeType(LevelAccessor world, double x, double y, double z){
		String type = "None";
		if (isNether(world, x, y, z)){
			if (isHaunting(world, x, y, z)){
				type = "Haunting";}
			else {
				type = "Nether";}
		}
		else if (isEnd(world, x, y, z)){
			type = "End";}
		else if (isCold(world, x, y, z)){
			type= "Cold/Ocean";}
		else type = "Overworld";
		return type;
	}



	public static boolean ShouldSpawn(LevelAccessor world, double x, double y, double z){
		if (isNether(world, x, y, z) || isDark(world, x, y, z) || isEnd(world, x, y, z)){
			return true;
		} else {
			return (random.nextInt(100) > 75);
		}

	}
}



