package edu.hw3.task2;

import java.util.ArrayList;

public class Clusterizer {
    public ArrayList<String> clusterize(String cluster) {

        if (cluster == null) {
            return null;
        }

        char[] symbols = cluster.toCharArray();

        ArrayList<String> arrayList = new ArrayList<>();

        for (int i = 0, firstBrackets = 0, beginning = 0; i < symbols.length; i++) {
            if (symbols[i] == '(') {
                firstBrackets++;
            } else if (symbols[i] == ')') {
                firstBrackets--;
            } else {
                if (firstBrackets == 0) {
                    beginning = i + 1;
                }
                continue;
            }

            if (firstBrackets == 0) {
                arrayList.add(new String(symbols, beginning, i - beginning + 1));
                beginning = i + 1;
            }
        }

        return arrayList;
    }
}
