package edu.hw5.task4;

import java.util.regex.Pattern;

public class PasswordChecker {
    private final static String PASSWORD_PATTERN = ".*[~!@#$%^&*|]+.*";

    public boolean isPasswordStrong(String password) {
        Pattern passwordPattern = Pattern.compile(PASSWORD_PATTERN);

        return passwordPattern.matcher(password).matches();
    }
}
