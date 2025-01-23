
package com.sennaton.sennaton_additions.SennatonItems.Items;

import net.kyrptonaught.customportalapi.portal.PortalIgnitionSource;
import net.kyrptonaught.customportalapi.portal.PortalPlacer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.fml.common.Mod;

//import org.sennaton.sennaton_additions.SennatonBlocks.FloatingPortalBlock;
//@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class WrappedCoalItem extends Item {
    public WrappedCoalItem() {
        super(new Item.Properties().rarity(Rarity.COMMON).durability(64));
    }

    //@SubscribeEvent
    //public void onRightClickItem(PlayerInteractEvent.RightClickItem event) {
       // Entity entity = event.getEntity();
        //Level world = event.getLevel();
      //  InteractionHand hand = event.getHand();

       // if (entity instanceof Player player) {
         //   ItemStack stack = player.getItemInHand(hand);
          //  if (!world.isClientSide) {
           //     Item item = stack.getItem();
            //    if (PortalIgnitionSource.isRegisteredIgnitionSourceWith(item)) {
             //       HitResult hit = player.pick(1,1,false);
             //       if (hit.getType() == HitResult.Type.BLOCK) {
              //          BlockHitResult blockHit = (BlockHitResult) hit;
                //        BlockPos usedBlockPos = blockHit.getBlockPos();
               //         if (PortalPlacer.attemptPortalLight(world, usedBlockPos.offset(blockHit.getDirection().getNormal()), PortalIgnitionSource.ItemUseSource(item))) {
                 //           event.setResult(Event.Result.ALLOW);
                 //       }
                 //   }
               // }
           // }
        //}
    //}


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
