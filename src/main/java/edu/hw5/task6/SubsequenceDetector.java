package edu.hw5.task6;

import java.util.regex.Pattern;

public class SubsequenceDetector {
    private final static String ANY_SYMBOL_PATTERN = ".*";

    public boolean isStringSubsequenceOfString(String subsequence, String line) {
        if (subsequence == null || line == null) {
            return false;
        }

        char[] symbols = subsequence.toCharArray();

        StringBuilder pattern = new StringBuilder();

        for (int i = 0; i < symbols.length; i++) {
            pattern.append(ANY_SYMBOL_PATTERN);
            pattern.append(symbols[i]);
            pattern.append(ANY_SYMBOL_PATTERN);
        }

        Pattern subsequencePattern = Pattern.compile(pattern.toString());

        return subsequencePattern.matcher(line).matches();
    }
}
