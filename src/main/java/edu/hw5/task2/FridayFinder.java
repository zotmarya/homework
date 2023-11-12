package edu.hw5.task2;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

public class FridayFinder {

    private final static int YEAR_UPPER_BOUND = 9999;
    private final static int THIRTEENTH = 13;
    private final static String START_OF_YEAR = "01-01-";

    public List<LocalDate> findFridays13thByYear(int year) {
        if (year < 1 || year > YEAR_UPPER_BOUND) {
            return null;
        }

        List<LocalDate> fridaysDates = new ArrayList<>();

        String startYear = START_OF_YEAR + year;
        String nextYear = START_OF_YEAR + (year + 1);

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        LocalDate currentDate = LocalDate.parse(startYear, dateTimeFormatter);
        LocalDate endDate = LocalDate.parse(nextYear, dateTimeFormatter);

        while (currentDate.isBefore(endDate)) {
            if (currentDate.getDayOfMonth() == THIRTEENTH && currentDate.getDayOfWeek().equals(DayOfWeek.FRIDAY)) {
                fridaysDates.add(currentDate);
            }

            currentDate = currentDate.plusDays(1);
        }

        return fridaysDates;
    }

    public LocalDate findFriday13thByDate(LocalDate date) {
        if (date == null) {
            return null;
        }

        TemporalAdjuster friday13Finder = TemporalAdjusters.ofDateAdjuster(
            date1 -> {
                LocalDate friday13Date = null;
                LocalDate tmpDate = date1;

                while (friday13Date == null) {
                    tmpDate = tmpDate.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));

                    if (tmpDate.getDayOfMonth() == THIRTEENTH) {
                        friday13Date = tmpDate;
                    }
                }

                return friday13Date;
            }
        );

        return date.with(friday13Finder);
    }
}
