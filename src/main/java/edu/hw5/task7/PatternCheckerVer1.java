package edu.hw5.task7;

import java.util.regex.Pattern;

public class PatternCheckerVer1 {

    private final static String PATTERN1 = "^[01]{2}0[01]*$";
    private final static String PATTERN2 = "^([01])[01]+\\1$";
    private final static String PATTERN3 = "^[01]{1,3}$";

    public boolean hasLength3AndThirdSymbol0(String input) {
        Pattern pattern = Pattern.compile(PATTERN1);

        return pattern.matcher(input).matches();
    }

    public boolean hasSameFirstAndLastSymbol(String input) {
        Pattern pattern = Pattern.compile(PATTERN2);

        return pattern.matcher(input).matches();
    }

    public boolean hasLengthInRangeFrom1To3(String input) {
        Pattern pattern = Pattern.compile(PATTERN3);

        return pattern.matcher(input).matches();
    }
}
