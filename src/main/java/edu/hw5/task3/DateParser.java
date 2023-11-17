package edu.hw5.task3;

import java.time.LocalDate;
import java.util.Optional;

public class DateParser {

    @SuppressWarnings("MagicNumber")
    public Optional<LocalDate> parseDate(String date) {
        String inputDate = date.trim();

        DateHandler chain = new YMDDashDateHandler();

        chain.setNextHandler(new YMDDashShortDateHandler())
            .setNextHandler(new DMYSlashDateHandler())
            .setNextHandler(new DMYSlashShortDateHandler())
            .setNextHandler(new TTYDateHandler())
            .setNextHandler(new DaysAgoDateHandler());

        return chain.tryParse(inputDate);
    }

}
