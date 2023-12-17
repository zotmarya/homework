package edu.hw10.task2;

import java.io.File;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;

public class CacheProxyTest {
    private static final String FILE_PATH = "src/test/resources/hw10/results.txt";

    FibCalculator calculator;

    @BeforeEach
    void setUp() {
        calculator = number -> {
            long result;

            if (number < 1) {
                result = -1;
            } else if (number == 1) {
                result = 0;
            } else {
                long first = 0;
                long second = 1;
                long current = second;

                for (long i = 2; i < number; i++) {
                    current = first + second;
                    first = second;
                    second = current;
                }

                result = current;
            }

            return result;
        };
    }

    @ParameterizedTest
    @CsvSource({"1,0", "2,1", "3,1", "4,2", "5,3"})
    void create_WhenGivenInterface_ReturnsResultOfTheImplementedInterface(int position, long expectedNumber) {
        FibCalculator proxy = CacheProxy.create(calculator, FibCalculator.class);

        long result = proxy.fib(position);

        assertThat(result).isEqualTo(expectedNumber);
    }

    @Test
    void create_WhenGivenInterfaceAndNewArgument_SavesResultIntoMap() {
        FibCalculator proxy = CacheProxy.create(calculator, FibCalculator.class);
        File file = new File(FILE_PATH);
        file.deleteOnExit();
        long beforeFileSize = file.length();

        proxy.fib(10);

        long afterFileSize = file.length();

        assertThat(afterFileSize).isGreaterThan(beforeFileSize);
    }
}
