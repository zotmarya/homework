package edu.hw1.task4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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

    @Test
    void fixString_WhenEmptyString_ReturnsEmptyString() {
        String mixedString = "";

        String fixedString = task4.fixString(mixedString);

        assertThat(fixedString).isEqualTo("");
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
