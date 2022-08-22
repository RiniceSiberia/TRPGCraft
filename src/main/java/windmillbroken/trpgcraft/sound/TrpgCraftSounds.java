package windmillbroken.trpgcraft.sound;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import windmillbroken.trpgcraft.util.Utils;

/**
 * 使用IDEA编写
 *
 * @Author: DUELIST
 * @Time: 2022-08-03-18:13
 * @Message: Have a good time!  :)
 **/
public class TrpgCraftSounds {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Utils.MOD_ID);
    public static final RegistryObject<SoundEvent> ROLL
            = SOUNDS.register("roll", () -> new SoundEvent(
                    new ResourceLocation(Utils.MOD_ID , "roll")));
    public static final RegistryObject<SoundEvent> MULTI_ROLL
            = SOUNDS.register("multi_roll", () -> new SoundEvent(
            new ResourceLocation(Utils.MOD_ID , "multi_roll")));
    public static final RegistryObject<SoundEvent> SUCCESS
            = SOUNDS.register("success", () -> new SoundEvent(
            new ResourceLocation(Utils.MOD_ID , "success")));
    public static final RegistryObject<SoundEvent> SUCCESS_CRITICAL
            = SOUNDS.register("success_critical", () -> new SoundEvent(
            new ResourceLocation(Utils.MOD_ID , "success_critical")));
    public static final RegistryObject<SoundEvent> FAILURE
            = SOUNDS.register("failure", () -> new SoundEvent(
            new ResourceLocation(Utils.MOD_ID , "failure")));
    public static final RegistryObject<SoundEvent> FAILURE_CRITICAL
            = SOUNDS.register("failure_critical", () -> new SoundEvent(
            new ResourceLocation(Utils.MOD_ID , "failure_critical")));
}

