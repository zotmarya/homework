package edu.hw3.task3;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.assertj.core.api.Assertions.assertThat;

public class FrequencyDictionaryTest {

    private FrequencyDictionary frequencyDictionary;

    @BeforeEach
    void setUp() {
        frequencyDictionary = new FrequencyDictionary();
    }

    @Test
    void makeFrequencyDictionary_WhenGivenStrings_ReturnsFrequencyHashMap() {
        ArrayList<String> givenArray = new ArrayList<>(List.of("a", "bb", "a", "bb"));

        HashMap<String, Integer> resultHashMap = frequencyDictionary.makeFrequencyDictionary(givenArray);

        assertThat(resultHashMap).containsAllEntriesOf(Map.of("bb", 2, "a", 2));
    }

    @Test
    void makeFrequencyDictionary_WhenGivenStrings_ReturnsFrequencyHashMap2() {
        ArrayList<String> givenArray = new ArrayList<>(List.of("код", "код", "код", "bug"));

        HashMap<String, Integer> resultHashMap = frequencyDictionary.makeFrequencyDictionary(givenArray);

        assertThat(resultHashMap).containsAllEntriesOf(Map.of("код", 3, "bug", 1));
    }
}
