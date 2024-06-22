package org.sennaton.sennaton_additions.SennatonMob;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;

import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;
import org.sennaton.sennaton_additions.SennatonMob.Dice.*;
import org.sennaton.sennaton_additions.Sennaton_Additions;


@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class MobInit {
    public static final DeferredRegister<EntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Sennaton_Additions.MODID);
    public static final RegistryObject<EntityType<NynaEntity>> NYNA = register("nyna",
            EntityType.Builder.<NynaEntity>of(NynaEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(NynaEntity::new)

                    .sized(0.6f, 1.8f));

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
        return REGISTRY.register(registryname, () -> (EntityType<T>) entityTypeBuilder.build(registryname));
    }


    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(NYNA.get(), NynaEntity.createAttributes().build());

    }

}
