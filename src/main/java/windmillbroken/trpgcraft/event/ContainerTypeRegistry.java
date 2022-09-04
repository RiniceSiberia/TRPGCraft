package windmillbroken.trpgcraft.event;

import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraft.world.entity.player.Inventory;
import windmillbroken.trpgcraft.gui.CharacterSheetMenu;
import windmillbroken.trpgcraft.util.Utils;

/**
 * 使用IDEA编写
 *
 * @Author: DUELIST
 * @Time: 2022-08-23-12:08
 * @Message: Have a good time!  :)
 **/
public class ContainerTypeRegistry {
    public static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, Utils.MOD_ID);
    public static RegistryObject<MenuType<CharacterSheetMenu>> CHARACTER_SHEET_MENU = CONTAINERS.register("character_sheet_container",
            () -> IForgeMenuType.create(CharacterSheetMenu::fromNetwork));

}
