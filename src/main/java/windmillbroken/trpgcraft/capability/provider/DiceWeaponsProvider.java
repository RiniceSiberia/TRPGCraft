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
import windmillbroken.trpgcraft.capability.api.DiceCapability;
import windmillbroken.trpgcraft.capability.impl.DiceCapabilityImpl;

import java.util.ArrayList;

/**
 * 使用IDEA编写
 *
 * @Author: DUELIST
 * @Time: 2022-08-19-10:08
 * @Message: Have a good time!  :)
 **/
public class DiceWeaponsProvider  implements ICapabilitySerializable<CompoundTag> {

    public static final int DAMAGE_TYPE_ID = 0;
    public static final int ACCURACY_TYPE_ID = 1;

    private DiceCapability damageDice;

    private DiceCapability accuracyDice;

    public static final Capability<DiceCapability> DAMAGE = CapabilityManager.get(new CapabilityToken<DiceCapability>() {
    });
    public static final Capability<DiceCapability> ACCURACY = CapabilityManager.get(new CapabilityToken<DiceCapability>() {
    });

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == DAMAGE) {
            return LazyOptional.of(() -> this.getOrCreateCapability(DAMAGE_TYPE_ID)).cast();
        } else if (cap == ACCURACY) {
            return LazyOptional.of(() -> this.getOrCreateCapability(ACCURACY_TYPE_ID)).cast();
        }else {
            return LazyOptional.empty();
        }
    }


    @NotNull
    protected DiceCapability getOrCreateCapability(int type) {
        if (damageDice == null) {
            damageDice = new DiceCapabilityImpl(new ArrayList<>());
        }
        if (accuracyDice == null) {
            accuracyDice = new DiceCapabilityImpl(new ArrayList<>());
        }
        if (type == DAMAGE_TYPE_ID) {
            return damageDice;
        } else if (type == ACCURACY_TYPE_ID) {
            return accuracyDice;
        }else {
            return damageDice;
        }
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag damageTag = getOrCreateCapability(DAMAGE_TYPE_ID).serializeNBT();
        CompoundTag accuracyTag = getOrCreateCapability(ACCURACY_TYPE_ID).serializeNBT();
        CompoundTag tag = new CompoundTag();
        tag.put("damage", damageTag);
        tag.put("accuracy", accuracyTag);
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        CompoundTag damage = nbt.getCompound("damage");
        CompoundTag accuracy = nbt.getCompound("accuracy");
        getOrCreateCapability(DAMAGE_TYPE_ID).deserializeNBT(damage);
        getOrCreateCapability(ACCURACY_TYPE_ID).deserializeNBT(accuracy);
    }

}