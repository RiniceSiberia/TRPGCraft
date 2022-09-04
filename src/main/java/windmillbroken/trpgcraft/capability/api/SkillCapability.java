package windmillbroken.trpgcraft.capability.api;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.util.INBTSerializable;
import windmillbroken.trpgcraft.bean.skill.SkillGraph;
import windmillbroken.trpgcraft.bean.skill.SkillNode;

import java.util.List;
import java.util.Map;


public interface SkillCapability extends INBTSerializable<CompoundTag> {
    void playerRefreshSkill(Player player);
    List<Integer> rollSkillIds(int num, int edu, Entity entity);
    List<Integer> getHobbySkills();
    Map<Integer, SkillNode> getSkillsByIds(List<Integer> ids);
    boolean unlockSkill(int skillNodeId, int type);
    boolean addSkillPoint(String skillName,int point,int type);
    void setPoint(int point,int type);
    int getPoint(int type);
    SkillGraph getSkillGraph();
    void setSkillMap(SkillGraph skillGraph);
    int getSkillValue(String skillName);
    void setSkillValue(String skillName, int value);
    void appendSkill(SkillNode skillNode,Map<String,Integer> linkedSkill);

}
