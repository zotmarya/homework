package edu.hw7.task2;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.math.BigInteger;
import static org.assertj.core.api.Assertions.assertThat;

public class FactorialCounterTest {
    @ParameterizedTest
    @CsvSource({"5,120","10,3628800", "0,1", "14,87178291200"})
    void evaluateFactorial_WhenGivenNumber_ReturnsFactorialOfNumber(int number, long expecteedFactorial) {
        FactorialCalculator factorialCalculator = new FactorialCalculator();

        BigInteger resultFactorial = factorialCalculator.evaluateFactorial(number);

        assertThat(resultFactorial).isEqualTo(expecteedFactorial);
    }

    @Test
    void evaluateFactorial_WhenGivenNegativeNumber_ReturnsNull() {
        FactorialCalculator factorialCalculator = new FactorialCalculator();

        BigInteger resultFactorial = factorialCalculator.evaluateFactorial(-1);

        assertThat(resultFactorial).isNull();
    }
}
