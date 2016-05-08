package com.thirds.adl.util;

import org.jetbrains.annotations.NotNull;

public class Naming {

    /**
     * This method converts <pre>UpperCamelCase</pre> or <pre>lowerCamelCase</pre> text to:
     * <pre>
     * Camel
     * Case
     * With
     * Newlines
     * </pre>
     * @param text is the input text to be converted
     * @return the converted text
     */
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
