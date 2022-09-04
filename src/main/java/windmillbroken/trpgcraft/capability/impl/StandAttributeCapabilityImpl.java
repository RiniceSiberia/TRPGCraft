package windmillbroken.trpgcraft.capability.impl;

import net.minecraft.nbt.CompoundTag;
import windmillbroken.trpgcraft.capability.api.StandAttributeCapability;
import windmillbroken.trpgcraft.bean.dice.Dice;
import windmillbroken.trpgcraft.util.AttributeUtils;

import java.util.List;

import static windmillbroken.trpgcraft.util.AttributeUtils.*;


/**
 * 使用IDEA编写
 *
 * @Author: DUELIST
 * @Time: 2022-08-22-12:10
 * @Message: Have a good time!  :)
 **/
public class StandAttributeCapabilityImpl implements StandAttributeCapability {

    private int str;
    private int con;
    private int siz;
    private int dex;
    private int app;
    private int inte;
    private int pow;
    private int edu;
    private int luck;
    //基础九属性

    private int build;
    //体型
    private List<Dice> db;
    //伤害加深

    private int mp;
    //魔法值
    private int mpLimit;
    //魔法值上限

    private int sanity;
    //理智

    public static final String BUILD = "build";
    public static final String DB = "db";
    public static final String MP = "mp";
    public static final String MP_LIMIT = "mp_limit";
    public static final String SANITY = "sanity";





    public StandAttributeCapabilityImpl(){
        //没有mov,这个属性被废弃了
        setStr(50);
        setCon(50);
        setSiz(50);
        setDex(50);
        setApp(50);
        setInte(50);
        setPow(50);
        setEdu(50);
        setLuck(50);
    }

    @Override
    public int getSkillValueByIndex(int index){
        if (index == STR_INDEX){
            return getStr();
        }else if (index == CON_INDEX){
            return getCon();
        }else if (index == SIZ_INDEX){
            return getSiz();
        }else if (index == DEX_INDEX){
            return getDex();
        }else if (index == APP_INDEX){
            return getApp();
        }else if (index == INTE_INDEX){
            return getInte();
        }else if (index == POW_INDEX){
            return getPow();
        }else if (index == EDU_INDEX){
            return getEdu();
        }
        return getLuck();
    }



    @Override
    public void addLuck(int value) {
        this.luck += value;
    }

    @Override
    public void reduceLuck(int value) {
        addLuck(-value);
    }

    @Override
    public void addMp(int value){
        this.mp += value;
        if (this.mp > this.mpLimit){
            this.mp = this.mpLimit;
        }
    }
    @Override
    public void reduceMp(int value){
        addMp(-value);
    }
    @Override
    public void addSanity(int value){
        this.sanity += value;
        if (this.sanity > this.mpLimit){
            this.sanity = this.mpLimit;
        }
    }
    @Override
    public void reduceSanity(int value){
        addSanity(-value);
    }


    @Override
    public int getStr() {
        return str;
    }
    @Override
    public void setStr(int str) {
        this.str = str;
        this.build = AttributeUtils.getBuild(this.str,this.siz);
        this.db = AttributeUtils.buildToDb(build);
    }
    @Override
    public int getCon() {
        return con;
    }
    @Override
    public void setCon(int con) {
        this.con = con;
    }
    @Override
    public int getSiz() {
        return siz;
    }
    @Override
    public void setSiz(int siz) {
        this.siz = siz;
        this.build = AttributeUtils.getBuild(this.str,this.siz);
        this.db = AttributeUtils.buildToDb(build);

    }
    @Override
    public int getDex() {
        return dex;
    }
    @Override
    public void setDex(int dex) {
        this.dex = dex;
    }
    @Override
    public int getApp() {
        return app;
    }
    @Override
    public void setApp(int app) {
        this.app = app;
    }
    @Override
    public int getInte() {
        return inte;
    }
    @Override
    public void setInte(int inte) {
        this.inte = inte;
    }
    @Override
    public int getPow() {
        return pow;
    }
    @Override
    public void setPow(int pow) {
        this.pow = pow;
        setMpLimit(pow/5);
        setSanity(pow);
    }
    @Override
    public int getEdu() {
        return edu;
    }
    @Override
    public void setEdu(int edu) {
        this.edu = edu;
    }
    @Override
    public int getLuck() {
        return luck;
    }
    @Override
    public void setLuck(int luck) {
        this.luck = luck;
    }

    @Override
    public int getBuild() {
        return build;
    }
    @Override
    public List<Dice> getDb() {
        return db;
    }
    @Override
    public int getMp() {
        return mp;
    }
    @Override
    public void setMp(int mp) {
        this.mp = mp;
        if (mp > mpLimit) {
            this.mp = mpLimit;
        }
    }
    @Override
    public int getMpLimit() {
        return mpLimit;
    }
    @Override
    public void setMpLimit(int mpLimit) {
        this.mpLimit = mpLimit;
        if (this.mp > mpLimit) {
            this.mp = mpLimit;
        }
    }
    @Override
    public int getSanity() {
        return sanity;
    }
    @Override
    public void setSanity(int sanity) {
        this.sanity = sanity;
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putInt(STR_NAME,str);
        tag.putInt(CON_NAME,con);
        tag.putInt(SIZ_NAME,siz);
        tag.putInt(DEX_NAME,dex);
        tag.putInt(APP_NAME,app);
        tag.putInt(INTE_NAME,inte);
        tag.putInt(POW_NAME,pow);
        tag.putInt(EDU_NAME,edu);
        tag.putInt(LUCK_NAME,luck);
        tag.putInt(MP,mp);
        tag.putInt(MP_LIMIT,mpLimit);
        tag.putInt(SANITY,sanity);
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        setStr(nbt.getInt(STR_NAME));
        setCon(nbt.getInt(CON_NAME));
        setSiz(nbt.getInt(SIZ_NAME));
        setDex(nbt.getInt(DEX_NAME));
        setApp(nbt.getInt(APP_NAME));
        setInte(nbt.getInt(INTE_NAME));
        setPow(nbt.getInt(POW_NAME));
        setEdu(nbt.getInt(EDU_NAME));
        setLuck(nbt.getInt(LUCK_NAME));
        setMpLimit(nbt.getInt(MP_LIMIT));
        setMp(nbt.getInt(MP));
        setSanity(nbt.getInt(SANITY));
    }
}
