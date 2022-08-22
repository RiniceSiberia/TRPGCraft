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
import windmillbroken.trpgcraft.capability.impl.DiceCapabilityImpl;
import windmillbroken.trpgcraft.capability.api.DiceCapability;

import java.util.ArrayList;


/**
 * @author DUELIST
 */
public class DiceProvider implements ICapabilitySerializable<CompoundTag> {

    public static final Capability<DiceCapability> DICE_CAPABILITY = CapabilityManager.get(new CapabilityToken<DiceCapability>() {
    });
    private DiceCapability diceCapability;


    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == DICE_CAPABILITY ) {
            return LazyOptional.of(this::getOrCreateCapability).cast();
        } else {
            return LazyOptional.empty();
        }
    }



    @NotNull
    protected DiceCapability getOrCreateCapability() {
        if (diceCapability == null) {
            diceCapability = new DiceCapabilityImpl(new ArrayList<>());
        }
        return diceCapability;
    }

    @Override
    public CompoundTag serializeNBT() {
        return getOrCreateCapability().serializeNBT();
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        getOrCreateCapability().deserializeNBT(nbt);
    }

}
