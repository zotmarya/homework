package edu.hw5.task3;

import java.time.LocalDate;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("MemberName")
public abstract class DateHandler {
    private final Pattern PATTERN;
    private DateHandler nextHandler;

    public DateHandler(String pattern) {
        this.PATTERN = Pattern.compile(pattern);
    }

    public DateHandler setNextHandler(DateHandler nextHandler) {
        this.nextHandler = nextHandler;

        return nextHandler;
    }

    public Optional<LocalDate> tryParse(String inputDate) {
        Matcher matcher = PATTERN.matcher(inputDate);

        if (matcher.matches()) {
            return parse(matcher);
        } else if (nextHandler != null) {
            return nextHandler.tryParse(inputDate);
        } else {
            return Optional.empty();
        }

    }

    protected abstract Optional<LocalDate> parse(Matcher matcher);
}
