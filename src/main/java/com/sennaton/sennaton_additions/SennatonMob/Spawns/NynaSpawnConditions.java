package com.sennaton.sennaton_additions.SennatonMob.Spawns;

import com.sennaton.sennaton_additions.Tags.CoolNether;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BiomeTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;

import java.util.Random;

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
		return (world.getBiome(Pos).is(CoolNether.IS_COOL_NETHER));
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



	public static boolean ShouldSpawn(EntityType<? extends LivingEntity> Nyna, ServerLevelAccessor world, MobSpawnType spawnType, BlockPos pos, RandomSource random){
		if (isNether(world, pos.getX(), pos.getY(), pos.getZ()) || isDark(world, pos.getX(), pos.getY(), pos.getZ()) || isEnd(world, pos.getX(), pos.getY(), pos.getZ())){
			return true;
		} else {
			return (random.nextInt(100) > 75);
		}

	}
}



