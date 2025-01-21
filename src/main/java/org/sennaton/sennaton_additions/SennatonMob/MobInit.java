package org.sennaton.sennaton_additions.SennatonMob;

//import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
//import net.minecraft.world.entity.EntityType.Builder.*;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import org.sennaton.sennaton_additions.SennatonMob.Dice.*;


//@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class MobInit {
    public static final EntityType<NynaEntity> NYNA = EntityType.Builder.<NynaEntity>of(NynaEntity::new, MobCategory.MONSTER)/*.sized(0.6f, 1.8f)*/.build("nyna");//.setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(NynaEntity::new)
    public static final EntityType<DiceEntity> DICE = EntityType.Builder.<DiceEntity>of(DiceEntity::new, MobCategory.MISC).sized(0.5f, 0.5f).build("dice");//.setCustomClientFactory(DiceEntity::new).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));
    public static final EntityType<FrozenDiceEntity> FROZEN_DICE = EntityType.Builder.<FrozenDiceEntity>of(FrozenDiceEntity::new, MobCategory.MISC).sized(0.5f, 0.5f).build("frozen_dice");
    public static final EntityType<WarpedDiceEntity> WARPED_DICE = EntityType.Builder.<WarpedDiceEntity>of(WarpedDiceEntity::new, MobCategory.MISC).sized(0.5f, 0.5f).build("warped_dice");
    public static final EntityType<BurningDiceEntity> BURNING_DICE = EntityType.Builder.<BurningDiceEntity>of(BurningDiceEntity::new, MobCategory.MISC).sized(0.5f, 0.5f).build("burning_dice");
    public static final EntityType<HauntedDiceEntity> HAUNTED_DICE = EntityType.Builder.<HauntedDiceEntity>of(HauntedDiceEntity::new, MobCategory.MISC).sized(0.5f, 0.5f).build("haunted_dice");


    public static void initiate() {
        register("nyna", NYNA);
        register("dice", DICE);
        register("frozen_dice", FROZEN_DICE);
        register("warped_dice", WARPED_DICE);
        register("burning_dice", BURNING_DICE);
        register("haunted_dice", HAUNTED_DICE);
    }

    private static <T extends Entity> EntityType<T> register(String name, EntityType built) {
        return Registry.register(BuiltInRegistries.ENTITY_TYPE, new ResourceLocation("sennaton_additions:" + name) , built);
    }


    //@SubscribeEvent
    //public static void registerAttributes(EntityAttributeCreationEvent event) {
        //event.put(NYNA, NynaEntity.createAttributes().build());

    //}

}
