package edu.hw1.task4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import static org.assertj.core.api.Assertions.assertThat;

class Task4Test {

    private Task4 task4;

    @BeforeEach
    void setUp() {
        task4 = new Task4();
    }

    @Test
    void fixString_WhenMixedString_FixesString() {
        String mixedString = "hTsii  s aimex dpus rtni.g";

        String fixedString = task4.fixString(mixedString);

        assertThat(fixedString).isEqualTo("This is a mixed up string.");
    }

    @ParameterizedTest
    @EmptySource
    void fixString_WhenEmptyString_ReturnsEmptyString(String emptyString) {
        String fixedString = task4.fixString(emptyString);

        assertThat(fixedString).isEmpty();
    }

    @Test
    void fixString_WhenOneSymbolString_ReturnsSameString() {
        String mixedString = "2";

        String fixedString = task4.fixString(mixedString);

        assertThat(fixedString).isEqualTo("2");
    }

    @Test
    void fixString_WhenOddSymbolsString_FixesString() {
        String mixedString = "badce";

        String fixedString = task4.fixString(mixedString);

        assertThat(fixedString).isEqualTo("abcde");
    }

}
