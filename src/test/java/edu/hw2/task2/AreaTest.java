package edu.hw2.task2;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;

public class AreaTest {

    static Arguments[] rectangles() {
        return new Arguments[] {
            Arguments.of(new Rectangle()),
            Arguments.of(new Square())
        };
    }

    @ParameterizedTest
    @MethodSource("rectangles")
    void calculateArea_WhenProperValues_CalculatesProperArea(Rectangle rect) {
        rect = rect.setWidth(10);
        rect = rect.setHeight(20);

        assertThat(rect.calculateArea()).isEqualTo(200.0);
    }

    @ParameterizedTest
    @MethodSource("rectangles")
    void calculateArea_WhenNegativeValue_CalculatesZeroArea(Rectangle rect) {
        rect = rect.setWidth(-5);
        rect = rect.setHeight(20);

        assertThat(rect.calculateArea()).isEqualTo(20);
    }

}
