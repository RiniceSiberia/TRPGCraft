package windmillbroken.trpgcraft.event;


import net.minecraft.ChatFormatting;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import windmillbroken.trpgcraft.item.weapons.DiceColdWeapon;
import windmillbroken.trpgcraft.item.weapons.SlaaneshSword;
import windmillbroken.trpgcraft.util.TextUtil;
import windmillbroken.trpgcraft.util.Utils;

/**
 *
 * 使用IDEA编写
 *
 * @Author: DUELIST
 * @Time: 2022-07-10-19:27
 * @Message: Have a good time!  :)
 **/

@Mod.EventBusSubscriber(value = Dist.CLIENT)
public class TrpgCraftEventHandler {
    @SubscribeEvent
    public static void onItemTooltip(final ItemTooltipEvent event) {
        if (event.getItemStack().getItem() instanceof SlaaneshSword sword) {
            for (int x = 0; x < event.getToolTip().size(); x++) {
                Component component = event.getToolTip().get(x);
                String attackDamageName = I18n.get("attribute.name.generic.attack_damage");
                if (component.getString().contains(attackDamageName)) {
                    event.getToolTip().set(x,new TranslatableComponent(ChatFormatting.BLUE
                            + "+"
                            + TextUtil.makeFabulous(I18n.get("tip.infinity"))
                            + " "
                            + ChatFormatting.BLUE
                            + I18n.get("attribute.name.generic.attack_damage")));
                    return;
                }
            }
        }else if (event.getItemStack().getItem() instanceof DiceColdWeapon weapon){
            for (int x = 0; x < event.getToolTip().size(); x++) {
                Component component = event.getToolTip().get(x);
                String attackDamageName = I18n.get("attribute.name.generic.attack_damage");
                if (component.getString().contains(attackDamageName)) {
                    event.getToolTip().set(x,new TranslatableComponent(
                            Utils.MESSAGE+".dice_cold_weapon.attack_damage",
                            TextUtil.diceListToComponent(weapon.getDamageDice(event.getItemStack())).getString(),
                            //攻击伤害
                            weapon.getDbRatio()
                    ));
                    return;
                }
            }
        }
    }
}
