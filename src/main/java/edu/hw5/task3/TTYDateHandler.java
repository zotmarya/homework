package edu.hw5.task3;

import java.time.LocalDate;
import java.util.Optional;
import java.util.regex.Matcher;

public class TTYDateHandler extends DateHandler {
    private final static String DATE_PATTERN = "^tomorrow|today|yesterday$";

    public TTYDateHandler() {
        super(DATE_PATTERN);
    }

    @Override
    protected Optional<LocalDate> parse(Matcher matcher) {
        LocalDate requestedDate = null;

        String date = matcher.group(0);

        if ("tomorrow".equals(date)) {
            requestedDate = LocalDate.now().plusDays(1);
        } else if ("today".equals(date)) {
            requestedDate = LocalDate.now();
        } else if ("yesterday".equals(date)) {
            requestedDate = LocalDate.now().minusDays(1);
        }

        return Optional.of(requestedDate);
    }
}
