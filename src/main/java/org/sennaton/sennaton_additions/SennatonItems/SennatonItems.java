package org.sennaton.sennaton_additions.SennatonItems;

//import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.EventBus;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.sennaton.sennaton_additions.SennatonItems.Items.CrystalEdgeItem;
import org.sennaton.sennaton_additions.SennatonItems.Items.Eggs.NynaEgg;
import org.sennaton.sennaton_additions.SennatonItems.Items.SharpenedCrystalItem;
import org.sennaton.sennaton_additions.SennatonItems.Items.WrappedCoalItem;
import org.sennaton.sennaton_additions.SennatonMob.MobInit;
import org.sennaton.sennaton_additions.Sennaton_Additions;

import java.util.function.Supplier;

public class SennatonItems {
    //public static final DeferredRegister<Item> ITEM = DeferredRegister.create(ForgeRegistries.ITEMS, Sennaton_Additions.MODID);
    //public static RegistryObject<Item> THIS;
    static IEventBus modEventBus = Sennaton_Additions.modEventBus;

    public static DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Sennaton_Additions.MODID);
    public static final RegistryObject<Item> crystalEdge = ITEMS.register("crystal_edge", () -> new CrystalEdgeItem());
    public static final RegistryObject<Item> sharpenedCrystal = ITEMS.register("sharpened_crystal", () -> new SharpenedCrystalItem());
    public static final RegistryObject<Item> hardenedDiamond = ITEMS.register("hardened_diamond", () -> new Item(new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> wrappedCoal = ITEMS.register("wrapped_coal", () -> new WrappedCoalItem());
    public static final RegistryObject<Item> nynaSpawnEgg = ITEMS.register("nyna_spawn_egg", () -> new NynaEgg(MobInit.NYNA, 0x000000, 0x860520, new Item.Properties()));

    public static void initiate(IEventBus eventBus) {ITEMS.register(eventBus);}

    public static Item get(RegistryObject<Item> item){
        return item.get();
    }

    public static CompoundTag taggo(int Variant)

    {
        CompoundTag Tag = new CompoundTag();
        //Tag.putString("id", "nyna");
        switch(Variant) {
            case 1 ->
                    Tag.putString("EntityTags", "{variant:\"NYNA\"}");
            case 2 ->
                    Tag.putString("EntityTags", "UN_NYNA\"}");
            case 4 ->
                    Tag.putString("EntityTags", "{variant:\"FIREY_NYNA\"}");
            case 5 ->
                    Tag.putString("variant", "{variant:\"HAUNTED_NYNA\"}");
        }
        return Tag;
    }
}
