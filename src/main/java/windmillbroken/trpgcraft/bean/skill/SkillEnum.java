package windmillbroken.trpgcraft.bean.skill;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * 使用IDEA编写
 *
 * @Author: DUELIST
 * @Time: 2022-08-07-17:30
 **/
public enum SkillEnum {
    MATHEMATICS("math",0,5,true,()->{
        Map<String,Integer> map = new HashMap<>();
        map.put("physics",1);
        map.put("chemistry",1);
        map.put("electronics",1);
        map.put("mechanics",1);
        map.put("accounting",1);
        map.put("appraise",1);
        map.put("computer_use",2);
        map.put("cryptography",2);
        return map;
    }),//数学,point 1 -> 5


    PHYSICS("physics",2,1,true,()->{
        Map<String,Integer> map = new HashMap<>();
        map.put("math",1);
        map.put("chemistry",1);
        return map;
    }),//物理

    CHEMISTRY("chemistry",2,1,true,()->{
        Map<String,Integer> map = new HashMap<>();
        map.put("math",1);
        map.put("demolitions",2);
        map.put("physics",1);
        return map;
    }),//化学

    COMPUTER_USE("computer_use",1,5,true,()->{
        Map<String,Integer> map = new HashMap<>();
        map.put("electronics",1);
        map.put("math",2);
        map.put("cryptography",2);
        return map;
    }),//computer

    CRYPTOGRAPHY("cryptography",3,1,true,()->{
        Map<String,Integer> map = new HashMap<>();
        map.put("math",2);
        map.put("cryptography",2);
        return map;
    }),//密码xue

    ELECTRONICS("electronics",1,10,true,()->{
        Map<String,Integer> map = new HashMap<>();
        map.put("math",1);
        map.put("computer_use",2);
        return map;
    }),//电子xue
//    ELECTRONICAL_REPAIR,//电器维修

    MECHANICS("mechanics",1,10,true,()->{
        Map<String,Integer> map = new HashMap<>();
        map.put("math",1);
        return map;
    }),//机械xue
    //MECHANICAL_REPAIR,//机械维修

    ACCOUNTING("accounting",1,5,true,()->{
        Map<String,Integer> map = new HashMap<>();
        map.put("math",1);
        map.put("appraise",2);
        return map;
    }),//会计

    APPRAISE("appraise",1,5,true,()->{
        Map<String,Integer> map = new HashMap<>();
        map.put("accounting",2);
        return map;
    }),//估价/资产评估



    MEDICINE("medicine",1,1,false,() -> {
        Map<String,Integer> map = new HashMap<>();
        map.put("psychoanalysis",1);
        map.put("first_aid",3);
        map.put("pharmacy",1);
        return map;
    }),//医学

    PSYCHOANALYSIS("psychoanalysis",2,1,false,() -> {
        Map<String,Integer> map = new HashMap<>();
        map.put("medicine",1);
        map.put("hypnosis",2);
        return map;
    }),//精神分析

    HYPNOSIS("hypnosis",3,1,false,() -> {
        Map<String,Integer> map = new HashMap<>();
        map.put("psychoanalysis",2);
        return map;
    }),//催眠

    FIRST_AID("first_aid",2,30,true,() -> {
        Map<String,Integer> map = new HashMap<>();
        map.put("medicine",3);
        return map;
    }),//急救

    PHARMACY("pharmacy",3,1,true,() -> {
        Map<String,Integer> map = new HashMap<>();
        map.put("medicine",1);
        map.put("botany",1);
        return map;
    }),//药学

    BOTANY("botany",2,1,true,() -> {
        Map<String,Integer> map = new HashMap<>();
        map.put("pharmacy",1);
        map.put("biology",1);
        return map;
    }),//植物xue


    BIOLOGY("biology",1,1,false,() -> {
        Map<String,Integer> map = new HashMap<>();
        map.put("botany",1);
        map.put("zoology",1);
        map.put("natual_world",1);
        return map;
    }),//生物xue

    ZOOLOGY("zoology",2,1,true,() -> {
        Map<String,Integer> map = new HashMap<>();
        map.put("biology",1);
        map.put("anima_handling",2);
        return map;
    }),//动物xue

    ANIMA_HANDLING("anima_handling",2,5,true,() ->{
        Map<String,Integer> map = new HashMap<>();
        map.put("zoology",2);
        return map;
    }),//动物驯养

    NATURAL_WORLD("natual_world",3,10,false,()->{
        Map<String,Integer> map = new HashMap<>();
        map.put("biology",1);
        map.put("geology",2);
        return map;
    }),//博物xue

    GEOLOGY("geology",2,1,false,()->{
        Map<String,Integer> map = new HashMap<>();
        map.put("natual_world",2);
        map.put("archaeology",1);
        return map;
    }),//地质xue
    ARCHAEOLOGY("archaeology",3,1,true,()->{
        Map<String,Integer> map = new HashMap<>();
        map.put("geology",1);
        map.put("history",1);
        return map;
    }),//考古xue

