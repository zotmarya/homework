package edu.hw5.task7;

import java.util.regex.Pattern;

public class PatternCheckerVer1 {

    public boolean hasLength3AndThirdSymbol0(String input) {
        Pattern pattern = Pattern.compile("^[01]{2}0[01]*$");

        return pattern.matcher(input).matches();
    }

    public boolean hasSameFirstAndLastSymbol(String input) {
        Pattern pattern = Pattern.compile("^([01])[01]+\\1$");

        return pattern.matcher(input).matches();
    }

    public boolean hasLengthInRangeFrom1To3(String input) {
        Pattern pattern = Pattern.compile("^[01]{1,3}$");

        return pattern.matcher(input).matches();
    }
}
