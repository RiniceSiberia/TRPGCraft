package windmillbroken.trpgcraft.bean.dice;

/**
 *
 * 使用IDEA编写
 *
 * @Author: DUELIST
 * @Time: 2022-07-30-18:12
 * @Message: Have a good time!  :)
 **/
public enum DiceTypeEnum {
    NORMAL((byte) 0, "normal"),

    CONSTANT((byte) 1, "constant"),

    BONUS((byte) 2, "bouns"),

    PENALTY((byte) 3, "penalty");


    private final byte Id;
    private final String name;

    DiceTypeEnum(byte id, String name) {
        this.Id = id;
        this.name = name;
    }
    public static DiceTypeEnum getById(byte id) {
        for (DiceTypeEnum diceTypeEnum : DiceTypeEnum.values()) {
            if (diceTypeEnum.Id == id) {
                return diceTypeEnum;
            }
        }
        return NORMAL;
    }

    public byte getId() {
        return Id;
    }

    public String getName() {
        return name;
    }
}
