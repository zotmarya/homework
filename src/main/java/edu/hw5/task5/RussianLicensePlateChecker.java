package edu.hw5.task5;

import java.util.regex.Pattern;

public class RussianLicensePlateChecker {
    public boolean isLicensePlateNumberValid(String licensePlateNumber) {
        if (licensePlateNumber == null) {
            return false;
        }

        Pattern rusLicensePattern =
            Pattern.compile("^[ABCEHKMOPTXYАВСЕНКМОРТХУ]\\d{3}[ABCEHKMOPTXYАВСЕНКМОРТХУ]{2}\\d{3}$");

        return rusLicensePattern.matcher(licensePlateNumber).matches();
    }
}
