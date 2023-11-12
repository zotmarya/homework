package edu.hw5.task8;

import java.util.regex.Pattern;

public class PatternCheckerVer2 {
    public boolean isOddLength(String input) {
        Pattern pattern = Pattern.compile("^[01]([01]{2})*$");

        return pattern.matcher(input).matches();
    }

    public boolean doesStartWith0AndOddOrStartsWith1AndEven(String input) {
        Pattern pattern = Pattern.compile("^(0([01]{2})*|1[01]([01]{2})*)$");

        return pattern.matcher(input).matches();
    }

    public boolean isDivisibleBy3(String input) {
        Pattern pattern = Pattern.compile("^((1*01*){3})+$");

        return pattern.matcher(input).matches();
    }

    public boolean isAnyLineExcept11Or111(String input) {
        Pattern pattern = Pattern.compile("^(1(1{3}|10|0)[01]*|1|0[01]+)$");

        return pattern.matcher(input).matches();
    }

    public boolean isEveryOddSymbol1(String input) {
        Pattern pattern = Pattern.compile("^(1[01])*1?$");

        return pattern.matcher(input).matches();
    }

    public boolean hasAtLeastTwo0AndAtMostOne1(String input) {
        Pattern pattern = Pattern.compile("^((10{2,})|(0{2,}10*)|(010+))$");

        return pattern.matcher(input).matches();
    }

    public boolean doesNotHaveConsecutive1(String input) {
        Pattern pattern = Pattern.compile("^(0*(1(?!1))*)+$");

        return pattern.matcher(input).matches();
    }
}
