package com.sennaton.sennaton_additions.SennatonMob;

//import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
//import net.minecraft.world.entity.EntityType.Builder.*;

import com.sennaton.sennaton_additions.SennatonMob.Dice.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import com.sennaton.sennaton_additions.SennatonMob.Dice.*;
import com.sennaton.sennaton_additions.Sennaton_Additions;


@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class MobInit {
    public static final DeferredRegister<EntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Sennaton_Additions.MODID);
    public static final RegistryObject<EntityType<NynaEntity>> NYNA = register("nyna",
            EntityType.Builder.<NynaEntity>of(NynaEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(NynaEntity::new).sized(0.6f, 1.8f));

    public static final RegistryObject<EntityType<DiceEntity>> DICE = register("dice",
            EntityType.Builder.<DiceEntity>of(DiceEntity::new, MobCategory.MISC).setCustomClientFactory(DiceEntity::new).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));
    public static final RegistryObject<EntityType<FrozenDiceEntity>> FROZEN_DICE = register("frozen_dice",
            EntityType.Builder.<FrozenDiceEntity>of(FrozenDiceEntity::new, MobCategory.MISC).setCustomClientFactory(FrozenDiceEntity::new).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));
    public static final RegistryObject<EntityType<WarpedDiceEntity>> WARPED_DICE = register("warped_dice",
            EntityType.Builder.<WarpedDiceEntity>of(WarpedDiceEntity::new, MobCategory.MISC).setCustomClientFactory(WarpedDiceEntity::new).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));
    public static final RegistryObject<EntityType<BurningDiceEntity>> BURNING_DICE = register("burning_dice",
            EntityType.Builder.<BurningDiceEntity>of(BurningDiceEntity::new, MobCategory.MISC).setCustomClientFactory(BurningDiceEntity::new).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));
    public static final RegistryObject<EntityType<HauntedDiceEntity>> HAUNTED_DICE = register("haunted_dice",
            EntityType.Builder.<HauntedDiceEntity>of(HauntedDiceEntity::new, MobCategory.MISC).setCustomClientFactory(HauntedDiceEntity::new).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));

    private static <T extends Entity> RegistryObject<EntityType<T>> register(String registryname, EntityType.Builder<T> entityTypeBuilder) {
        return REGISTRY.register(registryname, () -> entityTypeBuilder.build(registryname));
    }

    public static void initiate(IEventBus bus) {
    REGISTRY.register(bus);
    }

    private static <T extends Entity> void register(String name, EntityType built) {
        ForgeRegistries.ENTITY_TYPES.register(new ResourceLocation("sennaton_additions:" + name) , built);
    }

    public static <T extends Entity> EntityType get(RegistryObject<EntityType<T>> entity){
        return entity.get();
    }


    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(get(NYNA), NynaEntity.createAttributes().build());

    }

}
