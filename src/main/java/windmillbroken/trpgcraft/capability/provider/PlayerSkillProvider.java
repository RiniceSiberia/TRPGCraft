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
 *
 * 使用IDEA编写
 *
 * @Author: DUELIST
 * @Time: 2022-08-07-16:44
 * @Message: Have a good time!  :)
 **/
public class PlayerSkillProvider extends EntitySkillProvider implements ICapabilitySerializable<CompoundTag> {


    private DescriptionCapability descriptionCapability;
    public static final Capability<DescriptionCapability> DESCRIPTION_CAPABILITY
            = CapabilityManager.get(new CapabilityToken<DescriptionCapability>() {
    });


    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == SKILL_CAPABILITY) {
            return LazyOptional.of(this::getOrCreateSkillCapability).cast();
        }else if (cap == DESCRIPTION_CAPABILITY){
            return LazyOptional.of(this::getOrCreateDescriptionCapability).cast();
        }else if (cap == STAND_ATTRIBUTE_CAPABILITY){
            return LazyOptional.of(this::getOrCreateStandAttributeCapability).cast();
        }else {
            return LazyOptional.empty();
        }
    }




    @NotNull
    protected DescriptionCapability getOrCreateDescriptionCapability() {
        if (descriptionCapability == null) {
            descriptionCapability = new DescriptionCapabilityImpl();
        }
        return descriptionCapability;
    }



    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = super.serializeNBT();
        tag.put("description", getOrCreateDescriptionCapability().serializeNBT());
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        super.deserializeNBT(nbt);
        CompoundTag description = nbt.getCompound("description");
        getOrCreateDescriptionCapability().deserializeNBT(description);
    }

}
