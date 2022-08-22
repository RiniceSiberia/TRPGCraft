package windmillbroken.trpgcraft.item;

import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import windmillbroken.trpgcraft.item.tools.CharacterSheet;
import windmillbroken.trpgcraft.item.weapons.SlaaneshSword;
import windmillbroken.trpgcraft.tab.ModTab;
import windmillbroken.trpgcraft.util.Utils;


/**
    *      使用IDEA编写
    * @Author: DUELIST
    * @Time:  2022 07 02-20:34
 **/
public class TrpgCraftItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Utils.MOD_ID);
    public static final RegistryObject<Item> slaaneshSword = ITEMS.register("slaanesh_sword", () -> new SlaaneshSword(new Item.Properties().tab(ModTab.itemTab)));
    public static final RegistryObject<Item> characterSheet = ITEMS.register("character_sheet", () -> new CharacterSheet(new Item.Properties().tab(ModTab.itemTab)));


}
