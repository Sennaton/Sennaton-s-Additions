package org.sennaton.sennaton_additions.SennatonItems.Items.Eggs;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraftforge.common.ForgeSpawnEggItem;

import java.util.Optional;
import java.util.function.Supplier;

public class NynaEgg extends ForgeSpawnEggItem {

    public NynaEgg(Supplier<? extends EntityType<? extends Mob>> type, int backgroundColor, int highlightColor, Properties props, Optional<CompoundTag> tag) {
        super(type, backgroundColor, highlightColor, props);
    }
    public NynaEgg(Supplier<? extends EntityType<? extends Mob>> type, int backgroundColor, int highlightColor, Properties props) {
        super(type, backgroundColor, highlightColor, props);
    }
}

