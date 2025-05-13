package com.sennaton.sennaton_additions.Sound;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.ForgeRegistries;
import com.sennaton.sennaton_additions.Sennaton_Additions;

public class Sound {
    //public static final DeferredRegister<SoundEvent SOUND_EVENTS =
    //        DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Sennaton_Additions.MODID);

    public static void initiate() {
        registerSoundEvent("nyna_mew");
        registerSoundEvent("nyna_hurt");
        registerSoundEvent("nyna_death");
        registerSoundEvent("nyna_un_mew");
        registerSoundEvent("nyna_un_hurt");
        registerSoundEvent("nyna_un_death");
        registerSoundEvent("nyna_mews");
        registerSoundEvent("nyna_hurts");
        registerSoundEvent("nyna_deaths");
    }

    private static void registerSoundEvent(String name) {
        ResourceLocation id = new ResourceLocation(Sennaton_Additions.MODID, name);
        ForgeRegistries.SOUND_EVENTS.register(id, SoundEvent.createVariableRangeEvent(id));//(name, ()-> SoundEvent.createVariableRangeEvent(id););
    }

    //public static void register(IEventBus eventBus) {
    //SOUND_EVENTS.register(eventBus);
    //}
}
