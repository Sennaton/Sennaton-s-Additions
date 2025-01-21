package org.sennaton.sennaton_additions.SennatonBlocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.NetherPortalBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.PushReaction;

public class SennatonBlocks {

    //public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, Sennaton_Additions.MODID);
    public static final Block floatingPortal = new FloatingPortalBlock(BlockBehaviour.Properties.of().noCollission().randomTicks().pushReaction(PushReaction.BLOCK).strength(-1.0F).sound(SoundType.GLASS).lightLevel(s -> 11).noLootTable());
    public static void initiate(){
    register("floating_portal", floatingPortal);
    }

    public static <T extends Block> Block register(String path, T block) {
        return Blocks.register("sennaton_additions:" + path, block);
    }
    //public static void register(IEventBus modEventBus) {REGISTRY.register(modEventBus);}
}
