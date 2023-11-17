package edu.hw5.task6;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;

public class SubsequenceDetectorTest {

    @ParameterizedTest
    @CsvSource({"abc,achfdbaabgabcaabg", "cat,jkslcogrparjnytlfp"})
    void isStringSubsequenceOfString_WhenGivenValidSubsequence_ReturnsTrue(String subsequence, String line) {
        SubsequenceDetector subsequenceDetector = new SubsequenceDetector();

        boolean result = subsequenceDetector.isStringSubsequenceOfString(subsequence, line);

        assertThat(result).isTrue();
    }

    @ParameterizedTest
    @CsvSource({"grf,achfdbaabgabcaabg", "pol,jkslcogrparjnytlfp"})
    void isStringSubsequenceOfString_WhenGivenInvalidSubsequence_ReturnsTrue(String subsequence, String line) {
        SubsequenceDetector subsequenceDetector = new SubsequenceDetector();

        boolean result = subsequenceDetector.isStringSubsequenceOfString(subsequence, line);

        assertThat(result).isFalse();
    }

    @Test
    void isStringSubsequenceOfString_WhenGivenNullValues_ReturnsFalse() {
        SubsequenceDetector subsequenceDetector = new SubsequenceDetector();

        boolean result = subsequenceDetector.isStringSubsequenceOfString(null, null);

        assertThat(result).isFalse();
    }
}
