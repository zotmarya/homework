package edu.hw1.task1;

import java.util.regex.Pattern;

public class Task1 {

    final String pattern = "[0-9]{2,}:[0-5]{1}[0-9]{1}";
    final int minuteInSeconds = 60;

    public int timeToSeconds(String videoTime) {

        if (Pattern.matches(pattern, videoTime)) {

            String[] numbersStr = videoTime.split(":");

            return Integer.valueOf(numbersStr[0]) * minuteInSeconds + Integer.valueOf(numbersStr[1]);

        } else {
            return -1;
        }

    }

}

