package windmillbroken.trpgcraft.bean.skill;

import net.minecraft.network.chat.Component;


public class SkillNode {

    private String skillName;
    private Component skillElseName;
    private int rare;

    private int skillValue;
    private int skillGrowValue;
    private int skillExtraValue;
    private int skillDefaultValue;
    private int skillMaxValue;
    private boolean skillActive;

    public SkillNode(String skillName, int rare, int skillValue, int skillGrowValue, int skillExtraValue, int skillDefaultValue,int skillMaxValue,Component skillElseName, boolean skillActive) {
        this.skillName = skillName;
        this.rare = rare;
        this.skillValue = skillValue;
        this.skillGrowValue = skillGrowValue;
        this.skillExtraValue = skillExtraValue;
        this.skillDefaultValue = skillDefaultValue;
        this.skillMaxValue = skillMaxValue;
        this.skillElseName = skillElseName;
        this.skillActive = skillActive;
    }

    public SkillNode() {
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public int getRare() {
        return rare;
    }

    public void setRare(int rare) {
        this.rare = rare;
    }

    public int getSkillValue() {
        return skillValue;
    }

    public void setSkillValue(int skillValue) {
        this.skillValue = skillValue;
    }

    public Component getSkillElseName() {
        return skillElseName;
    }

    public void setSkillElseName(Component skillElseName) {
        this.skillElseName = skillElseName;
    }

    public int getSkillDefaultValue() {
        return skillDefaultValue;
    }

    public void setSkillDefaultValue(int skillDefaultValue) {
        this.skillDefaultValue = skillDefaultValue;
    }

    public int getSkillMaxValue() {
        return skillMaxValue;
    }

    public void setSkillMaxValue(int skillMaxValue) {
        this.skillMaxValue = skillMaxValue;
    }

    public int getSkillGrowValue() {
        return skillGrowValue;
    }

    public void setSkillGrowValue(int skillGrowValue) {
        this.skillGrowValue = skillGrowValue;
    }

    public int getSkillExtraValue() {
        return skillExtraValue;
    }

    public void setSkillExtraValue(int skillExtraValue) {
        this.skillExtraValue = skillExtraValue;
    }

    public boolean isSkillActive() {
        return skillActive;
    }

    public void setSkillActive(boolean skillActive) {
        this.skillActive = skillActive;
    }
}
