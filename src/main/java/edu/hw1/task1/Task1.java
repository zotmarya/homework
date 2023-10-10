package edu.hw1.task1;

import java.util.regex.Pattern;

public class Task1 {

    // make a boundary for minutes value with 2 to 4 digits,
    // as 9999 minutes ~ 7 days, there are no videos that last so long
    private static final String PATTERN = "[0-9]{2,4}:[0-5]{1}[0-9]{1}";
    private static final int MINUTE_IN_SECONDS = 60;

    public int timeToSeconds(String videoTime) {

        if (Pattern.matches(PATTERN, videoTime)) {

            String[] numbersStr = videoTime.split(":");

            return Integer.valueOf(numbersStr[0]) * MINUTE_IN_SECONDS + Integer.valueOf(numbersStr[1]);

        } else {
            return -1;
        }

    }

}

