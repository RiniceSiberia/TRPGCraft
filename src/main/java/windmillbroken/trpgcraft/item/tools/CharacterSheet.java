package windmillbroken.trpgcraft.item.tools;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;
import windmillbroken.trpgcraft.gui.CharacterSheetGui;
import windmillbroken.trpgcraft.util.Utils;

/**
 * @author duelist
 */
public class CharacterSheet extends Item {
    public CharacterSheet(Item.Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand interactionHand) {
        MinecraftServer server = world.getServer();
        if (world.isClientSide()){
            openSheetGui(player);
            if(server != null){
                //多人模式
                }else {
            }
        }
        return super.use(world, player, interactionHand);
    }


    public static void openSheetGui(Entity entity){
        Minecraft.getInstance().setScreen(new CharacterSheetGui(
                new TranslatableComponent( Utils.MOD_ID + ".sheet.title",
                        entity.getName().getString()),entity));
    }

}
