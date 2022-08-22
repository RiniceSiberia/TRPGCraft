package windmillbroken.trpgcraft.item.dices;

import windmillbroken.trpgcraft.bean.dice.DiceTypeEnum;

/**
 *
 * 使用IDEA编写
 *
 * @Author: DUELIST
 * @Time: 2022-07-31-17:43
 * @Message: Have a good time!  :)
 **/
public enum DiceEnum {
    D1(1, DiceTypeEnum.NORMAL),
    COIN(2,DiceTypeEnum.NORMAL),
    D3(3,DiceTypeEnum.NORMAL),
    D4(4,DiceTypeEnum.NORMAL),
    D6(6,DiceTypeEnum.NORMAL),
    D8(8,DiceTypeEnum.NORMAL),
    D10(10,DiceTypeEnum.NORMAL),
    D12(12,DiceTypeEnum.NORMAL),
    D20(20,DiceTypeEnum.NORMAL),
    D100(100,DiceTypeEnum.NORMAL),
    CONSTANT_DICE(2,DiceTypeEnum.CONSTANT),

    ;
    private final int diceValue;
    private final DiceTypeEnum diceTypeEnum;

    DiceEnum(int diceValue, DiceTypeEnum diceTypeEnum) {
        this.diceValue = diceValue;
        this.diceTypeEnum = diceTypeEnum;
    }

    public int getDiceValue() {
        return diceValue;
    }

    public DiceTypeEnum getDiceTypeEnum() {
        return diceTypeEnum;
    }
}
