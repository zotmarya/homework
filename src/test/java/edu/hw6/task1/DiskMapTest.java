package edu.hw6.task1;

import java.util.Collection;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DiskMapTest {
    private static final String FILE_PATH = "src/test/resources/hw6/diskmap.txt";

    private DiskMap diskMap;

    @BeforeEach
    void setUp() {
        diskMap = new DiskMap(FILE_PATH);
    }

    @Test
    void values_ReturnsKeySet() {
        Set<String> expectedKeySet = Set.of("cat", "dog", "horse", "rabbit");

        Set<String> resultSet = diskMap.keySet();

        assertThat(resultSet).containsExactlyInAnyOrderElementsOf(expectedKeySet);
    }

    @Test
    void keySet_ReturnsValue() {
        Set<String> expectedValueSet = Set.of("1", "2", "3", "4");

        Collection<String> resultSet = diskMap.values();

        assertThat(resultSet).containsExactlyInAnyOrderElementsOf(expectedValueSet);
    }

    @Test
    void put_SavesToFile() {
        int originalSize = diskMap.size();

        diskMap.put("bat", "5");

        int changedSize = diskMap.size();

        assertThat(changedSize).isGreaterThan(originalSize);

        diskMap.remove("bat");
    }
}
