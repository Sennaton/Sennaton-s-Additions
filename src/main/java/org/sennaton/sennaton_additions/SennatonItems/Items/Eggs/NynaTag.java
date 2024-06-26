package org.sennaton.sennaton_additions.SennatonItems.Items.Eggs;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.storage.loot.functions.SetNbtFunction;
import net.minecraftforge.common.ForgeSpawnEggItem;

import java.util.function.Supplier;

public class NynaTag extends CompoundTag{

    private NynaTag() {
    }

    public static NynaTag createNynaTag() {
        return new NynaTag();
    }
}
