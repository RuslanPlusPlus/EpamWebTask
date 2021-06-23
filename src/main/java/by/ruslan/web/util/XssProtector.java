package by.ruslan.web.util;
import java.util.Map;

public class XssProtector {
    private static final String SCRIPT_RIGHT_TAG= ">";
    private static final String SCRIPT_LEFT_TAG = "<";
    private static final String LESS_THAN_CHARACTER = "&lt;";
    private static final String GREATER_THAN_CHARACTER = "&gt;";
    private static final String ORDINARY_BRACKET = "'";
    private static final String AMPERSAND = "&";

    private static final Map<String, String> REPLACE_MAP = Map.<String, String>of(
            SCRIPT_LEFT_TAG, LESS_THAN_CHARACTER,
            SCRIPT_RIGHT_TAG, GREATER_THAN_CHARACTER,
            ORDINARY_BRACKET, "&amp;",
            AMPERSAND, "&apos;"
    );


    public static String filterXss(String param){
        String filteredString = param.replaceAll(SCRIPT_LEFT_TAG, REPLACE_MAP.get(SCRIPT_LEFT_TAG))
                .replaceAll(SCRIPT_RIGHT_TAG, REPLACE_MAP.get(SCRIPT_RIGHT_TAG))
                .replaceAll(ORDINARY_BRACKET, REPLACE_MAP.get(ORDINARY_BRACKET))
                .replaceAll(AMPERSAND, REPLACE_MAP.get(AMPERSAND));
        return filteredString;
    }
}
