package edu.hw5.task3;

import java.time.LocalDate;
import java.util.Optional;
import java.util.regex.Matcher;

public class YMDDashDateHandler extends DateHandler {

    public YMDDashDateHandler() {
        super("^(\\d{4})-(0[1-9]|1\\d)-(0[1-9]|[1-3]\\d)$");
    }

    @SuppressWarnings("MagicNumber")
    @Override
    protected Optional<LocalDate> parse(Matcher matcher) {
        int year = Integer.parseInt(matcher.group(1));
        int month = Integer.parseInt(matcher.group(2));
        int day = Integer.parseInt(matcher.group(3));

        return Optional.of(LocalDate.of(year, month, day));
    }
}
