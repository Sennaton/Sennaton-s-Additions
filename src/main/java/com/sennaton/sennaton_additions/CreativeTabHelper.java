package com.sennaton.sennaton_additions;

//import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
//import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;

//import static net.minecraft.world.item.CreativeModeTabs.BUILDING_BLOCKS;
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class CreativeTabHelper {
    private static ArrayList<pair> list = new ArrayList<>();

     //private static void addi(ForgeItemGroupEntries entries, ItemStack item){
          //entries.accept(item);
    //}

    public static void register(RegistryObject<Item> item, ResourceKey<CreativeModeTab> tab){
        //Forge
        list.add(new pair(item,tab));
    }

    @SubscribeEvent
    public static void rList(BuildCreativeModeTabContentsEvent event) {
        for (int i = 0; i < list.size(); i++) {
            pair p = list.get(i);
            if (event.getTabKey() == p.tab){
                event.accept(p.item);
            };
        }
    }

    private record pair(RegistryObject<Item> item, ResourceKey<CreativeModeTab> tab){

    }
}
