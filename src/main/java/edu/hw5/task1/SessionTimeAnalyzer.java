package edu.hw5.task1;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SessionTimeAnalyzer {
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
        Pattern timePattern = Pattern.compile("(\\d{4}-\\d{2}-\\d{2}), (\\d{2}:\\d{2})");
        Matcher matcher = timePattern.matcher(dateTime);

        if (!matcher.hasMatch()) {
            return null;
        }

        matcher.find();
        String date1 = matcher.group(1);
        String time1 = matcher.group(2);

        matcher.find();
        String date2 = matcher.group(1);
        String time2 = matcher.group(2);

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime1 = LocalDateTime.parse(date1 + " " + time1, dateTimeFormatter);
        LocalDateTime dateTime2 = LocalDateTime.parse(date2 + " " + time2, dateTimeFormatter);

        return Duration.between(dateTime1, dateTime2);
    }
}
