package windmillbroken.trpgcraft.bean.skill;

import net.minecraft.network.chat.Component;
import windmillbroken.trpgcraft.bean.dice.DiceImpl;
import windmillbroken.trpgcraft.bean.dice.Dice;
import windmillbroken.trpgcraft.bean.dice.DiceTypeEnum;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 使用IDEA编写
 *
 * @Author: DUELIST
 * @Time: 2022-08-07-1:55
 * @Message: Have a good time!  :)
 **/
public class SkillGraph {

    public static final int MAX_NUM = 512;
    public static final int MAX_DEPTH = 8;

    public static final int[] HUMAN_SKILL_STEP = {60,80,90};

    private Map<Integer,SkillNode> vertex = new LinkedHashMap<>();

    private int vertexNum = 0;
    private int edgeNum = 0;

    private Integer[][] edge = new Integer[MAX_NUM][MAX_NUM];
    //边和权重
//    List<Integer> isTrav = new ArrayList<>(MAX_NUM);
//    //保存顶点是否被访问过


    public SkillGraph() {

    }


    public static SkillGraph createFullSkillGraph() {
        SkillGraph skillGraph = new SkillGraph();
        for(SkillEnum skillEnum:SkillEnum.values()){
            if (skillEnum.getSkill().getSkillDefaultValue() == -1){
                Dice d3 = new DiceImpl(3, DiceTypeEnum.NORMAL);
                int r = 0;
                for (int i = 0;i < 3;i++){
                    r += d3.roll();
                }
                r *= 5;
                skillEnum.getSkill().setSkillDefaultValue(r);
            }
            skillGraph.appendNode(skillEnum.getSkill());
            for (Map.Entry<String,Integer> entry : skillEnum.getSkillLinked().entrySet()){
                String linkName = entry.getKey();
                Integer linkNodeId = skillGraph.getSkillIdByName(linkName);
                Integer thisId = skillGraph.getSkillIdByName(skillEnum.getSkill().getSkillName());
                Integer power = entry.getValue();
                skillGraph.linkNode(thisId,linkNodeId,power);
            }
        }
        return skillGraph;
    }

    public SkillNode getSkill(String skillName){
        for (Map.Entry<Integer,SkillNode> map : vertex.entrySet()){
            if (map.getValue().getSkillName().equals(skillName) || map.getValue().getSkillElseName().equals(skillName)){

                return map.getValue();
            }
        }
        return null;
    }

    public SkillNode getSkill(int id){
        return vertex.get(id);
    }



    public void appendNode(SkillNode skillNode) {
        for (int i = 0; i < MAX_NUM; i++) {
            if (!vertex.containsKey(i)) {
                this.vertex.put(i,skillNode);
                this.vertexNum++;
                return;
            }
        }
    }

    public boolean putNode(Integer id,SkillNode skillNode) {
            if (!vertex.containsKey(id)) {
                this.vertex.put(id,skillNode);
                this.vertexNum++;
                return true;
            }
        return false;
    }

    public boolean linkNode(Integer id1,Integer id2,Integer power) {
        if (vertex.containsKey(id1)&&vertex.containsKey(id2) && !id1.equals(id2)){
            edge[id1][id2] = power;
            edge[id2][id1] = power;
            edgeNum++;
            return true;
        }
        return false;
    }
    public boolean unLinkNode(Integer id1,Integer id2) {
        if (vertex.containsKey(id1)&&vertex.containsKey(id2) && !id1.equals(id2)){
            edge[id1][id2] = null;
            edge[id2][id1] = null;
            edgeNum--;
            return true;
        }
        return false;
    }

    public boolean removeNode(Integer skillNodeId) {
        if (vertex.containsKey(skillNodeId)){

            for (Integer[] e : edge) {
                for (Integer i : e) {
                    if ( i != null && i.equals(skillNodeId)){
                        e[i] = null;
                        edgeNum--;
                    }
                }
            }
            vertex.remove(skillNodeId);
            return true;
        }
        return false;
    }

    public Map<Integer,SkillNode> getVertex() {
        return vertex;
    }

    public Integer[][] getEdge() {
        return edge;
    }

    public List<Integer> dfs(int positionId, int findNodeId){
        //深度搜索，获得目标顶点周边的顶点
        if (edge[positionId][findNodeId] != null){
            List<Integer> result = new ArrayList<>();
            result.add(positionId);
            result.add(findNodeId);
            return result;
        }else {

            for (Integer skillNode : getArrayIndex(edge[positionId])) {
                var ret = dfs(skillNode,findNodeId);
                if (ret.size() > 0){
                    List<Integer> result = new ArrayList<>();
                    result.add(positionId);
                    result.addAll(ret);
                    return result;
                }
            }
            return new ArrayList<Integer>();
        }
    }

