package windmillbroken.trpgcraft.event;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import windmillbroken.trpgcraft.gui.CharacterSheetGui;
import windmillbroken.trpgcraft.util.Utils;

/**
 * 使用IDEA编写
 *
 * @Author: DUELIST
 * @Time: 2022-09-04-20:54
 * @Message: Have a good time!  :)
 **/
@Mod.EventBusSubscriber(modid = Utils.MOD_ID,bus = Mod.EventBusSubscriber.Bus.MOD,value = Dist.CLIENT)
public class GUIRegisterEvent {
    @SubscribeEvent
    public static void clientInit(FMLClientSetupEvent event){
        event.enqueueWork(() -> {
            MenuScreens.register(ContainerTypeRegistry.CHARACTER_SHEET_MENU.get(), CharacterSheetGui:: new);
        });
    }
}
