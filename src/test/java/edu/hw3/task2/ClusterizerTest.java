package edu.hw3.task2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.assertj.core.api.Assertions.assertThat;

public class ClusterizerTest {

    private Clusterizer clusterizer;

    @BeforeEach
    void setUp() {
        clusterizer = new Clusterizer();
    }

    @Test
    void clusterize_WhenGivenString_ReturnsArrayOfClusterizedElements() {
        String givenString = "()()()";

        ArrayList<String> result = clusterizer.clusterize(givenString);

        assertThat(result).containsExactly("()", "()", "()");
    }
}