    public List<Integer> getUpSkillNode(int positionId){

        for (Integer skillNode : edge[positionId]) {
            if (vertex.get(skillNode).getRare() < vertex.get(positionId).getRare()){
                var ret = getUpSkillNode(skillNode);
                ret.add(skillNode);
                if (ret.size() > 0){
                    return ret;
                }
            }
        }
        return new ArrayList<Integer>();
    }

    public List<Integer> getBottomSkillNode(int rare){

        var ret = new ArrayList<Integer>();
        for (Integer skillNode : vertex.keySet()) {
            if(vertex.get(skillNode).getSkillMaxValue() >= HUMAN_SKILL_STEP[HUMAN_SKILL_STEP.length-1]){

                continue;
            }
            if (vertex.get(skillNode).getRare() == 0){

                ret.add(skillNode);
            }else if ((vertex.get(skillNode).getRare() > 0 && vertex.get(skillNode).getRare() <= rare) || rare < 0){


                var upSkill = getSkillNodesByIds(getUpSkillNode(skillNode));
                if (upSkill.size() > 0){

                    int power = 0;
                    int totalPower = 0;
                    for (Map.Entry<Integer,SkillNode> up : upSkill.entrySet()){

                        totalPower += edge[skillNode][up.getKey()];
                        if (up.getValue().getSkillValue() > 0){

                            power += edge[skillNode][up.getKey()];
                        }
                    }
                    if ((power > 0 && power == totalPower)||(vertex.get(skillNode).getRare() >= 4 && power >= 5)){

                        for (int i = 0; i < power; i++) {

                            ret.add(skillNode);
                        }
                    }
                }else {
                    //上方没节点就跳过
                    ret.add(skillNode);
                }
            }
        }
        return ret;
    }

    public int getSkillValueMax(String skillNodeName){
        if (vertex.containsKey(skillNodeName)) {
            return vertex.get(skillNodeName).getSkillMaxValue();
        }
        return -1;
    }

    public SkillGraph getFullSkillGraph(){
        SkillGraph skillGraph = new SkillGraph();
        for (int i =0;i < SkillEnum.values().length;i++){
            SkillEnum skillEnum = SkillEnum.values()[i];
            SkillNode skillNode = skillEnum.getSkill();
            Map<String,Integer> skillLinkedMap = skillEnum.getSkillLinked();
            skillGraph.appendNode(skillNode);
            for (Map.Entry<String, Integer> map:skillLinkedMap.entrySet()){
                skillGraph.linkNode(i,getSkillIdByName(map.getKey()),map.getValue());
            }
        }
        return skillGraph;
    }



    /**
        *      使用IDEA编写
        * @Author: DUELIST
        * @Time:  2022/8/10-22:11
        * @Param: 列表
        * @Message: 回传非空下标数组
     **/
    public List<Integer> getArrayIndex(Integer[] array){
        List<Integer> arrayIndex = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null && array[i] > 0){
                arrayIndex.add(i);
            }
        }
        return arrayIndex;
    }
/**
    *      使用IDEA编写
    * @Author: DUELIST
    * @Time:  2022/8/14-13:15
 **/
    public int getSkillIdByName(String skillNodeName){
        for (Map.Entry<Integer, SkillNode> entry : vertex.entrySet()) {
            if (skillNodeName.equals(entry.getValue().getSkillName())
                    || (entry.getValue().getSkillElseName() != null
                    && skillNodeName.equals(entry.getValue().getSkillElseName().getString()))){
                return entry.getKey();
            }
        }
        return -1;
    }
    /**
     *      使用IDEA编写
     * @Author: DUELIST
     * @Time:  2022/8/10-22:03
     * @Param: id列表
     **/
    public Map<Integer,SkillNode> getSkillNodesByIds(List<Integer> ids){
        List<Integer> list = ids.stream().distinct().collect(Collectors.toList());
        //去重
        Map<Integer,SkillNode> map = new HashMap<>(MAX_NUM);
        for (Integer i : list){
            if (vertex.containsKey(i)){
                map.put(i,vertex.get(i));
            }
        }
        return map;
    }

    public boolean skillRename(Integer skillNodeId, Component elseName){
        boolean b =true;
        for (Map.Entry<Integer, SkillNode> entry : vertex.entrySet()) {
            if (entry.getValue().getSkillName().equals(elseName.getString())
                    || (entry.getValue().getSkillElseName()!= null && entry.getValue().getSkillElseName().equals(elseName))){
                b = false;
                break;
            }
        }
        if (b){
            vertex.get(skillNodeId).setSkillElseName(elseName);
        }
        return b;
    }

    public int getVertexNum() {
        return vertexNum;
    }

    public int getEdgeNum() {
        return edgeNum;
    }

}
