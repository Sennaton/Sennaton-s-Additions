package org.sennaton.sennaton_additions.SennatonItems;

import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.sennaton.sennaton_additions.SennatonItems.Items.SharpenedCrystalItem;
import org.sennaton.sennaton_additions.SennatonItems.Items.WrappedCoalItem;
import org.sennaton.sennaton_additions.SennatonMob.MobInit;
import org.sennaton.sennaton_additions.Sennaton_Additions;
import org.sennaton.sennaton_additions.SennatonItems.Items.CrystalEdgeItem;
public class SennatonItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Sennaton_Additions.MODID);

    public static final RegistryObject<Item> CRYSTAL_EDGE = ITEMS.register("crystal_edge", CrystalEdgeItem::new);
    public static final RegistryObject<Item> SHARPENED_CRYSTAL = ITEMS.register("sharpened_crystal", SharpenedCrystalItem::new);
    public static final RegistryObject<Item> HARDENED_DIAMOND = ITEMS.register("hardened_diamond", () -> new Item(new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> WRAPPED_COAL = ITEMS.register("wrapped_coal", WrappedCoalItem::new);
    public static final RegistryObject<Item> NYNA_SPAWN_EGG = ITEMS.register("nyna_spawn_egg",
            () -> new ForgeSpawnEggItem(MobInit.NYNA, 0x000000, 0x860520,
                    new Item.Properties()));
    public static void register(IEventBus eventBus) {ITEMS.register(eventBus);}
}
