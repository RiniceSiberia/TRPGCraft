package windmillbroken.trpgcraft.util;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.level.Level;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import net.minecraft.ChatFormatting;
import windmillbroken.trpgcraft.bean.dice.Dice;
import windmillbroken.trpgcraft.bean.dice.DiceTypeEnum;

/**
 * 使用IDEA编写
 *
 * @Author: DUELIST
 * @Time: 2022-07-10-22:22
 **/
public class TextUtil {

    private static final ChatFormatting[] fabulousness = new ChatFormatting[] { 
            ChatFormatting.RED, 
            ChatFormatting.GOLD, 
            ChatFormatting.YELLOW, 
            ChatFormatting.GREEN, 
            ChatFormatting.AQUA,
            ChatFormatting.BLUE, 
            ChatFormatting.LIGHT_PURPLE};

    public static String makeFabulous(String input) {
        return ludicrousFormatting(input, fabulousness, 1.6F, 1, 1);
    }

    private static final ChatFormatting[] sanic = new ChatFormatting[] { 
            ChatFormatting.BLUE,
            ChatFormatting.BLUE,
            ChatFormatting.BLUE, 
            ChatFormatting.BLUE, 
            ChatFormatting.WHITE, 
            ChatFormatting.BLUE,
            ChatFormatting.WHITE,
            ChatFormatting.WHITE,
            ChatFormatting.BLUE, 
            ChatFormatting.WHITE,
            ChatFormatting.WHITE,
            ChatFormatting.BLUE,
            ChatFormatting.RED,
            ChatFormatting.WHITE,
            ChatFormatting.GRAY,
            ChatFormatting.GRAY,
            ChatFormatting.GRAY,
            ChatFormatting.GRAY, 
            ChatFormatting.GRAY, 
            ChatFormatting.GRAY,
            ChatFormatting.GRAY,
            ChatFormatting.GRAY,
            ChatFormatting.GRAY,
            ChatFormatting.GRAY, 
            ChatFormatting.GRAY,
            ChatFormatting.GRAY,
            ChatFormatting.GRAY,
            ChatFormatting.GRAY,
            ChatFormatting.GRAY,
            ChatFormatting.GRAY };

    public static String makeSANIC(String input) {
        return ludicrousFormatting(input, sanic, 1.0F, 2, 1);
    }

    public static String ludicrousFormatting(String input, ChatFormatting[] colours, double delay, int step, int posStep) {
        StringBuilder sb = new StringBuilder(input.length() * 3);
        if (delay <= 0) {
            delay = 0.001;
        }
        Level level = Minecraft.getInstance().level;
        int offset = (int) (Math.floor(level != null ? level.getGameTime() : 0)/ delay) % colours.length;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            int col = ((i * posStep) + colours.length - offset) % colours.length;

            sb.append(colours[col].toString());
            sb.append(c);
        }

        return sb.toString();
    }



    public static Component diceListToComponent(List<Dice> diceList) {
        if (diceList.isEmpty()) {
            return new TextComponent("Null");
        }
        int constant = diceList.stream().filter(dice -> dice.getType() == DiceTypeEnum.CONSTANT).mapToInt(Dice::getDiceValue).sum();
        int bpNum = diceList.stream().filter(dice -> dice.getType() == DiceTypeEnum.BONUS).mapToInt(Dice::getDiceValue).sum()
                - diceList.stream().filter(dice -> dice.getType() == DiceTypeEnum.PENALTY).mapToInt(Dice::getDiceValue).sum();
        Map<Integer,Long> normalDiceMap = diceList.stream().filter(dice -> dice != null && dice.getType() == DiceTypeEnum.NORMAL)
                .collect(Collectors.groupingBy(Dice::getDiceValue,Collectors.counting()));
        List<Map.Entry<Integer,Long>> entries = normalDiceMap.entrySet().stream().sorted((e1, e2) -> e2.getKey().compareTo(e1.getKey())).collect(Collectors.toList());
        return new TextComponent(diceColorFormatting(entries,bpNum,constant));

    }

    public static String diceColorFormatting( List<Map.Entry<Integer,Long>> normalDiceMap,int bpNum,int constant){
        StringBuffer sb = new StringBuffer();
        if (bpNum > 0){
            sb.append(ChatFormatting.GOLD);
            sb.append(ChatFormatting.BOLD);
            sb.append("-".repeat(bpNum));
            sb.append(" ");
        }else if (bpNum < 0){
            sb.append(ChatFormatting.RED);
            sb.append(ChatFormatting.BOLD);
            sb.append("+".repeat(-bpNum));
            sb.append(" ");
        }
        sb.append(ChatFormatting.ITALIC);
        sb.append(ChatFormatting.WHITE);
        StringBuffer normalDiceStr = new StringBuffer();
        for (Map.Entry<Integer,Long> map:normalDiceMap){
            int diceValue =map.getKey();
            long diceCount = map.getValue();
            normalDiceStr.append(diceCount).append("D").append(diceValue).append("+");
        }
        if (constant <= 0){
            normalDiceStr.deleteCharAt(normalDiceStr.length()-1);
        }
        sb.append(normalDiceStr);
        if (constant > 0){
            sb.append(ChatFormatting.RESET);
            sb.append(ChatFormatting.BLUE);
            sb.append(constant);
        }
        return sb.toString();
    }
}
