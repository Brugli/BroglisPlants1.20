package com.brugli.broglisplants.sound;

import com.brugli.broglisplants.BroglisPlants;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BroglisPlantsSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, BroglisPlants.MODID);

    public static final RegistryObject<SoundEvent> PITCHER_PLANT_BURPS = registerSoundEvents("pitcher_plant_burps");

    public static final RegistryObject<SoundEvent> MANDRAKE_CRY_1 = registerSoundEvents("mandrake_cry_1");
    public static final RegistryObject<SoundEvent> MANDRAKE_CRY_2 = registerSoundEvents("mandrake_cry_2");
    public static final RegistryObject<SoundEvent> MANDRAKE_CRY_3 = registerSoundEvents("mandrake_cry_3");
    public static final RegistryObject<SoundEvent> MANDRAKE_CRY_4 = registerSoundEvents("mandrake_cry_4");
    public static final RegistryObject<SoundEvent> MANDRAKE_CRY_5 = registerSoundEvents("mandrake_cry_5");


    private static RegistryObject<SoundEvent> registerSoundEvents(String name) {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(BroglisPlants.MODID, name)));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}
