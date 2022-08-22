package windmillbroken.trpgcraft.bean.description;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import windmillbroken.trpgcraft.util.Utils;

/**
 * 使用IDEA编写
 *
 * @Author: DUELIST
 * @Time: 2022-08-21-21:40
 * @Message: Have a good time!  :)
 **/
public enum SexEnum {

    MALE(0,"male"),
    FEMALE(1,"female"),
    BOTH(2,"both"),
    ELSE(3,"else")
    ;
    private final int id;
    private final String name;

    public static SexEnum getById(int id){
        for (SexEnum sex : SexEnum.values()){
            if (sex.getId() == id){
                return sex;
            }
        }
        return ELSE;
    }

    public static boolean isInto(SexEnum into,SexEnum target){
        //你的性取向和对方的性别
        if (into.equals(BOTH) || into.equals(target)){
            return true;
        }
        return false;
    }

    public Component toComponent(){
        return new TranslatableComponent("tips."+ Utils.MOD_ID+".sex."+this.name);
    }

    SexEnum(int id,String name){
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
