package windmillbroken.trpgcraft.capability.impl;

import net.minecraft.nbt.CompoundTag;
import windmillbroken.trpgcraft.capability.api.StandAttributeCapability;
import windmillbroken.trpgcraft.bean.dice.Dice;
import windmillbroken.trpgcraft.util.AttributeUtils;

import java.util.List;


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

    //基础八属性，不含幸运


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

    public static final String STR = "str";
    public static final String CON = "con";
    public static final String SIZ = "siz";
    public static final String DEX = "dex";
    public static final String APP = "app";
    public static final String INTE = "inte";
    public static final String POW = "pow";
    public static final String EDU = "edu";
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
    }

    @Override
    public void addMp(int value){
        if (value <= 0) {
            return;
        }
        this.mp += value;
        if (this.mp > this.mpLimit){
            this.mp = this.mpLimit;
        }
    }
    @Override
    public void reduceMp(int value){
        if (value >= 0) {
            return;
        }
        this.mp -= value;
        if (this.mp < 0){
            this.mp = 0;
        }
    }
    @Override
    public void addSanity(int value){
        if (value <= 0) {
            return;
        }
        this.sanity += value;
        if (this.sanity > this.mpLimit){
            this.sanity = this.mpLimit;
        }
    }
    @Override
    public void reduceSanity(int value){
        if (value >= 0) {
            return;
        }
        this.sanity -= value;
        if (this.sanity < 0){
            this.sanity = 0;
        }
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
        tag.putInt(STR,str);
        tag.putInt(CON,con);
        tag.putInt(SIZ,siz);
        tag.putInt(DEX,dex);
        tag.putInt(APP,app);
        tag.putInt(INTE,inte);
        tag.putInt(POW,pow);
        tag.putInt(EDU,edu);
        tag.putInt(MP,mp);
        tag.putInt(MP_LIMIT,mpLimit);
        tag.putInt(SANITY,sanity);
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        setStr(nbt.getInt(STR));
        setCon(nbt.getInt(CON));
        setSiz(nbt.getInt(SIZ));
        setDex(nbt.getInt(DEX));
        setApp(nbt.getInt(APP));
        setInte(nbt.getInt(INTE));
        setPow(nbt.getInt(POW));
        setEdu(nbt.getInt(EDU));
        setMpLimit(nbt.getInt(MP_LIMIT));
        setMp(nbt.getInt(MP));
        setSanity(nbt.getInt(SANITY));
    }
}
