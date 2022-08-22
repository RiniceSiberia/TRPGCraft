package windmillbroken.trpgcraft.capability.api;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.INBTSerializable;
import windmillbroken.trpgcraft.bean.dice.Dice;

import java.util.List;

/**
 *
 * @Author: DUELIST
 * @Time: 2022-07-14-20:55
 * @Message: Have a good time!  :)
 **/
public interface DiceCapability extends INBTSerializable<CompoundTag> {
    List<Dice> getDice();
    int getDiceValue(int index);
    int getDiceValueMax();
    int getDiceValueMin();
    void setDice(List<Dice> dice);
    void addDice(Dice dice);
    Dice popDice();
    int roll();





}
