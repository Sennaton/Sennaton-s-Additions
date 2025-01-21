package org.sennaton.sennaton_additions.SennatonBlocks;


import net.kyrptonaught.customportalapi.CustomPortalBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.PushReaction;
//import net.minecraft.client.renderer.ItemBlockRenderTypes;

public class FloatingPortalBlock extends CustomPortalBlock {
    public FloatingPortalBlock() {
        super(BlockBehaviour.Properties.of().noCollission().randomTicks().pushReaction(PushReaction.BLOCK).strength(-1.0F).sound(SoundType.GLASS).lightLevel(s -> 11).noLootTable());
    }
}
