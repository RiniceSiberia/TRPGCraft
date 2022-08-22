package windmillbroken.trpgcraft.capability.api;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.INBTSerializable;
import windmillbroken.trpgcraft.bean.dice.Dice;

import java.util.List;

/**
 * 使用IDEA编写
 *
 * @Author: DUELIST
 * @Time: 2022-08-22-13:23
 * @Message: Have a good time!  :)
 **/
public interface StandAttributeCapability extends INBTSerializable<CompoundTag> {
    public void addMp(int value);

    public void reduceMp(int value);

    public void addSanity(int value);

    public void reduceSanity(int value);

    public int getStr();

    public void setStr(int str);

    public int getCon();

    public void setCon(int con);

    public int getSiz();

    public void setSiz(int siz);

    public int getDex();

    public void setDex(int dex);

    public int getApp();

    public void setApp(int app);

    public int getInte();

    public void setInte(int inte);

    public int getPow();

    public void setPow(int pow);

    public int getEdu();

    public void setEdu(int edu);

    public int getBuild();

    public List<Dice> getDb();

    public int getMp();

    public void setMp(int mp);

    public int getMpLimit();

    public void setMpLimit(int mpLimit);

    public int getSanity();

    public void setSanity(int sanity);
}
