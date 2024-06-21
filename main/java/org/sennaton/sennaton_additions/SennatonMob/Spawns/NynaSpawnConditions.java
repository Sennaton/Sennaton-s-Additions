package org.sennaton.sennaton_additions.SennatonMob.Spawns;

import net.minecraft.data.worldgen.biome.NetherBiomes;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

import java.util.Random;

import static com.ibm.icu.lang.UCharacter.GraphemeClusterBreak.T;

public class NynaSpawnConditions {
	static Random random = new Random();
	public static boolean isCold(LevelAccessor world, double x, double y, double z) {
		return (world.getBiome(BlockPos.containing(x, y, z)).value().getBaseTemperature() < 0.05);

	}
	public static boolean isDark(LevelAccessor world, double x, double y, double z){
		return (world.getMaxLocalRawBrightness(BlockPos.containing(x, y, z)) < 1);
	}
	public static boolean isNether(LevelAccessor world, double x, double y, double z){
		return false;//(world.getBiome(BlockPos.containing(x, y, z)).getClass().isAssignableFrom(NetherBiomes)  );
	}
	public static boolean isHaunting(LevelAccessor world, double x, double y, double z){
		return false;//(world.getBiome(BlockPos.containing(x, y, z)).getClass().isAssignableFrom(NetherBiomes)  );
	}
	public static boolean ShouldSpawn(LevelAccessor world, double x, double y, double z){
		if (isNether(world, x, y, z) || isDark(world, x, y, z)){
			return true;
		} else {
			return (random.nextInt(100) > 75);
		}

	}
}



