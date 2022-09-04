package windmillbroken.trpgcraft.util;


/**
 * @author user
 */
public class Utils {
    public static final String MOD_ID = "trpgcraft";
    public static final String ATTRIBUTE_NAME = "attribute."+MOD_ID+".name.";
    public static final String ITEM = "item."+MOD_ID+".";
    public static final String SHEET = MOD_ID + ".sheet.";
    public static final String SHEET_ATTRIBUTE = SHEET + "attribute.";
    public static final String MESSAGE = ".message";
    public static final String SKILL = MOD_ID + ".skill.";

    public static String message(String s){
        return MOD_ID +"."+ s + MESSAGE;
    }

}
