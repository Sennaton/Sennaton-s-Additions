package com.sennaton.sennaton_additions.SennatonItems;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SpawnEggItem;
import com.sennaton.sennaton_additions.SennatonItems.Items.CrystalEdgeItem;
import com.sennaton.sennaton_additions.SennatonItems.Items.SharpenedCrystalItem;
import com.sennaton.sennaton_additions.SennatonItems.Items.WrappedCoalItem;
import com.sennaton.sennaton_additions.SennatonMob.MobInit;

public class SennatonItems {

    public static Item crystalEdge = new CrystalEdgeItem();
    public static Item sharpenedCrystal = new SharpenedCrystalItem();
    public static Item hardenedDiamond = new Item(new Item.Properties().fireResistant());
    public static Item wrappedCoal = new WrappedCoalItem();
    public static Item nynaSpawnEgg = new SpawnEggItem(MobInit.NYNA, 0x000000, 0x860520, new Item.Properties());


    public static void initiate() {
        register("crystal_edge", crystalEdge);
        register("sharpened_crystal", sharpenedCrystal);
        register("hardened_diamond", hardenedDiamond);
        register("wrapped_coal", wrappedCoal);
        register("nyna_spawn_egg", nynaSpawnEgg);
    }
    public static <T extends Item> Item register(String path, T item) {
       return Items.registerItem(new ResourceLocation("sennaton_additions:" + path), item);
    }

    public static Item get(Item item){
        return item;
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
