package edu.hw5.task1;

import java.time.LocalTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SessionTimeAnalyzer {
    public static String calculateSessionTime(String startTime, String endTime) {
        Pattern timePattern = Pattern.compile("%(\\d{4}-\\d{2}-\\d{2}), (\\d{2}:\\d{2})%");



        return null;
    }

    public static void main(String[] args) {
        calculateSessionTime("2022-03-12, 20:20 - 2022-03-12, 23:50", "2022-04-01, 21:30 - 2022-04-02, 01:20");
    }
}
