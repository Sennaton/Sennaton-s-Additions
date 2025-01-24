
package com.sennaton.sennaton_additions.SennatonItems.Items;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

//import org.sennaton.sennaton_additions.SennatonBlocks.FloatingPortalBlock;

public class WrappedCoalItem extends Item {
    public WrappedCoalItem() {
        super(new Item.Properties().rarity(Rarity.COMMON).durability(64));
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Player entity = context.getPlayer();
        BlockPos pos = context.getClickedPos().relative(context.getClickedFace());
        ItemStack itemstack = context.getItemInHand();
        Level world = context.getLevel();
        //if (!entity.mayUseItemAt(pos, context.getClickedFace(), itemstack)) {
            //return InteractionResult.FAIL;
        //} else {
            //int x = pos.getX();
           // int y = pos.getY();
            //int z = pos.getZ();
            //boolean success = false;
            //if (world.isEmptyBlock(pos) && true) {
                //FloatingPortalBlock.portalSpawn(world, pos);
           // this.useOn();
        itemstack.hurtAndBreak(1, entity, c -> c.broadcastBreakEvent(context.getHand()));
        entity.swing(context.getHand(), true);

                //success = true;
            //}

            return InteractionResult.PASS;
        //}
    }
    //public InteractionResult useOn(){
        //return InteractionResult.SUCCESS;
    //}

}
