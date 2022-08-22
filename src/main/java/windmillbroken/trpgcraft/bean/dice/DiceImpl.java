package windmillbroken.trpgcraft.bean.dice;

/**
 *
 * 使用IDEA编写
 *
 * @Author: DUELIST
 * @Time: 2022-07-30-15:47
 * @Message: Have a good time!  :)
 **/
public class DiceImpl implements Dice{
    private int diceValue;
    private DiceTypeEnum type;


    public DiceImpl(int diceValue, DiceTypeEnum type) {
        this.diceValue = diceValue;
        this.type = type;
    }

    @Override
    public int getDiceValue() {
        return this.diceValue;
    }

    @Override
    public void setDiceValue(int diceValue) {
        this.diceValue = diceValue;
    }

    @Override
    public DiceTypeEnum getType() {
        return type;
    }

    @Override
    public void setType(DiceTypeEnum type) {
        this.type = type;
    }

    @Override
    public int roll() {
        if (this.type == DiceTypeEnum.NORMAL){
            return (int) (Math.random() * diceValue + 1);
        }else if (this.type == DiceTypeEnum.CONSTANT){
            return this.diceValue;
        }else if (this.type == DiceTypeEnum.BONUS){
            return 0;
        }else if (this.type == DiceTypeEnum.PENALTY){
            return 0;
        }else {
            return 0;
        }
    }
}
