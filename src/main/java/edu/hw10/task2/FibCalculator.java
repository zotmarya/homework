package edu.hw10.task2;

@FunctionalInterface
public interface FibCalculator {
    @Cache(persist = true)
    long fib(int number);
}
