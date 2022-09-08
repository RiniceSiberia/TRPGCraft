package windmillbroken.trpgcraft.bean.skill;

import com.google.common.collect.ImmutableMap;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * 使用IDEA编写
 *
 * @Author: DUELIST
 * @Time: 2022-08-07-17:30
 * @Message: Have a good time!  :)
 **/
public enum SkillEnum {
    MATHEMATICS("math",0,5,true,ImmutableMap.<String, Integer> builder()
        .put("physics",1)
        .put("chemistry",1)
        .put("electronics",1)
        .put("mechanics",1)
        .put("accounting",1)
        .put("appraise",1)
        .put("computer_use",2)
        .put("cryptography",2)
        .build()),//数学,point 1 -> 5


    PHYSICS("physics",2,1,true,ImmutableMap.<String, Integer> builder()
        .put("math",1)
        .put("chemistry",1)
        .build()),//物理

    CHEMISTRY("chemistry",2,1,true,ImmutableMap.<String, Integer> builder()
        .put("math",1)
        .put("demolitions",2)
        .put("physics",1)
        .build()),//化学

    COMPUTER_USE("computer_use",1,5,true,ImmutableMap.<String, Integer> builder()
        .put("electronics",1)
        .put("math",2)
        .put("cryptography",2)
        .build()),//computer

    CRYPTOGRAPHY("cryptography",3,1,true,ImmutableMap.<String, Integer> builder()
        .put("math",2)
        .put("cryptography",2)
        .build()),//密码xue

    ELECTRONICS("electronics",1,10,true,ImmutableMap.<String, Integer> builder()
        .put("math",1)
        .put("computer_use",2)
        .build()),//电子xue
//    ELECTRONICAL_REPAIR,//电器维修

    MECHANICS("mechanics",1,10,true,ImmutableMap.<String, Integer> builder()
        .put("math",1)
        .build()),//机械xue
    //MECHANICAL_REPAIR,//机械维修

    ACCOUNTING("accounting",1,5,true,ImmutableMap.<String, Integer> builder()
        .put("math",1)
        .put("appraise",2)
        .build()),//会计

    APPRAISE("appraise",1,5,true,ImmutableMap.<String, Integer> builder()
        .put("accounting",2)
        .build()),//估价/资产评估



    MEDICINE("medicine",1,1,false,ImmutableMap.<String, Integer> builder()
        .put("psychoanalysis",1)
        .put("first_aid",3)
        .put("pharmacy",1)
        .build()),//医学

    PSYCHOANALYSIS("psychoanalysis",2,1,false,ImmutableMap.<String, Integer> builder()
        .put("medicine",1)
        .put("hypnosis",2)
        .build()),//精神分析

    HYPNOSIS("hypnosis",3,1,false,ImmutableMap.<String, Integer> builder()
        .put("psychoanalysis",2)
        .build()),//催眠

    FIRST_AID("first_aid",2,30,true,ImmutableMap.<String, Integer> builder()
        .put("medicine",3)
        .build()),//急救

    PHARMACY("pharmacy",3,1,true,ImmutableMap.<String, Integer> builder()
        .put("medicine",1)
        .put("botany",1)
        .build()),//药学

    BOTANY("botany",2,1,true,ImmutableMap.<String, Integer> builder()
        .put("pharmacy",1)
        .put("biology",1)
        .build()),//植物xue


    BIOLOGY("biology",1,1,false,ImmutableMap.<String, Integer> builder()
        .put("botany",1)
        .put("zoology",1)
        .put("natual_world",1)
        .build()),//生物xue

    ZOOLOGY("zoology",2,1,true,ImmutableMap.<String, Integer> builder()
        .put("biology",1)
        .put("anima_handling",2)
        .build()),//动物xue

    ANIMA_HANDLING("anima_handling",2,5,true,ImmutableMap.<String, Integer> builder()
        .put("zoology",2)
        .build()),//动物驯养

    NATURAL_WORLD("natual_world",3,10,false,ImmutableMap.<String, Integer> builder()
        .put("biology",1)
        .put("geology",2)
        .build()),//博物xue

    GEOLOGY("geology",2,1,false,ImmutableMap.<String, Integer> builder()
        .put("natual_world",2)
        .put("archaeology",1)
        .build()),//地质xue
    ARCHAEOLOGY("archaeology",3,1,true,ImmutableMap.<String, Integer> builder()
        .put("geology",1)
        .put("history",1)
        .build()),//考古xue

    HISTORY("history",1,5,false,ImmutableMap.<String, Integer> builder()
        .put("archaeology",1)
        .build()),//历史

