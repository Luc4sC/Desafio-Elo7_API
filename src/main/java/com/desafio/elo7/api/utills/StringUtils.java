package com.desafio.elo7.api.utills;

public class StringUtils {

    public static boolean isValidText(String text){
        return text.contains(" ") || text.contains("/") || text.contains("\\") || text.length() > 20;
    }
}
