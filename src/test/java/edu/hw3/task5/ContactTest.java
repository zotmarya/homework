package edu.hw3.task5;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ContactTest {
    @Test
    void sortContacts_WhenGivenContactListAndAscSortType_ReturnsSortedList() {
        ArrayList<String> contacts = new ArrayList<>(Arrays.asList("John Locke", "Thomas Aquinas",
            "David Hume", "Rene Descartes"
        ));
        String sortType = "ASC";
        Contact[] expectedSortedContacts =
            new Contact[] {new Contact("Thomas", "Aquinas"), new Contact("Rene", "Descartes"),
                new Contact("David", "Hume"), new Contact("John", "Locke")};

        Contact[] resultList = Contact.sortContacts(contacts, sortType);

        assertThat(Arrays.compare(resultList, expectedSortedContacts)).isZero();
    }

    @Test
    void sortContacts_WhenGivenContactListAndDescSortType_ReturnsSortedList() {
        ArrayList<String> contacts = new ArrayList<>(Arrays.asList("Paul Erdos", "Leonhard Euler", "Carl Gauss"));
        String sortType = "DESC";
        Contact[] expectedSortedContacts =
            new Contact[] {new Contact("Carl", "Gauss"), new Contact("Leonhard", "Euler"),
                new Contact("Paul", "Erdos")};

        Contact[] resultList = Contact.sortContacts(contacts, sortType);

        assertThat(Arrays.compare(resultList, expectedSortedContacts)).isZero();
    }

    @Test
    void sortContacts_WhenGivenOnlyNames_ReturnsSortedList() {
        ArrayList<String> contacts = new ArrayList<>(Arrays.asList("Paul", "Leonhard", "Carl"));
        String sortType = "ASC";
        Contact[] expectedSortedContacts =
            new Contact[] {new Contact("Carl"), new Contact("Leonhard"), new Contact("Paul")};

        Contact[] resultList = Contact.sortContacts(contacts, sortType);

        assertThat(Arrays.compare(resultList, expectedSortedContacts)).isZero();
    }

    @Test
    void sortContacts_WhenGivenEmptyArray_ReturnsEmptyArray() {
        ArrayList<String> contacts = new ArrayList<>();
        String sortType = "ASC";

        Contact[] resultList = Contact.sortContacts(contacts, sortType);

        assertThat(resultList).isEmpty();
    }

    @Test
    void sortContacts_WhenGivenNull_ReturnsEmptyArray() {
        ArrayList<String> contacts = null;
        String sortType = "ASC";

        Contact[] resultList = Contact.sortContacts(contacts, sortType);

        assertThat(resultList).isEmpty();
    }
}