    HISTORY("history",1,5,false,()->{
        Map<String,Integer> map = new HashMap<>();
        map.put("archaeology",1);
        return map;
    }),//历史

    LITERATURE("literature",0,15,false,()->{
        Map<String,Integer> map = new HashMap<>();
        map.put("fast_read",3);
        return map;
    }),
    //新增 wenxue
    //替换:    LANGUAGE_OTHER,//外语
    //    LANGUAGE_OWN,//母语
    FAST_READ("fast_read",1,10,true,() ->{
        Map<String,Integer> map = new HashMap<>();
        map.put("literature",3);
        return map;
    }),
    // replace liberary


    OCCULT("occult",2,5,false,()->new HashMap<>()),//神秘xue

    METEOROLOGY("meteorology",3,1,false,()->new HashMap<>()),//气象xue


    ASTRONOMY("astronomy",3,1,false,() -> new HashMap<>()),//天文xue


    LAW("law",1,5,false,() -> {
        Map<String,Integer> map = new HashMap<>();
        map.put("anthropology",1);
        map.put("forensics",1);
        return map;
    }),//法律
    ANTHROPOLOGY("anthropology",2,1,false,() -> {
        Map<String,Integer> map = new HashMap<>();
        map.put("law",1);
        return map;
    }),//人类xue
    FORENSICS("forensics",3,1,false,() -> {
        Map<String,Integer> map = new HashMap<>();
        map.put("law",1);
        return map;
    }),//刑侦xueor司法科xue


    HAND_GUN("hand_gun",1,20,true,() -> new HashMap<>()),
    //手枪

    SHOT_GUN("shot_gun",1,15,true,() -> new HashMap<>()),
    //霰弹枪，修改了熟练度属xing

    SUBMACHINE_GUN("submachine_gun",2,10,true,() -> {
        Map<String,Integer> map = new HashMap<>();
        map.put("rifle_gun",1);
        return map;
    }),//冲锋枪，修改了熟练度属xing

    RIFLEGUN("rifle_gun",2,10,true,() -> {
        Map<String,Integer> map = new HashMap<>();
        map.put("submachine_gun",1);
        return map;
    }),//步枪，修改了熟练度属xing

    MACHINE_GUN("machine_gun",3,5,true,() -> {
        Map<String,Integer> map = new HashMap<>();
        map.put("submachine_gun",1);
        return map;
    }),//machinegun

    HEAVY_WEAPONS("heavy_weapons",3,5,true,() -> {
        Map<String,Integer> map = new HashMap<>();
        map.put("submachine_gun",1);
        return map;
    }),//
//    ARTILLERY(),

    THROWER("thrower",3,5,true,() -> new HashMap<>()),
    //
    // FLAMETHROWER,

    DEMOLITIONS("demolitions",3,10,true,() -> {
        Map<String,Integer> map = new HashMap<>();
        map.put("chemistry",2);
        return map;
    }),//爆破

    FIGHTING("fighting",0,25,true,() -> {
        Map<String,Integer> map = new HashMap<>();
        map.put("blunt",2);
        map.put("cleave",2);
        map.put("stab",2);
        map.put("polearm",1);
        map.put("exotic",1);
        return map;
    }),//空手格斗



    BLUNT("blunt",1,10,true,()->{
        Map<String,Integer> map = new HashMap<>();
        map.put("fighting",2);
        return map;
    }),//锤锏

    CLEAVE("cleave",1,1,true,()->{
        Map<String,Integer> map = new HashMap<>();
        map.put("fighting",2);
        return map;
    }),//

    STAB("stab",1,1,true,()->{
        Map<String,Integer> map = new HashMap<>();
        map.put("fighting",2);
        return map;
    }),//

    POLEARM("polearm",2,1,true,()->{
        Map<String,Integer> map = new HashMap<>();
        map.put("fighting",1);
        return map;
    }),//枪棒

    EXOTIC("exotic",1,10,true,()->{
        Map<String,Integer> map = new HashMap<>();
        map.put("fighting",1);
        return map;
    }),//奇门

    //    AXE,//斧头
    //    CHAINSAW,//电锯
    //    DODGE,//闪避
    //    FIREARMS,//射击
    //    FLAIL,//连枷
    //    SPEAR,//
    //    SWORD,//
    //    WHIP,//鞭子
    //    GARROTE,//绞索

    FINE_ART("fine_art",1,5,false, HashMap::new),//美术

    PHOTOGRAPHY("photograph",1,1,true,() -> new HashMap<>()),//摄影

    MUSIC("music",1,1,false,() -> new HashMap<>()),//音乐

    ART_AND_CRAFT("art_and_fine",1,5,false, HashMap::new),

    DISGUISE("disguise",1,5,false, HashMap::new),//乔装

    READ_LIPS("read_lips",3,1,false, HashMap::new),//读唇


    STEALTH("stealth",0,20,true,() -> {
        Map<String,Integer> map = new HashMap<>();
        map.put("sleight_of_hand",3);
        return map;
    }),//潜行

