package windmillbroken.trpgcraft.event;

import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import windmillbroken.trpgcraft.capability.provider.DiceProvider;
import windmillbroken.trpgcraft.item.DiceItems;
import windmillbroken.trpgcraft.util.Utils;

import static windmillbroken.trpgcraft.capability.provider.DiceProvider.DICE_CAPABILITY;


/**
 *
 * 使用IDEA编写
 *
 * @Author: DUELIST
 * @Time: 2022-08-02-22:49
 * @Message: 给骰子附加判定数值的能力
 **/
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD , value = Dist.CLIENT)
public class PropertyRegistry {
    @SubscribeEvent
    public static void diceModelNBTRegistry(final FMLClientSetupEvent event){
        event.enqueueWork(() -> {
            ItemProperties.register(DiceItems.DICE.get(),
                    new ResourceLocation(Utils.MOD_ID,"dice_type"),
                    (stack,world,entity,id)  ->
                            stack.getCapability(DICE_CAPABILITY)
                                    .resolve().map(c -> (int)(c.getDice().get(0).getType().getId())).orElse(0)
            );
        });
        event.enqueueWork(() -> {
            ItemProperties.register(DiceItems.DICE.get(),
                    new ResourceLocation(Utils.MOD_ID,"dice_value"),
                    (stack,world,entity,id)  ->
                            stack.getCapability(DICE_CAPABILITY)
                                    .resolve().map(c -> c.getDice().get(0).getDiceValue()).orElse(0)
                    );
        });
    }
}
