package windmillbroken.trpgcraft.bean.dice;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;


public interface Dice {
    public int getDiceValue();
    public void setDiceValue(int diceValue);
    public void setType(DiceTypeEnum type);
    public DiceTypeEnum getType();
    public int roll();
}