    THROW("throw",0,20,true,() -> {
        Map<String,Integer> map = new HashMap<>();
        map.put("sleight_of_hand",1);
        return map;
    }),//投掷

    SLEIGHT_OF_HAND("sleight_of_hand",1,10,true,() -> {
        Map<String,Integer> map = new HashMap<>();
        map.put("stealth",3);
        map.put("throw",1);
        map.put("locksmith",3);
        map.put("forgery",2);
        return map;
    }),//妙手

    LOCKSMITH("locksmith",2,1,true,() -> {
        Map<String,Integer> map = new HashMap<>();
        map.put("sleight_of_hand",3);
        return map;
    }),//锁匠

    FORGERY("forgery",3,1,true,() -> {
        Map<String,Integer> map = new HashMap<>();
        map.put("sleight_of_hand",2);
        return map;
    }),//

    SWIM("swim",0,20,false,() -> {
        Map<String,Integer> map = new HashMap<>();
        map.put("diving",1);
        return map;
    }),//游泳

    DIVING("diving",3,20,false,() -> {
        Map<String,Integer> map = new HashMap<>();
        map.put("swim",1);
        return map;
    }),//潜水

    JUMP("jump",0,20,false, HashMap::new),//跳跃
    BOW("bow",1,5,true, HashMap::new),//弓术,熟练改为5
    CLIMB("climb",2,20,false, HashMap::new),//
    RIDE("ride",1,5,true, HashMap::new),//

    DRIVE_AUTO("drive_auto",0,20,true,() -> {
        Map<String,Integer> map = new HashMap<>();
        map.put("operate_heavy_machinery",1);
        return map;
    }),//汽车驾驶

    OPERATE_HEAVY_MACHINERY("operate_heavy_machinery",2,1,true,() -> {
        Map<String,Integer> map = new HashMap<>();
        map.put("drive_auto",1);
        return map;
    }),//操作重型机械


    TRACK("track",1,10,false,() -> {
        Map<String,Integer> map = new HashMap<>();
        map.put("navigate",1);
        return map;
    }),//
    NAVIGATE("navigate",2,10,true,() -> {
        Map<String,Integer> map = new HashMap<>();
        map.put("track",1);
        return map;
    }),//领航
    SURVIVAL("survival",1,10,false,HashMap::new),//生存

    PERSUADE("persuade",0,10,false,() -> {
        Map<String,Integer> map = new HashMap<>();
        map.put("intimidate",1);
        map.put("fast_talk",3);
        return map;
    }),//说服
    INTIMIDATE("intimidate",0,15,false,() -> {
        Map<String,Integer> map = new HashMap<>();
        map.put("persuade",1);
        return map;
    }),//恐吓
    FAST_TALK("fast_talk",1,5,false,() -> {
        Map<String,Integer> map = new HashMap<>();
        map.put("persuade",3);
        map.put("acting",3);
        return map;
    }),//话术
    CHARM("charm",1,15,false,() -> {
        Map<String,Integer> map = new HashMap<>();
        map.put("acting",2);
        return map;
    }),//魅惑
    ACTING("charm",2,5,false,() -> {
        Map<String,Integer> map = new HashMap<>();
        map.put("fast_talk",3);
        map.put("charm",2);
        return map;
    }),//表演
    EMOTION_READING("emotion_reading",1,10,false,HashMap::new),//
//    CREDIT_RATING,//
//    CTHULHU_MYTHOS,//
//    LANGUAGE,//
//    PILOT,//驾驶
//    PSYCHOLOGY,//
//    SCIENCE,//


    LISTEN("listen",0,-1,true,()-> new HashMap<>()),//听觉
    TOUCH("touch",0,-1,true,()->new HashMap<>()),//触觉
    SMELL("smell",0,-1,true,()->new HashMap<>()),//嗅觉
    VISION("vision",0,-1,true,()->new HashMap<>()),//视觉

//    LISTEN,//聆听
//    SPOT_HIDDEN,//侦查



    PROFILE("profile",4,1,false,()->{
        Map<String,Integer> map = new HashMap<>();
        map.put("natural_world",1);
        map.put("anthropology",2);
        map.put("forensics",2);
        map.put("track",1);
        return map;
    }),//侧写

    WARCRAFT("warcraft",4,1,true,()->{
        Map<String,Integer> map = new HashMap<>();

        return map;
    }),//兵法



    ;


    private Map<String,Integer> skillLinked;
    private SkillNode skill;

    SkillEnum(String skillName, int rare, int skillDefaultValue, boolean skillActive,Supplier<? extends Map<String,Integer>> skillLinked) {
        Map<String,Integer> map = skillLinked.get();
        this.skillLinked = map;
        this.skill = new SkillNode(skillName,rare,0,0,0,skillDefaultValue,skillDefaultValue,null,skillActive);
    }

    public SkillNode getSkill() {
        return skill;
    }
    public Map<String,Integer> getSkillLinked() {
        return skillLinked;
    }

    public String getSkillName() {
        return skill.getSkillName();
    }
}
