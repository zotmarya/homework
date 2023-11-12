package edu.hw5.task3;

import java.time.LocalDate;
import java.util.Optional;
import java.util.regex.Matcher;

public class DMYSlashDateHandler extends DateHandler {
    public DMYSlashDateHandler() {
        super("^(0?[1-9]|1\\d)/(0?[1-9]|[1-3]\\d)/(\\d{4})$");
    }

    @SuppressWarnings("MagicNumber")
    @Override
    protected Optional<LocalDate> parse(Matcher matcher) {
        int year = Integer.parseInt(matcher.group(3));
        int month = Integer.parseInt(matcher.group(2));
        int day = Integer.parseInt(matcher.group(1));

        return Optional.of(LocalDate.of(year, month, day));
    }
}
