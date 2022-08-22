package windmillbroken.trpgcraft.capability.impl;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.entity.player.Player;
import windmillbroken.trpgcraft.capability.api.SkillCapability;
import windmillbroken.trpgcraft.capability.api.StandAttributeCapability;
import windmillbroken.trpgcraft.capability.provider.PlayerSkillProvider;
import windmillbroken.trpgcraft.bean.dice.DiceImpl;
import windmillbroken.trpgcraft.bean.skill.CheckResultEnum;
import windmillbroken.trpgcraft.bean.skill.SkillGraph;
import windmillbroken.trpgcraft.bean.skill.SkillNode;
import windmillbroken.trpgcraft.bean.dice.Dice;
import windmillbroken.trpgcraft.bean.dice.DiceTypeEnum;

import java.util.*;


/**
    * @Author: DUELIST
    * @Time:  2022/8/14-11:09
    * @Message: skill capability
 **/
public class SkillCapabilityImpl implements SkillCapability {


    private SkillGraph skillGraph;
    private Integer professionSkillPoint;
    private Integer hobbySkillPoint;
    private Integer growSkillPoint;

    public static final int PROFESSION = 0;
    public static final int HOBBY = 1;
    public static final int GROW = 2;


    public SkillCapabilityImpl() {
        this.skillGraph = SkillGraph.createFullSkillGraph();
        this.professionSkillPoint = 0;
        this.hobbySkillPoint = 0;
        this.growSkillPoint = 0;
    }

    @Override
    public void playerRefreshSkill(Player player){
        int str = player.getCapability(PlayerSkillProvider.STAND_ATTRIBUTE_CAPABILITY)
                .resolve().map(StandAttributeCapability::getStr).orElse(0);
        int dex = player.getCapability(PlayerSkillProvider.STAND_ATTRIBUTE_CAPABILITY)
                .resolve().map(StandAttributeCapability::getDex).orElse(0);
        int app = player.getCapability(PlayerSkillProvider.STAND_ATTRIBUTE_CAPABILITY)
                .resolve().map(StandAttributeCapability::getApp).orElse(0);
        int inte = player.getCapability(PlayerSkillProvider.STAND_ATTRIBUTE_CAPABILITY)
                .resolve().map(StandAttributeCapability::getInte).orElse(0);
        int edu = player.getCapability(PlayerSkillProvider.STAND_ATTRIBUTE_CAPABILITY)
                .resolve().map(StandAttributeCapability::getEdu).orElse(0);
        int[] a = {str,dex,app,inte,edu};
        int max = 0;
        for (int value:a){
            if (value > max){
                max = value;
            }
        }
        this.professionSkillPoint = max * 2 + edu * 2;
        this.hobbySkillPoint = inte * 2;

        for (SkillNode skillNode : this.skillGraph.getVertex().values()){
            skillNode.setSkillValue(0);
            skillNode.setSkillGrowValue(0);
            skillNode.setSkillExtraValue(0);
        }
    }

    /**
     *      使用IDEA编写
     * @Author: DUELIST
     * @Time:  2022/8/7-17:28
     *
     **/
    @Override
    public List<Integer> rollSkillIds(int num, int edu) {
        int rare = -1;
        CheckResultEnum result = CheckResultEnum.SUCCESS;
        Dice dice = new DiceImpl(100, DiceTypeEnum.NORMAL);
        while (result.equals(CheckResultEnum.SUCCESS) || result.equals(CheckResultEnum.CRITICAL_SUCCESS)){
            rare++;
            result = CheckResultEnum.skillCheck(dice,edu);

        }
        List<Integer> skillNodeIdsGraph = this.skillGraph.getBottomSkillNode(rare);
        List<Integer> list = new ArrayList<>();
        if (num <= 0){
            return new ArrayList<>();
        }
        for (int i = 0;i < num;i++){
            int index = new DiceImpl(skillNodeIdsGraph.size(), DiceTypeEnum.NORMAL).roll();
            if (list.contains(skillNodeIdsGraph.get(index))){
                //如果抽取的节点已经包括了这个节点，略过本次结果，继续抽取
                i--;
                continue;
            }
            list.add(skillNodeIdsGraph.get(index));
        }
        return list;
    }

