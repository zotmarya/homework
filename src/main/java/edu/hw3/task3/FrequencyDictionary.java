package edu.hw3.task3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FrequencyDictionary {

    public Map<Object, Integer> makeFrequencyDictionary(ArrayList<Object> strings) {
        if (strings == null) {
            return null;
        }

        Map<Object, Integer> hashMap = new HashMap<>();

        for (Object element : strings) {
            if (hashMap.containsKey(element)) {
                int value = hashMap.get(element);

                hashMap.put(element, ++value);
            } else {
                hashMap.put(element, 1);
            }
        }

        return hashMap;
    }
}
