package windmillbroken.trpgcraft.tab;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import windmillbroken.trpgcraft.item.TrpgCraftItems;

/**
    *      使用IDEA编写
    * @Author: DUELIST
    * @Time:  2022 07 02-20:33
    * @Message:
 **/
public class TrpgCraftTab extends CreativeModeTab {
    public TrpgCraftTab() {
        super("trpgcraft_group");
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(TrpgCraftItems.slaaneshSword.get());
    }
}
