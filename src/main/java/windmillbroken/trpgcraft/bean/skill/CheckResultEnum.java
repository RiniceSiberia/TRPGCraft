package windmillbroken.trpgcraft.bean.skill;

import net.minecraft.Util;
import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.MinecraftServer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import windmillbroken.trpgcraft.bean.dice.Dice;
import windmillbroken.trpgcraft.sound.TrpgCraftSounds;
import windmillbroken.trpgcraft.util.Utils;

/**
 * 使用IDEA编写
 *
 * @Author: DUELIST
 * @Time: 2022-07-30-17:52
 * @Message: Have a good time!  :)
 **/
public enum CheckResultEnum {
    SUCCESS(Utils.MOD_ID+".roll.success",Utils.MOD_ID+".sound.success"),
    FAILURE(Utils.MOD_ID+".roll.failure",Utils.MOD_ID+".sound.failure"),
    CRITICAL_SUCCESS(Utils.MOD_ID+".roll.success.critical",Utils.MOD_ID+".sound.success_critical"),
    CRITICAL_FAILURE(Utils.MOD_ID+".roll.failure.critical",Utils.MOD_ID+".sound.failure_critical"),
    ERROR(Utils.MOD_ID+".error",null),
    ;
    private static final int CRITICAL_SUCCESS_VALUE = 3;
    private static final int CRITICAL_FAILURE_VALUE = 97;
    private final String id;
    //注册名称
    private final String voiceId;
    //声音注册名称

    public static final int REG = 0;
    public static final int HALF = 1;
    public static final int FIFTH = 2;


    CheckResultEnum(String id, String voiceId){
        this.id = id;
        this.voiceId = voiceId;
    }

    public static CheckResultEnum skillCheck(Dice dice, Component skillName, int skillValue, Level world, Entity entity,int hard){
        //带有难度
        switch (hard){
            case HALF:
                skillValue /= 2;
            case FIFTH:
                    skillValue /= 5;
            default:

        }
        return skillCheck(dice, skillName, skillValue, world, entity);
    }
    public static CheckResultEnum skillCheck(Dice dice, SkillNode skill, Level world, Entity entity){
        Component component;
        if (skill.getSkillElseName() != null){
            component = skill.getSkillElseName();
        }else {
            component = skill.getSkillNameComponent();
        }
        int skillValue = skill.getSkillDefaultValue()
                + skill.getSkillExtraValue()
                + skill.getSkillGrowValue()
                + skill.getSkillValue();
        return skillCheck(dice,component,skillValue,world,entity);
    }
    public static CheckResultEnum skillCheck(Dice dice, Component skillName, int skillValue, Level world, Entity entity){
        CheckResultEnum c = ERROR;
        if (dice != null){
            int result = dice.roll();
            MinecraftServer server = world.getServer();
            world.playSound((Player)null, entity.getX(), entity.getY(), entity.getZ(),
                    TrpgCraftSounds.ROLL.get(), SoundSource.NEUTRAL,
                    0.7F, 1.0F);
            Component name = entity.getName();
            if (result <= skillValue){
                if (result <CRITICAL_SUCCESS_VALUE){
                    c = CRITICAL_SUCCESS;
                }else {
                    c = SUCCESS;
                }
            }else if (result > skillValue){
                if (result > CRITICAL_FAILURE_VALUE){
                    c = CRITICAL_FAILURE;
                }else {
                    c = FAILURE;
                }
            }
            Component component = getResultMessageToString(c,skillValue,result,name,skillName);
            if (world.isClientSide()){
                //只在客户端处理不在多人world
                if(server != null){
                    server.getPlayerList().broadcastMessage(
                            component, ChatType.CHAT, Util.NIL_UUID);
                }else {
                    entity.sendMessage(component,Util.NIL_UUID);
                }
            }
        }
        return c;
    }


    public String getId(){
        return id;
    }

    public Component getResultNameToString(int skillValue,int rollValue) {
        return new TranslatableComponent(this.getId(),skillValue,rollValue);
    }
    public static Component getResultMessageToString(CheckResultEnum checkResultEnum,int skillValue,int rollValue,Component playerName,Component skillName){
        return new TranslatableComponent(checkResultEnum.getId() + Utils.MESSAGE,playerName,skillName,rollValue,skillValue);
    }

    public String getVoiceId() {
        return voiceId;
    }
}
