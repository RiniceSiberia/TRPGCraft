package windmillbroken.trpgcraft.util;

import net.minecraft.Util;
import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import windmillbroken.trpgcraft.sound.TrpgCraftSounds;

/**
 * 使用IDEA编写
 *
 * @Author: DUELIST
 * @Time: 2022-09-06-12:37
 * @Message: Have a good time!  :)
 **/
public class RollUtils {

    public static void voice(Level world, Entity entity){
        world.playSound((Player)null, entity.getX(), entity.getY(), entity.getZ(),
                TrpgCraftSounds.ROLL.get(), SoundSource.NEUTRAL,
                0.7F, 1.0F);
    }
    public static void printRollNum(Level world, Component component,Entity entity){
        MinecraftServer server = world.getServer();
        if (world.isClientSide()){
            //只在客户端处理不在多人world
            if(server != null){
                server.getPlayerList().broadcastMessage(component, ChatType.CHAT, Util.NIL_UUID);
            }else {
                entity.sendMessage(component,Util.NIL_UUID);
            }
        }
    }
}