    @Override
    public List<Integer> getHobbySkills(){
        return this.skillGraph.getBottomSkillNode(-1);
    }

    @Override
    public Map<Integer, SkillNode> getSkillsByIds(List<Integer> ids){
        return skillGraph.getSkillNodesByIds(ids);
    }

    @Override
    public boolean unlockSkill(int skillNodeId, int type){
        SkillNode skillNode = skillGraph.getSkill(skillNodeId);
        if (!(skillNode == null
                || skillNode.getSkillValue() >= SkillGraph.HUMAN_SKILL_STEP[SkillGraph.HUMAN_SKILL_STEP.length-1])){
            int max = skillNode.getSkillMaxValue();

            for (int i = 0; i < SkillGraph.HUMAN_SKILL_STEP.length-1; i++){
                if(SkillGraph.HUMAN_SKILL_STEP[i] > max){

                    max = SkillGraph.HUMAN_SKILL_STEP[i];
                    if ((skillNode.getSkillValue() == 0)){

                        if (professionSkillPoint > 0 && type == PROFESSION){

                            skillNode.setSkillValue(1);
                            skillNode.setSkillMaxValue(max);
                            professionSkillPoint--;
                            return true;
                        }else if (hobbySkillPoint > 0 && type == HOBBY){

                            skillNode.setSkillValue(1);
                            skillNode.setSkillMaxValue(max);
                            professionSkillPoint--;
                            return true;
                        }
                    }else if (skillNode.getSkillValue() >0){
                        skillNode.setSkillMaxValue(max);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean addSkillPoint(String skillName, int point, int type){
        SkillNode skillNode = this.skillGraph.getSkill(skillName);
        if (skillNode != null){

            if (point + skillNode.getSkillValue() <= skillNode.getSkillMaxValue()){

                if (type == PROFESSION){
                    if (professionSkillPoint < point){

                        return false;
                    }
                    professionSkillPoint -= point;
                }else if (type == HOBBY){
                    if (hobbySkillPoint < point){

                        return false;
                    }
                    hobbySkillPoint -= point;
                }else if (type == GROW){
                    if (growSkillPoint < point){

                        return false;
                    }
                    growSkillPoint -= point;
                }
                if (point + skillNode.getSkillValue() <= skillNode.getSkillMaxValue()) {
                    skillNode.setSkillValue(skillNode.getSkillValue() + point);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void setPoint(int point, int type) {
        if (type == PROFESSION){
            this.professionSkillPoint = point;
        }else if (type == HOBBY){
            this.hobbySkillPoint = point;
        }else if (type == GROW){
            this.growSkillPoint = point;
        }
    }

    @Override
    public int getPoint(int type) {
        if (type == PROFESSION){
            return this.professionSkillPoint;
        }else if (type == HOBBY){
            return this.hobbySkillPoint;
        }else if (type == GROW){
            return this.growSkillPoint;
        }
        return 0;
    }

    @Override
    public SkillGraph getSkillGraph() {
        return this.skillGraph;
    }

    @Override
    public void setSkillMap(SkillGraph skillGraph) {
        this.skillGraph = skillGraph;
    }

    @Override
    public int getSkillValue(String skillName) {
        return this.skillGraph.getSkill(skillName).getSkillValue();
    }

    @Override
    public void setSkillValue(String skillName, int value) {
        this.skillGraph.getSkill(skillName).setSkillValue(value);
    }

    @Override
    public void appendSkill(SkillNode skillNode,Map<String,Integer> linkedSkill) {
        this.skillGraph.appendNode(skillNode);
        for(Map.Entry<String,Integer> entry:linkedSkill.entrySet()){
            this.skillGraph.linkNode(
                    this.skillGraph.getSkillIdByName(skillNode.getSkillName()),
                    this.skillGraph.getSkillIdByName(entry.getKey()),
                    entry.getValue());
        }
    }

    @Override
    public CompoundTag serializeNBT() {
        //将技能图写入nbt
        final CompoundTag tag = new CompoundTag();
        final ListTag skills = new ListTag();
        final ListTag links = new ListTag();
        final Collection<Integer> keys = this.skillGraph.getVertex().keySet();
        for (final Integer key : keys) {
            final CompoundTag skill = new CompoundTag();
            SkillNode s = this.skillGraph.getVertex().get(key);
            skill.putInt("skill_id",key);
            skill.putString("skill_name",s.getSkillName());
            if (s.getSkillElseName() != null){
                skill.putString("skill_else_name",s.getSkillElseName().getString());
            }
            skill.putInt("skill_rare",s.getRare());
            skill.putInt("skill_value",s.getSkillValue());
            skill.putInt("skill_grow_value",s.getSkillGrowValue());
            skill.putInt("skill_extra_value",s.getSkillExtraValue());
            skill.putInt("skill_default_value",s.getSkillDefaultValue());
            skill.putInt("skill_max_value",s.getSkillMaxValue());
            skill.putBoolean("skill_active",s.isSkillActive());

            skills.add(skill);
        }
        final Integer[][] edg = this.skillGraph.getEdge();

        for (int i = 0;i < SkillGraph.MAX_NUM ;i++){
            for (int j = 0;j < i;j++){
                if (edg[i][j] != null && edg[i][j] != 0){
                    final CompoundTag link = new CompoundTag();
                    link.putInt("skill_id_1",i);
                    link.putInt("skill_id_2",j);
                    link.putInt("power",edg[i][j]);
                    links.add(link);
                }
            }
        }
        tag.put("skill_map",skills);
        tag.put("skill_link",links);
        tag.putInt("skill_vertex_num",this.skillGraph.getVertexNum());
        tag.putInt("skill_edge_num",this.skillGraph.getEdgeNum());
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        final ListTag skills = nbt.getList("skill_map", Tag.TAG_COMPOUND);
        final ListTag links = nbt.getList("skill_link", Tag.TAG_COMPOUND);
        SkillGraph skillGraph = new SkillGraph();
        //將邊的數量和面數存入
        for (int i = 0;i < skills.size();i++){

            CompoundTag skill = skills.getCompound(i);
            SkillNode skillNode = new SkillNode();
            skillNode.setSkillName(skill.getString("skill_name"));
            if (skill.contains("skill_else_name")){
                skillNode.setSkillElseName(new TextComponent(skill.getString("skill_else_name")));
            }
            skillNode.setRare(skill.getInt("skill_rare"));
            skillNode.setSkillValue(skill.getInt("skill_value"));
            skillNode.setSkillGrowValue(skill.getInt("skill_grow_value"));
            skillNode.setSkillExtraValue(skill.getInt("skill_extra_value"));
            skillNode.setSkillDefaultValue(skill.getInt("skill_default_value"));
            skillNode.setSkillMaxValue(skill.getInt("skill_max_value"));
            skillNode.setSkillActive(skill.getBoolean("skill_active"));
            skillGraph.putNode(skill.getInt("skill_id"),skillNode);
        }
        //存入关联信息
        for (int i = 0;i < links.size();i++){
            CompoundTag link = links.getCompound(i);
            int id1 = link.getInt("skill_id_1");
            int id2 = link.getInt("skill_id_2");
            int power = link.getInt("power");
            skillGraph.linkNode(id1,id2,power);
        }
//        skillGraph.setVertexNum(nbt.getInt("skill_vertex_num"));
//        skillGraph.setEdgeNum(nbt.getInt("skill_edge_num"));
        this.skillGraph = skillGraph;
    }
}
