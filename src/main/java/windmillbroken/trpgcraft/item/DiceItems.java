package windmillbroken.trpgcraft.item;

import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import windmillbroken.trpgcraft.item.dices.DiceItem;
import windmillbroken.trpgcraft.tab.ModTab;
import windmillbroken.trpgcraft.util.Utils;

/**
 *
 * 使用IDEA编写
 *
 * @Author: DUELIST
 * @Time: 2022-07-30-20:41
 * @Message: Have a good time!  :)
 **/
public class DiceItems {
    public static final DeferredRegister<Item> DICES = DeferredRegister.create(ForgeRegistries.ITEMS, Utils.MOD_ID);
    public static final RegistryObject<Item> DICE = DICES.register("dice", () -> new DiceItem(new Item.Properties().tab(ModTab.itemTab)));
//    public static final RegistryObject<Item> D1 = DICES.register("d1", () -> new DiceItem(new DiceImpl(1, DiceTypeEnum.NORMAL), new Item.Properties().tab(ModTab.itemTab)));
//    public static final RegistryObject<Item> COIN = DICES.register("coin", () -> new DiceItem(new DiceImpl(2, DiceTypeEnum.NORMAL), new Item.Properties().tab(ModTab.itemTab)));
//    public static final RegistryObject<Item> D3 = DICES.register("d3", () -> new DiceItem(new DiceImpl(3, DiceTypeEnum.NORMAL), new Item.Properties().tab(ModTab.itemTab)));
//    public static final RegistryObject<Item> D4 = DICES.register("d4", () -> new DiceItem(new DiceImpl(4, DiceTypeEnum.NORMAL), new Item.Properties().tab(ModTab.itemTab)));
//    public static final RegistryObject<Item> D6 = DICES.register("d6", () -> new DiceItem(new DiceImpl(6, DiceTypeEnum.NORMAL), new Item.Properties().tab(ModTab.itemTab)));
//    public static final RegistryObject<Item> D8 = DICES.register("d8", () -> new DiceItem(new DiceImpl(8, DiceTypeEnum.NORMAL), new Item.Properties().tab(ModTab.itemTab)));
//    public static final RegistryObject<Item> D10 = DICES.register("d10", () -> new DiceItem(new DiceImpl(10, DiceTypeEnum.NORMAL), new Item.Properties().tab(ModTab.itemTab)));
//    public static final RegistryObject<Item> D12 = DICES.register("d12", () -> new DiceItem(new DiceImpl(12, DiceTypeEnum.NORMAL), new Item.Properties().tab(ModTab.itemTab)));
//    public static final RegistryObject<Item> D20 = DICES.register("d20", () -> new DiceItem(new DiceImpl(20, DiceTypeEnum.NORMAL), new Item.Properties().tab(ModTab.itemTab)));
//    public static final RegistryObject<Item> D100 = DICES.register("d100", () -> new DiceItem(new DiceImpl(100, DiceTypeEnum.NORMAL), new Item.Properties().tab(ModTab.itemTab)));

}
