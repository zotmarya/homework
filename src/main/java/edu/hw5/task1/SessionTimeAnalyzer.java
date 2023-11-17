package edu.hw5.task1;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SessionTimeAnalyzer {

    private final static String DATE_TIME_PATTERN = "(\\d{4}-\\d{2}-\\d{2}), (\\d{2}:\\d{2})";
    private final static String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm";

    public Duration calculateSessionTime(List<String> sessions) {
        Duration[] sessionsTime = new Duration[sessions.size()];

        for (int i = 0; i < sessionsTime.length; i++) {
            sessionsTime[i] = findSessionTime(sessions.get(i));
            if (sessionsTime[i] == null) {
                return null;
            }
        }

        Duration averageTime = sessionsTime[0];

        for (int i = 1; i < sessionsTime.length; i++) {
            averageTime = averageTime.plus(sessionsTime[i]);
        }

        averageTime = averageTime.dividedBy(sessionsTime.length);

        return averageTime;
    }

    private static Duration findSessionTime(String dateTime) {
        Pattern timePattern = Pattern.compile(DATE_TIME_PATTERN);
        Matcher matcher = timePattern.matcher(dateTime);

        if (!matcher.find()) {
            return null;
        }

        String date1 = matcher.group(1);
        String time1 = matcher.group(2);

        if (!matcher.find()) {
            return null;
        }

        String date2 = matcher.group(1);
        String time2 = matcher.group(2);

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
        LocalDateTime dateTime1 = LocalDateTime.parse(date1 + " " + time1, dateTimeFormatter);
        LocalDateTime dateTime2 = LocalDateTime.parse(date2 + " " + time2, dateTimeFormatter);

        return Duration.between(dateTime1, dateTime2);
    }
}
