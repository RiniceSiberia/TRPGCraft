package windmillbroken.trpgcraft.capability.provider;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import windmillbroken.trpgcraft.capability.api.DescriptionCapability;
import windmillbroken.trpgcraft.capability.api.SkillCapability;
import windmillbroken.trpgcraft.capability.api.StandAttributeCapability;
import windmillbroken.trpgcraft.capability.impl.DescriptionCapabilityImpl;
import windmillbroken.trpgcraft.capability.impl.SkillCapabilityImpl;
import windmillbroken.trpgcraft.capability.impl.StandAttributeCapabilityImpl;

/**
 * 使用IDEA编写
 *
 * @Author: DUELIST
 * @Time: 2022-08-22-14:15
 * @Message: Have a good time!  :)
 **/
public class EntitySkillProvider implements ICapabilitySerializable<CompoundTag> {
    private SkillCapability skillCapability;
    public static final Capability<SkillCapability> SKILL_CAPABILITY
            = CapabilityManager.get(new CapabilityToken<SkillCapability>() {
    });
    private StandAttributeCapability standAttributeCapability;
    public static final Capability<StandAttributeCapability> STAND_ATTRIBUTE_CAPABILITY
            = CapabilityManager.get(new CapabilityToken<StandAttributeCapability>() {
    });

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == SKILL_CAPABILITY) {
            return LazyOptional.of(this::getOrCreateSkillCapability).cast();
        }else if (cap == STAND_ATTRIBUTE_CAPABILITY){
            return LazyOptional.of(this::getOrCreateStandAttributeCapability).cast();
        }else {
            return LazyOptional.empty();
        }
    }

    @NotNull
    protected SkillCapability getOrCreateSkillCapability() {
        if (skillCapability == null) {
            skillCapability = new SkillCapabilityImpl();
        }
        return skillCapability;
    }

    @NotNull
    protected StandAttributeCapability getOrCreateStandAttributeCapability() {
        if (standAttributeCapability == null) {
            standAttributeCapability = new StandAttributeCapabilityImpl();
        }
        return standAttributeCapability;
    }



    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.put("skill", getOrCreateSkillCapability().serializeNBT());
        tag.put("stand_attribute", getOrCreateStandAttributeCapability().serializeNBT());
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        CompoundTag skill = nbt.getCompound("skill");
        CompoundTag standAttribute = nbt.getCompound("stand_attribute");
        getOrCreateSkillCapability().deserializeNBT(skill);
        getOrCreateStandAttributeCapability().deserializeNBT(standAttribute);
    }

}
