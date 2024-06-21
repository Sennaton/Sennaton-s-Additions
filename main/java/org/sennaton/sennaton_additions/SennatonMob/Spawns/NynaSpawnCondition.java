package org.sennaton.sennaton_additions.SennatonMob.Spawns;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class NynaSpawnCondition {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
		if (world.getBiome(BlockPos.containing(x, y, z)).value().getBaseTemperature() < 0.05) {
			return false;
		}
		return world.getMaxLocalRawBrightness(BlockPos.containing(x, y, z)) < 1;
	}
}

