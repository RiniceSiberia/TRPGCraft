package windmillbroken.trpgcraft.bean.dice;

public interface Dice {
    public int getDiceValue();
    public void setDiceValue(int diceValue);
    public void setType(DiceTypeEnum type);
    public DiceTypeEnum getType();
    public int roll();
}
