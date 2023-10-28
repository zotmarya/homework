package edu.hw3.task5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import org.jetbrains.annotations.NotNull;

public class Contact implements Comparable<Contact> {

    private String name;
    private String surname;

    public static Contact[] sortContacts(ArrayList<String> contactsList, String sortType) {
        if (contactsList == null) {
            return new Contact[] {};
        }

        Contact[] contacts = new Contact[contactsList.size()];

        for (int i = 0; i < contacts.length; i++) {
            String[] names = contactsList.get(i).trim().split("\\s+");
            if (names.length > 1) {
                contacts[i] = new Contact(names[0], names[1]);
            } else {
                contacts[i] = new Contact(names[0]);
            }
        }

        if ("DESC".equals(sortType)) {
            Arrays.sort(contacts, Comparator.reverseOrder());
        } else {
            Arrays.sort(contacts);
        }

        return contacts;
    }

    public Contact(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Contact(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(@NotNull Contact secondContact) {
        int compareNamesDifference;

        String firstContactName = this.surname;
        String secondContactName = secondContact.surname;

        if (this.surname == null) {
            firstContactName = this.name;
        }

        if (secondContact.surname == null) {
            secondContactName = secondContact.name;
        }

        compareNamesDifference = firstContactName.compareTo(secondContactName);

        if (this.surname != null && secondContact.surname != null && compareNamesDifference == 0) {
            compareNamesDifference = this.name.compareTo(secondContact.name);
        }

        return compareNamesDifference;
    }
}
