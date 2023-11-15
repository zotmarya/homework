package edu.hw5.task3;

import java.time.LocalDate;
import java.util.Optional;
import java.util.regex.Matcher;

public class DaysAgoDateHandler extends DateHandler {
    private final static String DATE_PATTERN = "^1 day ago|(1\\d+|[2-9]\\d+) days ago$";

    public DaysAgoDateHandler() {
        super(DATE_PATTERN);
    }

    @Override
    protected Optional<LocalDate> parse(Matcher matcher) {
        String inputDays = matcher.group(0);

        int days = Integer.parseInt(inputDays.substring(0, inputDays.indexOf(' ')));

        return Optional.of(LocalDate.now().minusDays(days));
    }
}
