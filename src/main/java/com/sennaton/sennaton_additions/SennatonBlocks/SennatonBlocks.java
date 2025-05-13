package com.sennaton.sennaton_additions.SennatonBlocks;

import net.kyrptonaught.customportalapi.CustomPortalBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import com.sennaton.sennaton_additions.Sennaton_Additions;

public class SennatonBlocks {

    public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, Sennaton_Additions.MODID);
    //public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, Sennaton_Additions.MODID);
    public static final RegistryObject<CustomPortalBlock> floatingPortal = REGISTRY.register("floating_portal", ()-> new FloatingPortalBlock(BlockBehaviour.Properties.of().noCollission().randomTicks().pushReaction(PushReaction.BLOCK).strength(-1.0F).sound(SoundType.GLASS).lightLevel(s -> 11).noLootTable()));
    //public static void initiate(){
    //register("floating_portal", floatingPortal);
    //}

    public static void initiate(IEventBus modEventBus) {
        REGISTRY.register(modEventBus);
    }

    public static <B extends Block> Block get(RegistryObject<B> block){
        return block.get();
    }

    public static <B extends Block> RegistryObject<Block> get(String name, B block){
       return REGISTRY.register(name,()-> block);
    }
    //public static void register(IEventBus modEventBus) {REGISTRY.register(modEventBus);}
}
