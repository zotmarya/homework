package edu.hw3.task2;

import java.util.ArrayList;

public class Clusterizer {
    public ArrayList<String> clusterize(String string) {

        char[] symbols = string.toCharArray();

        ArrayList arrayList = new ArrayList();

        for (int i = 0, firstBrackets = 0, secondBrackets = 0, beginning = 0; i < symbols.length; i++) {
            if (symbols[i] == '(') {
                firstBrackets++;
            } else if (symbols[i] == ')') {
                secondBrackets++;
            }

            if (firstBrackets == secondBrackets) {
                arrayList.add(new String(symbols, beginning, i - beginning + 1));
                beginning = i + 1;
            }
        }

        return arrayList;
    }
}
