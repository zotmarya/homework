package edu.hw3.task3;

import java.util.ArrayList;
import java.util.HashMap;

public class FrequencyDictionary {

    public HashMap<String, Integer> makeFrequencyDictionary(ArrayList<String> strings) {
        HashMap<String, Integer> hashMap = new HashMap<>();

        for (int i = 0, size = strings.size(); i < size; i++) {
            String element = strings.get(i);

            if(hashMap.containsKey(element)){
                int value = hashMap.get(element);

                hashMap.put(element, ++value);
            } else {
                hashMap.put(element, 1);
            }
        }

        return hashMap;
    }
}
