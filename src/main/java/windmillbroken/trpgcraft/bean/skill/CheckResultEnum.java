package windmillbroken.trpgcraft.bean.skill;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import windmillbroken.trpgcraft.bean.dice.Dice;
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


    CheckResultEnum(String id, String voiceId){
        this.id = id;
        this.voiceId = voiceId;
    }

    public static CheckResultEnum skillCheck(Dice dice, int skill){
        if (dice != null){
            int result = dice.roll();
            if (result <= skill){
                if (result <CRITICAL_SUCCESS_VALUE){
                    return CRITICAL_SUCCESS;
                }
                return SUCCESS;
            }else if (result > skill){
                if (result > CRITICAL_FAILURE_VALUE){
                    return CRITICAL_FAILURE;
                }
                return FAILURE;
            }
        }
        return ERROR;
    }

    public String getId(){
        return id;
    }

    public Component getResultToString(int skillValue,int rollValue) {
        Component component = new TranslatableComponent(this.getId(),skillValue,rollValue);
        return component;
    }

    public String getVoiceId() {
        return voiceId;
    }
}
