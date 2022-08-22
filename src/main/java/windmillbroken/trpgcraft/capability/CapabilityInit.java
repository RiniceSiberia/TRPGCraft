package windmillbroken.trpgcraft.capability;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import windmillbroken.trpgcraft.item.dices.DiceItem;
import windmillbroken.trpgcraft.capability.api.DiceCapability;
import windmillbroken.trpgcraft.capability.api.SkillCapability;
import windmillbroken.trpgcraft.capability.impl.SkillCapabilityImpl;
import windmillbroken.trpgcraft.capability.provider.DiceProvider;
import windmillbroken.trpgcraft.capability.provider.PlayerSkillProvider;
import windmillbroken.trpgcraft.util.Utils;

/**
 *
 * @Author: DUELIST
 * @Time: 2022-07-19-14:06
 * @Message: Have a good time!  :)
 **/
@Mod.EventBusSubscriber
public class CapabilityInit {
    @SubscribeEvent
    public static void registerCapability(RegisterCapabilitiesEvent event) {
        event.register(DiceCapability.class);
        event.register(SkillCapability.class);

    }

    @SubscribeEvent
    public static void attachItemCapabilities(AttachCapabilitiesEvent<ItemStack> event) {

        if (event.getObject().getItem() instanceof DiceItem) {
            event.addCapability(new ResourceLocation(Utils.MOD_ID + ":" + "dice_capability"), new DiceProvider());
        }
    }

    @SubscribeEvent
    public static void attachEntityCapability(AttachCapabilitiesEvent<Entity> event) {

        if (event.getObject() instanceof Player player){
            event.addCapability(new ResourceLocation(Utils.MOD_ID + ":" + "player_capability"), new PlayerSkillProvider());
        }
    }

    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event) {
        final Player original = event.getOriginal();
        original.reviveCaps();
        final LazyOptional<SkillCapability> oldCap = original.getCapability(PlayerSkillProvider.SKILL_CAPABILITY);
        final LazyOptional<SkillCapability> newCap = event.getPlayer().getCapability(PlayerSkillProvider.SKILL_CAPABILITY);
        newCap.ifPresent((n) -> oldCap.ifPresent((o) -> n.deserializeNBT(o.serializeNBT())));
        original.invalidateCaps();
    }

}
