package edu.hw3.task7;

import java.util.Comparator;

public interface NullComparator {
    Comparator<String> COMPARATOR = (String firstElement, String secondElement) -> {
        int difference;

        if (firstElement == null && secondElement == null) {
            difference = 0;
        } else if (firstElement == null) {
            difference = -1;
        } else if (secondElement == null) {
            difference = 1;
        } else {
            difference = firstElement.compareTo(secondElement);
        }

        return difference;
    };

}
