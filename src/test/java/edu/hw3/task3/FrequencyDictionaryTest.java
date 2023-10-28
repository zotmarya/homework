package edu.hw3.task3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;

public class FrequencyDictionaryTest {

    private FrequencyDictionary frequencyDictionary;

    @BeforeEach
    void setUp() {
        frequencyDictionary = new FrequencyDictionary();
    }

    static Stream<Arguments> keysAndValues() {
        return Stream.of(
            Arguments.arguments(new ArrayList<>(List.of("a", "bb", "a", "bb")), Map.of("bb", 2, "a", 2)),
            Arguments.arguments(new ArrayList<>(List.of("this", "and", "that", "and")),
                Map.of("that", 1, "and", 2, "this", 1)),
            Arguments.arguments( new ArrayList<>(List.of("код", "код", "код", "bug")),
                Map.of("код", 3, "bug", 1)),
            Arguments.arguments(new ArrayList<>(List.of(1, 1, 2, 2)), Map.of(1, 2, 2, 2))
        );
    }

    @ParameterizedTest
    @MethodSource("keysAndValues")
    void makeFrequencyDictionary_WhenGivenStrings_ReturnsFrequencyHashMap(ArrayList<Object> givenList, Map expectedDictionary) {
        HashMap<Object, Integer> resultHashMap = frequencyDictionary.makeFrequencyDictionary(givenList);

        assertThat(resultHashMap).containsAllEntriesOf(expectedDictionary);
    }

    @Test
    void makeFrequencyDictionary_WhenGivenNull_ReturnsNull() {
        ArrayList<Object> givenList = null;

        HashMap<Object, Integer> resultHashMap = frequencyDictionary.makeFrequencyDictionary(givenList);

        assertThat(resultHashMap).isNull();
    }


    @Test
    void makeFrequencyDictionary_WhenGivenEmptyArray_ReturnsEmptyMap() {
        ArrayList<Object> givenList = new ArrayList<>();

        HashMap<Object, Integer> resultHashMap = frequencyDictionary.makeFrequencyDictionary(givenList);

        assertThat(resultHashMap).isEmpty();
    }
}
