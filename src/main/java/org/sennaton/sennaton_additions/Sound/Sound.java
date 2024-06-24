package org.sennaton.sennaton_additions.Sound;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.sennaton.sennaton_additions.Sennaton_Additions;

public class Sound {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Sennaton_Additions.MODID);

    public static final RegistryObject<SoundEvent> NYNA_MEW = registerSoundEvent("nyna_mew");


    private static RegistryObject<SoundEvent> registerSoundEvent(String name) {
        ResourceLocation id = new ResourceLocation(Sennaton_Additions.MODID, name);
    return SOUND_EVENTS.register(name, ()-> SoundEvent.createVariableRangeEvent(id));
    }

    public static void register(IEventBus eventBus) {
    SOUND_EVENTS.register(eventBus);
    }
}
