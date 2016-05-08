package com.thirds.adl.util;

import org.jetbrains.annotations.NotNull;

public class Naming {

    @NotNull
    public static String camelCaseToNewlines(String text) {

        String result = "";
        for (int i = 0; i < text.length(); i++) {
            if (i != 0 && Character.isUpperCase(text.charAt(i))) {
                result += "\n";
            }
            result += text.charAt(i);
        }
        return result;
    }
}
