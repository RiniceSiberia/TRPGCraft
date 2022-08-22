package windmillbroken.trpgcraft.item.weapons;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import windmillbroken.trpgcraft.capability.provider.DiceWeaponsProvider;
import windmillbroken.trpgcraft.tier.ModItemTier;
import windmillbroken.trpgcraft.capability.api.DiceCapability;
import windmillbroken.trpgcraft.bean.dice.Dice;


import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * 使用IDEA编写
 *
 * @Author: DUELIST
 * @Time: 2022-07-19-20:56
 * @Message: 近战武器的父类，
 **/
public abstract class DiceColdWeapon extends SwordItem {
    private final float dbRatio;
    //伤害加深倍率，默认为1.0;
    public DiceColdWeapon( float attackSpeed, Properties properties) {
        super(ModItemTier.DICE, 0, attackSpeed,properties);
        this.dbRatio = 1.0f;
    }
    public DiceColdWeapon( float attackSpeed, Properties properties, float dbRatio) {
        super(ModItemTier.DICE, 0, attackSpeed,properties);
        this.dbRatio = dbRatio;
    }


    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt) {
        return new DiceWeaponsProvider();
    }

    public Optional<DiceCapability> getCap(ItemStack stack, int type){
        if (type == DiceWeaponsProvider.DAMAGE_TYPE_ID){
            return stack.getCapability(DiceWeaponsProvider.DAMAGE).resolve();
        }else {
            return stack.getCapability(DiceWeaponsProvider.ACCURACY).resolve();
        }
    }

    public List<Dice> getDamageDice(ItemStack stack){
        return getCap(stack, DiceWeaponsProvider.DAMAGE_TYPE_ID).map(DiceCapability::getDice).orElse(null);
    }

    public List<Dice> getAccuracy(ItemStack stack){
        return getCap(stack, DiceWeaponsProvider.ACCURACY_TYPE_ID).map(DiceCapability::getDice).orElse(null);
    }

    public void setAccuracy(ItemStack stack,Dice dice){
        getCap(stack, DiceWeaponsProvider.ACCURACY_TYPE_ID).ifPresent(
                cap->cap.setDice(new ArrayList<>(List.of(dice)))
        );
    }

    public void addDamageDice(ItemStack stack,Dice dice){
        getCap(stack, DiceWeaponsProvider.DAMAGE_TYPE_ID).ifPresent(cap->cap.addDice(dice));
    }
    public Dice popDamageDice(ItemStack stack){
        return getCap(stack, DiceWeaponsProvider.DAMAGE_TYPE_ID).map(DiceCapability::popDice).orElse(null);
    }

    public int rollDamage(ItemStack stack){
        return getCap(stack, DiceWeaponsProvider.DAMAGE_TYPE_ID).map(DiceCapability::roll).orElse(0);
    }

    public int getDamageListSize(ItemStack itemStack){
        return getCap(itemStack, DiceWeaponsProvider.DAMAGE_TYPE_ID).map(cap->cap.getDice().size()).orElse(0);
    }

    public float getDbRatio() {
        return this.dbRatio;
    }
}
