package windmillbroken.trpgcraft.capability.impl;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.TextComponent;
import windmillbroken.trpgcraft.bean.description.Description;
import windmillbroken.trpgcraft.capability.api.DescriptionCapability;
import windmillbroken.trpgcraft.bean.description.SexEnum;

import static windmillbroken.trpgcraft.bean.description.SexEnum.*;

/**
 * 使用IDEA编写
 *
 * @Author: DUELIST
 * @Time: 2022-08-21-21:13
 * @Message: Have a good time!  :)
 **/
public class DescriptionCapabilityImpl implements DescriptionCapability {

    private Description description;

    public static final String CHARACTER_NAME = "character_name";
    public static final String OCCUPATION = "occupation";
    public static final String ARCHETYPE = "archetype";
    public static final String RESIDENCE = "residence";
    public static final String BIRTH_PLACE = "birth_place";
    public static final String AGE = "age";
    public static final String PRONOUN = "pronoun";
    public static final String INTO = "into";

    public static final String STORY = "story";
    public static final String PERSONAL_DESCRIPTION = "personal_description";
    public static final String TRAITS = "traits";
    public static final String IDEOLOGY_AND_BELIEFS = "ideology_and_beliefs";
    public static final String INJURIES_AND_SCARS = "injuries_and_scars";
    public static final String SIGNIFICANT_PEOPLE = "significant_people";
    public static final String PHOBIAS_AND_MANIAS = "phobias_and_manias";
    public static final String MEANINGFUL_LOCATIONS = "meaningful_locations";

    public DescriptionCapabilityImpl(){
        this.description = new Description();
        this.description.setCharacterName(new TextComponent(""));
        this.description.setOccupation(new TextComponent(""));
        this.description.setArchetype(new TextComponent(""));
        this.description.setResidence(new TextComponent(""));
        this.description.setBirthPlace(new TextComponent(""));
        this.description.setAge(18);
        this.description.setPronoun(ELSE);
        this.description.setInto(ELSE);
        this.description.setStory(new TextComponent(""));
        this.description.setPersonalDescription(new TextComponent(""));
        this.description.setTraits(new TextComponent(""));
        this.description.setIdeologyAndBeliefs(new TextComponent(""));
        this.description.setInjuriesAndScars(new TextComponent(""));
        this.description.setSignificantPeople(new TextComponent(""));
        this.description.setPhobiasAndManias(new TextComponent(""));
        this.description.setMeaningfulLocations(new TextComponent(""));
    }

    @Override
    public Description getDescription() {
        return description;
    }

    @Override
    public void setDescription(Description description) {
        this.description = description;
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putString(CHARACTER_NAME,this.description.getCharacterName().getString());
        tag.putString(OCCUPATION,this.description.getOccupation().getString());
        tag.putString(ARCHETYPE,this.description.getArchetype().getString());
        tag.putString(RESIDENCE,this.description.getResidence().getString());
        tag.putString(BIRTH_PLACE,this.description.getBirthPlace().getString());
        tag.putInt(AGE,this.description.getAge());
        tag.putInt(PRONOUN,this.description.getPronoun().getId());
        tag.putInt(INTO,this.description.getInto().getId());
        tag.putString(STORY,this.description.getStory().getString());
        tag.putString(PERSONAL_DESCRIPTION,this.description.getPersonalDescription().getString());
        tag.putString(TRAITS,this.description.getTraits().getString());
        tag.putString(IDEOLOGY_AND_BELIEFS,this.description.getIdeologyAndBeliefs().getString());
        tag.putString(INJURIES_AND_SCARS,this.description.getInjuriesAndScars().getString());
        tag.putString(SIGNIFICANT_PEOPLE,this.description.getSignificantPeople().getString());
        tag.putString(PHOBIAS_AND_MANIAS,this.description.getPhobiasAndManias().getString());
        tag.putString(MEANINGFUL_LOCATIONS,this.description.getMeaningfulLocations().getString());
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        this.description.setCharacterName(new TextComponent(nbt.getString(CHARACTER_NAME)));
        this.description.setOccupation(new TextComponent(nbt.getString(OCCUPATION)));
        this.description.setArchetype(new TextComponent(nbt.getString(ARCHETYPE)));
        this.description.setResidence(new TextComponent(nbt.getString(RESIDENCE)));
        this.description.setBirthPlace(new TextComponent(nbt.getString(BIRTH_PLACE)));
        this.description.setAge(nbt.getInt(AGE));
        this.description.setPronoun(SexEnum.getById(nbt.getInt(PRONOUN)));
        this.description.setInto(SexEnum.getById(nbt.getInt(INTO)));
        this.description.setStory(new TextComponent(nbt.getString(STORY)));
        this.description.setPersonalDescription(new TextComponent(nbt.getString(PERSONAL_DESCRIPTION)));
        this.description.setTraits(new TextComponent(nbt.getString(TRAITS)));
        this.description.setIdeologyAndBeliefs(new TextComponent(nbt.getString(IDEOLOGY_AND_BELIEFS)));
        this.description.setInjuriesAndScars(new TextComponent(nbt.getString(INJURIES_AND_SCARS)));
        this.description.setSignificantPeople(new TextComponent(nbt.getString(SIGNIFICANT_PEOPLE)));
        this.description.setPhobiasAndManias(new TextComponent(nbt.getString(PHOBIAS_AND_MANIAS)));
        this.description.setMeaningfulLocations(new TextComponent(nbt.getString(MEANINGFUL_LOCATIONS)));
    }
}
