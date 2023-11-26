package edu.hw7.task2;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.LongStream;

public class FactorialCalculator {
    public BigInteger evaluateFactorial(int number) {
        BigInteger factorial = null;

        if (number >= 0) {
            List<BigInteger> numbers = LongStream.rangeClosed(1, number).mapToObj(BigInteger::valueOf).toList();

            factorial = numbers.parallelStream().reduce(BigInteger.ONE, BigInteger::multiply);
        }

        return factorial;
    }

}
