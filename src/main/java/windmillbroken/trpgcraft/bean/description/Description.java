package windmillbroken.trpgcraft.bean.description;

import net.minecraft.network.chat.Component;

/**
 * 使用IDEA编写
 *
 * @Author: DUELIST
 * @Time: 2022-08-22-16:42
 * @Message: Have a good time!  :)
 **/
public class Description {

    private Component characterName;
    //人名
    private Component occupation;
    //职业
    private Component archetype;
    //种族
    private Component residence;
    //当前住所
    private Component birthPlace;
    //出生地
    private int age;
    //年龄
    private SexEnum pronoun;
    //性别
    private SexEnum into;
    //性取向
    //我真是服了lgbt了

    private Component story;
    //个人小传
    private Component personalDescription;
    //个人描述
    private Component traits;
    //特征
    private Component ideologyAndBeliefs;
    //意识形态与信仰
    private Component injuriesAndScars;
    //残疾与疤痕
    private Component significantPeople;
    //重要之人
    private Component phobiasAndManias;
    //恐惧与厌恶
    private Component meaningfulLocations;
    //重要之地

    public Component getCharacterName() {
        return characterName;
    }

    public void setCharacterName(Component characterName) {
        this.characterName = characterName;
    }

    public Component getOccupation() {
        return occupation;
    }

    public void setOccupation(Component occupation) {
        this.occupation = occupation;
    }

    public Component getArchetype() {
        return archetype;
    }
    
    public void setArchetype(Component archetype) {
        this.archetype = archetype;
    }

    public Component getResidence() {
        return residence;
    }

    public void setResidence(Component residence) {
        this.residence = residence;
    }

    public Component getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(Component birthPlace) {
        this.birthPlace = birthPlace;
    }
    
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public SexEnum getPronoun() {
        return pronoun;
    }

    public void setPronoun(SexEnum pronoun) {
        this.pronoun = pronoun;
    }

    public SexEnum getInto() {
        return into;
    }
    
    public void setInto(SexEnum into) {
        this.into = into;
    }

    public Component getStory() {
        return story;
    }

    public void setStory(Component story) {
        this.story = story;
    }

    public Component getPersonalDescription() {
        return personalDescription;
    }
    
    public void setPersonalDescription(Component personalDescription) {
        this.personalDescription = personalDescription;
    }
    
    public Component getTraits() {
        return traits;
    }

    public void setTraits(Component traits) {
        this.traits = traits;
    }

    public Component getIdeologyAndBeliefs() {
        return ideologyAndBeliefs;
    }

    public void setIdeologyAndBeliefs(Component ideologyAndBeliefs) {
        this.ideologyAndBeliefs = ideologyAndBeliefs;
    }

    public Component getInjuriesAndScars() {
        return injuriesAndScars;
    }

    public void setInjuriesAndScars(Component injuriesAndScars) {
        this.injuriesAndScars = injuriesAndScars;
    }

    public Component getSignificantPeople() {
        return significantPeople;
    }

    public void setSignificantPeople(Component significantPeople) {
        this.significantPeople = significantPeople;
    }

    public Component getPhobiasAndManias() {
        return phobiasAndManias;
    }

    public void setPhobiasAndManias(Component phobiasAndManias) {
        this.phobiasAndManias = phobiasAndManias;
    }
    
    public Component getMeaningfulLocations() {
        return meaningfulLocations;
    }

    public void setMeaningfulLocations(Component meaningfulLocations) {
        this.meaningfulLocations = meaningfulLocations;
    }
}
