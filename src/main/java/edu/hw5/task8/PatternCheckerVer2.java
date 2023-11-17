package edu.hw5.task8;

import java.util.regex.Pattern;

public class PatternCheckerVer2 {
    private final static String PATTERN1 = "^[01]([01]{2})*$";
    private final static String PATTERN2 = "^(0([01]{2})*|1[01]([01]{2})*)$";
    private final static String PATTERN3 = "^((1*01*){3})+$";
    private final static String PATTERN4 = "^(1(1{3}|10|0)[01]*|1|0[01]+)$";
    private final static String PATTERN5 = "^(1[01])*1?$";
    private final static String PATTERN6 = "^((10{2,})|(0{2,}10*)|(010+))$";
    private final static String PATTERN7 = "^(0*(1(?!1))*)+$";

    public boolean isOddLength(String input) {
        Pattern pattern = Pattern.compile(PATTERN1);

        return pattern.matcher(input).matches();
    }

    public boolean doesStartWith0AndOddOrStartsWith1AndEven(String input) {
        Pattern pattern = Pattern.compile(PATTERN2);

        return pattern.matcher(input).matches();
    }

    public boolean isDivisibleBy3(String input) {
        Pattern pattern = Pattern.compile(PATTERN3);

        return pattern.matcher(input).matches();
    }

    public boolean isAnyLineExcept11Or111(String input) {
        Pattern pattern = Pattern.compile(PATTERN4);

        return pattern.matcher(input).matches();
    }

    public boolean isEveryOddSymbol1(String input) {
        Pattern pattern = Pattern.compile(PATTERN5);

        return pattern.matcher(input).matches();
    }

    public boolean hasAtLeastTwo0AndAtMostOne1(String input) {
        Pattern pattern = Pattern.compile(PATTERN6);

        return pattern.matcher(input).matches();
    }

    public boolean doesNotHaveConsecutive1(String input) {
        Pattern pattern = Pattern.compile(PATTERN7);

        return pattern.matcher(input).matches();
    }
}
