package org.sennaton.sennaton_additions.SennatonItems;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.MinecartItem;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
//import org.sennaton.sennaton_additions.SennatonItems.Items.Eggs.NynaEgg;
import org.sennaton.sennaton_additions.SennatonItems.Items.SharpenedCrystalItem;
import org.sennaton.sennaton_additions.SennatonItems.Items.WrappedCoalItem;
import org.sennaton.sennaton_additions.SennatonMob.MobInit;
import org.sennaton.sennaton_additions.SennatonMob.NynaEntity;
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
                    new Item.Properties().stacksTo(16)));
   /* public static final RegistryObject<Item> FRIGID_NYNA_SPAWN_EGG = ITEMS.register("frigid_nyna_spawn_egg",
            () -> new NynaEgg(MobInit.NYNA, 0x000000, 0x860520,
                    new Item.Properties().stacksTo(16), taggo(2)));
*/    public static void register(IEventBus eventBus) {ITEMS.register(eventBus);}


    public static CompoundTag taggo(int Variant)

    {
        CompoundTag Tag = new CompoundTag();
        Tag.putString("id", "nyna");
        switch(Variant) {
            case 1 ->
                    Tag.putString("variant", "NYNA");
            case 2 ->
                    Tag.putString("variant", "FRIGID_NYNA");
            case 3 ->
                    Tag.putString("variant", "UN_NYNA");
            case 4 ->
                    Tag.putString("variant", "FIREY_NYNA");
            case 5 ->
                    Tag.putString("variant", "HAUNTED_NYNA");
        }
        return Tag;
    }
}
