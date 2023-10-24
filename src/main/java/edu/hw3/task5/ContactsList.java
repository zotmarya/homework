package edu.hw3.task5;

import java.util.ArrayList;
import java.util.Comparator;

public class ContactsList {


    public Object[] sortContacts(ArrayList<String> contactsList, String sortType) {
        Comparator<String> comparator = (firstContact, secondContact) -> {
            String[] firstName = firstContact.split(" ");
            String[] secondName = secondContact.split(" ");

            int compareSurnamesDifference = firstName[1].compareTo(secondName[1]);

            if (compareSurnamesDifference != 0) {
                return compareSurnamesDifference;
            } else {
                return firstName[0].compareTo(secondName[0]);
            }
        };

        contactsList.sort(comparator);

        return contactsList.toArray();
    }

}
