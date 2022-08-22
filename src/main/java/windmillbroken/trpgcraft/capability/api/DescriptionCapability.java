package windmillbroken.trpgcraft.capability.api;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.INBTSerializable;
import windmillbroken.trpgcraft.bean.description.Description;

/**
 * 使用IDEA编写
 *
 * @Author: DUELIST
 * @Time: 2022-08-22-12:12
 * @Message: Have a good time!  :)
 **/
public interface DescriptionCapability extends INBTSerializable<CompoundTag> {
    public Description getDescription();
    public void setDescription(Description description);
}