    LITERATURE("literature",0,15,false,ImmutableMap.<String, Integer> builder()
        .put("fast_read",3)
        .build()),
    //新增 wenxue
    //替换:    LANGUAGE_OTHER,//外语
    //    LANGUAGE_OWN,//母语
    FAST_READ("fast_read",1,10,true,ImmutableMap.<String, Integer> builder()
        .put("literature",3)
        .build()),
    // replace liberary


    OCCULT("occult",2,5,false,ImmutableMap.<String, Integer> builder().build()),
    //神秘xue

    METEOROLOGY("meteorology",3,1,false,ImmutableMap.<String, Integer> builder().build()),
    //气象xue


    ASTRONOMY("astronomy",3,1,false,ImmutableMap.<String, Integer> builder().build()),
    //天文xue


    LAW("law",1,5,false,ImmutableMap.<String, Integer> builder()
        .put("anthropology",1)
        .put("forensics",1)
        .build()),//法律
    ANTHROPOLOGY("anthropology",2,1,false,ImmutableMap.<String, Integer> builder()
        .put("law",1)
        .build()),//人类xue
    FORENSICS("forensics",3,1,false,ImmutableMap.<String, Integer> builder()
        .put("law",1)
        .build()),//刑侦xueor司法科xue


    HAND_GUN("hand_gun",1,20,true,ImmutableMap.<String, Integer> builder().build()),
    //手枪

    SHOT_GUN("shot_gun",1,15,true,ImmutableMap.<String, Integer> builder().build()),
    //霰弹枪，修改了熟练度属xing

    SUBMACHINE_GUN("submachine_gun",2,10,true,ImmutableMap.<String, Integer> builder()
        .put("rifle_gun",1)
        .build()),//冲锋枪，修改了熟练度属xing

    RIFLEGUN("rifle_gun",2,10,true,ImmutableMap.<String, Integer> builder()
        .put("submachine_gun",1)
        .build()),//步枪，修改了熟练度属xing

    MACHINE_GUN("machine_gun",3,5,true,ImmutableMap.<String, Integer> builder()
        .put("submachine_gun",1)
        .build()),//machinegun

    HEAVY_WEAPONS("heavy_weapons",3,5,true,ImmutableMap.<String, Integer> builder()
        .put("submachine_gun",1)
        .build()),//
//    ARTILLERY(),

    THROWER("thrower",3,5,true,ImmutableMap.<String, Integer> builder().build()),
    //
    // FLAMETHROWER,

    DEMOLITIONS("demolitions",3,10,true,ImmutableMap.<String, Integer> builder()
        .put("chemistry",2)
        .build()),//爆破

    FIGHTING("fighting",0,25,true,ImmutableMap.<String, Integer> builder()
        .put("blunt",2)
        .put("cleave",2)
        .put("stab",2)
        .put("polearm",1)
        .put("exotic",1)
        .build()),//空手格斗



    BLUNT("blunt",1,10,true,ImmutableMap.<String, Integer> builder()
        .put("fighting",2)
        .build()),//锤锏

    CLEAVE("cleave",1,1,true,ImmutableMap.<String, Integer> builder()
        .put("fighting",2)
        .build()),//

    STAB("stab",1,1,true,ImmutableMap.<String, Integer> builder()
        .put("fighting",2)
        .build()),//

    POLEARM("polearm",2,1,true,ImmutableMap.<String, Integer> builder()
        .put("fighting",1)
        .build()),//枪棒

    EXOTIC("exotic",1,10,true,ImmutableMap.<String, Integer> builder()
        .put("fighting",1)
        .build()),//奇门

    //    AXE,//斧头
    //    CHAINSAW,//电锯
    //    DODGE,//闪避
    //    FIREARMS,//射击
    //    FLAIL,//连枷
    //    SPEAR,//
    //    SWORD,//
    //    WHIP,//鞭子
    //    GARROTE,//绞索

    FINE_ART("fine_art",1,5,false, ImmutableMap.<String, Integer> builder().build()),
    //美术

    PHOTOGRAPHY("photograph",1,1,true,ImmutableMap.<String, Integer> builder().build()),
    //摄影

    MUSIC("music",1,1,false,ImmutableMap.<String, Integer> builder().build()),
    //音乐

    ART_AND_CRAFT("art_and_fine",1,5,false, ImmutableMap.<String, Integer> builder().build()),
    //手工艺

    DISGUISE("disguise",1,5,false, ImmutableMap.<String, Integer> builder().build()),
    //乔装

    READ_LIPS("read_lips",3,1,false, ImmutableMap.<String, Integer> builder().build()),
    //读唇


    STEALTH("stealth",0,20,true,ImmutableMap.<String, Integer> builder()
        .put("sleight_of_hand",3)
        .build()),//潜行

    THROW("throw",0,20,true,ImmutableMap.<String, Integer> builder()
        .put("sleight_of_hand",1)
        .build()),//投掷

