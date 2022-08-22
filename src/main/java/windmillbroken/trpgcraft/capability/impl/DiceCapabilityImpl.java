package windmillbroken.trpgcraft.capability.impl;

import net.minecraft.nbt.CompoundTag;
import windmillbroken.trpgcraft.capability.api.DiceCapability;
import windmillbroken.trpgcraft.bean.dice.Dice;
import windmillbroken.trpgcraft.bean.dice.DiceImpl;
import windmillbroken.trpgcraft.bean.dice.DiceTypeEnum;
import windmillbroken.trpgcraft.util.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 *
 * @Author: DUELIST
 * @Time: 2022-07-15-12:38
 * @Message: Have a good time!  :)
 **/
public class DiceCapabilityImpl implements DiceCapability {



    public static final String TAG_VALUE_LIST = Utils.MOD_ID+"_" +"dice_value_list";
    public static final String TAG_TYPE_LIST = Utils.MOD_ID+"_"+"dice_type_list";

    private List<Dice> diceList;

    public DiceCapabilityImpl(List<Dice> diceList){
        this.diceList = diceList;
    }

    @Override
    public List<Dice> getDice() {
        //直接获取骰子列表
        return diceList;
    }


    @Override
    public int getDiceValue(int index){

        return diceList.get(index).getDiceValue();
    }

    @Override
    public int getDiceValueMax() {
        int max = 0;
        for (Dice dice : diceList) {
            max += dice.getDiceValue();
        }
        return max;
    }

    @Override
    public int getDiceValueMin() {
        int min = 0;
        for (Dice dice : diceList) {
            if (dice.getType() == DiceTypeEnum.CONSTANT){
                min += dice.getDiceValue();
            }else {
                min ++;
            }
        }

        return min;
    }

    @Override
    public void setDice(List<Dice> diceList) {
        //设置骰子列表
        this.diceList = diceList;
    }

    @Override
    public void addDice(Dice dice) {
        this.diceList.add(dice);
    }

    @Override
    public Dice popDice() {
        Dice dice;
        if (diceList.size() > 0){
            int index = diceList.size()-1;
            dice = diceList.get(index);
            diceList.remove(index);
        }else {
            dice = new DiceImpl(0, DiceTypeEnum.CONSTANT);
        }
        return dice;
    }

    @Override
    public int roll() {
        int tem = 0;
        int bpNum = diceList.stream().filter(dice -> dice.getType() == DiceTypeEnum.BONUS).mapToInt(Dice::getDiceValue).sum()
                - diceList.stream().filter(dice -> dice.getType() == DiceTypeEnum.PENALTY).mapToInt(Dice::getDiceValue).sum();
        //惩罚骰子和奖励骰子的抵消
        int rollNum = diceList.stream().mapToInt(Dice::roll).sum();
        while (bpNum != 0){
            tem = diceList.stream().filter(dice -> dice.getType() == DiceTypeEnum.BONUS).mapToInt(Dice::roll).sum();
            if (bpNum > 0){
                bpNum--;
                if (tem > rollNum){
                    rollNum = tem;
                }
            }else {
                bpNum++;
                if (tem < rollNum){
                    rollNum = tem;
                }
            }
        }


        return rollNum;
    }

    @Override
    public CompoundTag serializeNBT() {
        //把实体转为nbt形式
        final CompoundTag tag = new CompoundTag();
        List<Integer> d = new ArrayList<>();
        for (Dice dice : diceList) {
            d.add(dice.getDiceValue());
        }

        tag.putIntArray(TAG_VALUE_LIST,d);
        List<Byte> t = new ArrayList<>();
        for(Dice dice : diceList){
            t.add(dice.getType().getId());
        }

        tag.putByteArray(TAG_TYPE_LIST,t);
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        //把nbt转为实体
        List<Integer> d = Arrays.stream(nbt.getIntArray(TAG_VALUE_LIST)).boxed().collect(Collectors.toList());
        byte[] t = nbt.getByteArray(TAG_TYPE_LIST);
        List<Dice> dice = new ArrayList<>();
        for (int i = 0; i < t.length; i++) {
            byte type = t[i];
                    Dice d1 = new DiceImpl(d.get(i),DiceTypeEnum.getById(type));
                    dice.add(d1);
        }
        diceList = dice;
    }
}
