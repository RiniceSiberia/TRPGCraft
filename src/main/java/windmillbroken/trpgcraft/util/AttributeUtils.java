package windmillbroken.trpgcraft.util;

import windmillbroken.trpgcraft.bean.dice.Dice;
import windmillbroken.trpgcraft.bean.dice.DiceImpl;
import windmillbroken.trpgcraft.bean.dice.DiceTypeEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * 使用IDEA编写
 *
 * @Author: DUELIST
 * @Time: 2022-07-29-16:17
 * @Message: Have a good time!  :)
 **/
public class AttributeUtils {

    private static final int A = 2;
    private static final int B = 65;
    private static final int C = 85;
    private static final int D = 125;
    private static final int E = 165;
    private static final int F = 205;




    public static int getBuild(int str,int siz){
        int s = str + siz;
        if (s >= A && s <B){
            return -2;
        }else if (s >= B && s <C){
            return -1;
        }else if (s >= C && s <D){
            return 0;
        }else if (s >= D && s <E){
            return 1;
        }else if (s >= E && s <F){
            return 2;
        }else if (s >= F){
            //例 206 -45 = 161 /80 = 2(java向下取整)
            int n = (int) (s-45)/80;
            return n+1;
        }else {
            return -3;
        }
    }

    public static List<Dice> buildToDb(int build){
        List<Dice> diceList = new ArrayList<>();
        switch (build){
            case -3:
                diceList.add(new DiceImpl(-100,DiceTypeEnum.CONSTANT));
            case -2:
                diceList.add(new DiceImpl(-2,DiceTypeEnum.CONSTANT));
                break;
            case -1:
                diceList.add(new DiceImpl(-1,DiceTypeEnum.CONSTANT));
                break;
            case 0:
                break;
            case 1:
                diceList.add(new DiceImpl(4,DiceTypeEnum.NORMAL));
                break;
            case 2:
                diceList.add(new DiceImpl(6,DiceTypeEnum.NORMAL));
                break;
            default:
                if (build > 3){
                    for (int i = 0; i < build - 1; i++) {
                        diceList.add(new DiceImpl(6,DiceTypeEnum.NORMAL));
                    }
                }
        }
        return diceList;
    }

}