    SLEIGHT_OF_HAND("sleight_of_hand",1,10,true,ImmutableMap.<String, Integer> builder()
        .put("stealth",3)
        .put("throw",1)
        .put("locksmith",3)
        .put("forgery",2)
        .build()),//妙手

    LOCKSMITH("locksmith",2,1,true,ImmutableMap.<String, Integer> builder()
        .put("sleight_of_hand",3)
        .build()),//锁匠

    FORGERY("forgery",3,1,true,ImmutableMap.<String, Integer> builder()
        .put("sleight_of_hand",2)
        .build()),//

    SWIM("swim",0,20,false,ImmutableMap.<String, Integer> builder()
        .put("diving",1)
        .build()),//游泳

    DIVING("diving",3,20,false,ImmutableMap.<String, Integer> builder()
        .put("swim",1)
        .build()),//潜水

    JUMP("jump",0,20,false, ImmutableMap.<String, Integer> builder().build()),
    //跳跃
    BOW("bow",1,5,true, ImmutableMap.<String, Integer> builder().build()),
    //弓术,熟练改为5
    CLIMB("climb",2,20,false, ImmutableMap.<String, Integer> builder().build()),
    //
    RIDE("ride",1,5,true, ImmutableMap.<String, Integer> builder().build()),//

    DRIVE_AUTO("drive_auto",0,20,true,ImmutableMap.<String, Integer> builder()
        .put("operate_heavy_machinery",1)
        .build()),//汽车驾驶

    OPERATE_HEAVY_MACHINERY("operate_heavy_machinery",2,1,true,ImmutableMap.<String, Integer> builder()
        .put("drive_auto",1)
        .build()),//操作重型机械


    TRACK("track",1,10,false,ImmutableMap.<String, Integer> builder()
        .put("navigate",1)
        .build()),//
    NAVIGATE("navigate",2,10,true,ImmutableMap.<String, Integer> builder()
        .put("track",1)
        .build()),//领航
    SURVIVAL("survival",1,10,false,ImmutableMap.<String, Integer> builder().build()),//生存

    PERSUADE("persuade",0,10,false,ImmutableMap.<String, Integer> builder()
        .put("intimidate",1)
        .put("fast_talk",3)
        .build()),//说服
    INTIMIDATE("intimidate",0,15,false,ImmutableMap.<String, Integer> builder()
        .put("persuade",1)
        .build()),//恐吓
    FAST_TALK("fast_talk",1,5,false,ImmutableMap.<String, Integer> builder()
        .put("persuade",3)
        .put("acting",3)
        .build()),//话术
    CHARM("charm",1,15,false,ImmutableMap.<String, Integer> builder()
        .put("acting",2)
        .build()),//魅惑
    ACTING("charm",2,5,false,ImmutableMap.<String, Integer> builder()
        .put("fast_talk",3)
        .put("charm",2)
        .build()),//表演
    EMOTION_READING("emotion_reading",1,10,false,ImmutableMap.<String, Integer> builder().build()),
    //察言观色
//    CREDIT_RATING,//
//    CTHULHU_MYTHOS,//
//    LANGUAGE,//
//    PILOT,//驾驶
//    PSYCHOLOGY,//
//    SCIENCE,//


    LISTEN("listen",0,-1,true,ImmutableMap.<String, Integer> builder().build()),//听觉
    TOUCH("touch",0,-1,true,ImmutableMap.<String, Integer> builder().build()),//触觉
    SMELL("smell",0,-1,true,ImmutableMap.<String, Integer> builder().build()),//嗅觉
    VISION("vision",0,-1,true,ImmutableMap.<String, Integer> builder().build()),//视觉

//    LISTEN,//聆听
//    SPOT_HIDDEN,//侦查



    PROFILE("profile",4,1,false,ImmutableMap.<String, Integer> builder()
            .put("natural_world",1)
            .put("anthropology",2)
            .put("forensics",2)
            .put("track",1)
        .build()),//侧写，需要司法科学，博物学，人类学，领航

    LEAD("lead",4,4,false,ImmutableMap.<String, Integer> builder()
            .put("persuade",1)
            .put("intimidate",1)
            .put("fast_talk",1)
            .put("charm",1)
            .put("emotion_reading",1)
            .put("acting",1)
        .build()),//领导/雄辩，需要话术，说服，察言观色，表演和取悦


//    WARCRAFT("warcraft",4,1,true,ImmutableMap.<String, Integer> builder()
//            .put("polearm",1)
//            .put("rifle_gun",1)
//            .put("bow",1)
//            .put("meteorology",2)
//            .put("history",2)
//        .build()),//兵法



    ;


    private Map<String,Integer> skillLinked;
    private SkillNode skill;

    SkillEnum(String skillName, int rare, int skillDefaultValue, boolean skillActive,ImmutableMap<String,Integer> skillLinked) {
        this.skillLinked = skillLinked;
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
