package edu.hw3.task7;

import org.junit.jupiter.api.Test;
import java.util.TreeMap;
import static org.assertj.core.api.Assertions.assertThat;

public class NullComparatorTest {
    @Test
    void comparator_WhenTreeMapHasNull_HandlesNullArgument() {
        TreeMap<String, String> treeMap = new TreeMap<>(NullComparator.COMPARATOR);

        treeMap.put(null, "test");

        assertThat(treeMap.containsKey(null)).isTrue();
    }

}
