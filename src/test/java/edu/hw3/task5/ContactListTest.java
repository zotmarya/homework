package edu.hw3.task5;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

public class ContactListTest {

    private static Stream<Arguments> contactsAndSortedListArrays() {
        return Stream.of(
            Arguments.of((Object[]) new String[] {}),
            Arguments.of((Object[]) new String[] {})
        );
    }


    @ParameterizedTest
    @MethodSource
    void sortContacts_WhenGivenContactList_ReturnsSortedList(String contactList, String sortedList) {

    }
}
