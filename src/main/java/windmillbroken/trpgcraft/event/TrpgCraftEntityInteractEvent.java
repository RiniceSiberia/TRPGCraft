package windmillbroken.trpgcraft.event;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;
import windmillbroken.trpgcraft.capability.provider.PlayerSkillProvider;
import windmillbroken.trpgcraft.gui.CharacterSheetGui;
import windmillbroken.trpgcraft.item.tools.CharacterSheet;
import windmillbroken.trpgcraft.util.Utils;


@Mod.EventBusSubscriber()
public class TrpgCraftEntityInteractEvent {
    @SubscribeEvent
    public static void entityInteract(@NotNull PlayerInteractEvent.EntityInteract event) {
        if (event.getHand() == InteractionHand.MAIN_HAND && event.getPlayer().isLocalPlayer()){
            Player player = event.getPlayer();
            Entity entity = event.getTarget();
            Level world = event.getWorld();
            MinecraftServer server = world.getServer();
            if (player.getMainHandItem().getItem() instanceof CharacterSheet
            && entity.getCapability(PlayerSkillProvider.STAND_ATTRIBUTE_CAPABILITY).resolve().isPresent()) {
                if (world.isClientSide()){
                    CharacterSheet.openSheetGui(player);
                    if(server != null){
                        //多人模式
                    }else {

                    }
                }
            }
        }
    }
}
