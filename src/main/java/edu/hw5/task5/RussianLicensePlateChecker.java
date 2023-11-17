package edu.hw5.task5;

import java.util.regex.Pattern;

public class RussianLicensePlateChecker {
    private final static String PLATE_PATTERN = "^[ABCEHKMOPTXYАВСЕНКМОРТХУ]\\d{3}[ABCEHKMOPTXYАВСЕНКМОРТХУ]{2}\\d{3}$";

    public boolean isLicensePlateNumberValid(String licensePlateNumber) {
        if (licensePlateNumber == null) {
            return false;
        }

        Pattern rusLicensePattern =
            Pattern.compile(PLATE_PATTERN);

        return rusLicensePattern.matcher(licensePlateNumber).matches();
    }
}
