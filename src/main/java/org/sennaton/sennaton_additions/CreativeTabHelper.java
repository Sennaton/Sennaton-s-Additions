package org.sennaton.sennaton_additions;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;

//import static net.minecraft.world.item.CreativeModeTabs.BUILDING_BLOCKS;

public class CreativeTabHelper {

     private static void addi(FabricItemGroupEntries entries, ItemStack item){
          entries.accept(item);
    }

    public static void register(Item item, ResourceKey<CreativeModeTab> tab){
        ItemGroupEvents.modifyEntriesEvent(tab).register((s) -> {addi(s, new ItemStack(item));});
            //content.accept(SennatonItems.crystalEdge);
    }
}
