package edu.hw5.task3;

import java.time.LocalDate;
import java.util.Optional;
import java.util.regex.Matcher;

public class DMYSlashShortDateHandler extends DateHandler {
    private final static String DATE_PATTERN = "^(0?[1-9]|1\\d)/(0?[1-9]|[1-3]\\d)/(\\d{2}$)";

    public DMYSlashShortDateHandler() {
        super(DATE_PATTERN);
    }

    @SuppressWarnings("MagicNumber")
    @Override
    protected Optional<LocalDate> parse(Matcher matcher) {
        int year = 2000 + Integer.parseInt(matcher.group(3));
        int month = Integer.parseInt(matcher.group(2));
        int day = Integer.parseInt(matcher.group(1));

        return Optional.of(LocalDate.of(year, month, day));
    }
}
