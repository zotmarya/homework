package edu.hw3.task2;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

public class ClusterizerTest {

    private Clusterizer clusterizer;

    @BeforeEach
    void setUp() {
        clusterizer = new Clusterizer();
    }

    static Stream<Arguments> clusters() {
        return Stream.of(
            Arguments.arguments("()()()", new String[] {"()", "()", "()"}),
            Arguments.arguments("((()))", new String[] {"((()))"}),
            Arguments.arguments("((()))(())()()(()())", new String[] {"((()))", "(())", "()", "()", "(()())"}),
            Arguments.arguments("((())())(()(()()))", new String[] {"((())())", "(()(()()))"}),
            Arguments.arguments("(abc)()()", new String[] {"(abc)", "()", "()"}),
            Arguments.arguments("(abc)abc()()", new String[] {"(abc)", "()", "()"})
        );
    }

    @ParameterizedTest
    @MethodSource("clusters")
    void clusterize_WhenGivenString_ReturnsArrayOfClusterizedElements(String cluster, String[] expectedClusters) {
        List<String> result = clusterizer.clusterize(cluster);

        assertThat(result).containsExactly(expectedClusters);
    }

    @Test
    void clusterize_WhenGivenNull_ReturnsNull() {
        String cluster = null;

        List<String> result = clusterizer.clusterize(cluster);

        assertThat(result).isNull();
    }

    @Test
    void clusterize_WhenGivenEmptyString_ReturnsEmptyArray() {
        String cluster = "";

        List<String> result = clusterizer.clusterize(cluster);

        assertThat(result).isEmpty();
    }
}
