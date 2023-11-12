package edu.hw5.task4;

import java.util.regex.Pattern;

public class PasswordChecker {
    public boolean isPasswordStrong(String password) {
        Pattern passwordPattern = Pattern.compile(".*[~!@#$%^&*|]+.*");

        return passwordPattern.matcher(password).matches();
    }
}
