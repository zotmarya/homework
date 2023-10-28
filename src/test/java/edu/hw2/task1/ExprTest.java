package edu.hw2.task1;

import edu.hw2.task1.Expr.Addition;
import edu.hw2.task1.Expr.Constant;
import edu.hw2.task1.Expr.Negate;
import edu.hw2.task1.Expr.Exponent;
import edu.hw2.task1.Expr.Multiplication;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class ExprTest {

    @ParameterizedTest
    @ValueSource(doubles = {1, 10, 40, -20, 0.5, -7.1})
    void evaluate_WhenConstantType_ReturnsConstant(double number) {
        Constant constant = new Constant(number);

        double result = constant.evaluate();

        assertThat(result).isEqualTo(number);
    }

    private static Stream<Arguments> constantAndResultOfNegatedNumber() {
        return Stream.of(
            arguments(new Constant(1), -1),
            arguments(new Constant(10), -10),
            arguments(new Constant(-33), 33),
            arguments(new Constant(4.2), -4.2),
            arguments(new Constant(-3.6), 3.6),
            arguments(new Constant(0), 0)
        );
    }

    @ParameterizedTest
    @MethodSource("constantAndResultOfNegatedNumber")
    void evaluate_WhenNegateType_ReturnsOppositeNumber(Expr number, double expectedNumber) {
        Negate negateNumber = new Negate(number);

        double result = negateNumber.evaluate();

        assertThat(result).isEqualTo(expectedNumber);
    }

    private static Stream<Arguments> constantExponentAndResultOfExponentNumber() {
        return Stream.of(
            arguments(new Constant(1), 10, 1),
            arguments(new Constant(10), 2, 100),
            arguments(new Constant(-33), 1, -33),
            arguments(new Constant(4.2), 0, 1),
            arguments(new Constant(-3.6), 2, 12.96),
            arguments(new Constant(0), 3, 0)
        );
    }

    @ParameterizedTest
    @MethodSource("constantExponentAndResultOfExponentNumber")
    void evaluate_WhenExponentType_ReturnsExponentOfNumber(Expr number, double exponent, double expectedNumber) {
        Exponent constant = new Exponent(number, exponent);

        double result = constant.evaluate();

        assertThat(result).isEqualTo(expectedNumber);
    }

    private static Stream<Arguments> twoConstantsAndResultOfAdditionNumber() {
        return Stream.of(
            arguments(new Constant(3), new Constant(10), 13),
            arguments(new Constant(22), new Constant(-9), 13),
            arguments(new Constant(-6), new Constant(50), 44),
            arguments(new Constant(9.4), new Constant(7.2), 16.6),
            arguments(new Constant(-12), new Constant(-33.2), -45.2),
            arguments(new Constant(0), new Constant(2.1), 2.1)
        );
    }

    @ParameterizedTest
    @MethodSource("twoConstantsAndResultOfAdditionNumber")
    void evaluate_WhenAdditionType_ReturnsAdditionOfNumbers(Expr number1, Expr number2, double expectedNumber) {
        Addition constant = new Addition(number1, number2);

        double result = constant.evaluate();

        assertThat(result).isEqualTo(expectedNumber);
    }

    private static Stream<Arguments> twoConstantsAndResultOfMultiplicationNumber() {
        return Stream.of(
            arguments(new Constant(2), new Constant(2), 4),
            arguments(new Constant(-5), new Constant(-6), 30),
            arguments(new Constant(0), new Constant(22), 0),
            arguments(new Constant(7), new Constant(-3), -21),
            arguments(new Constant(3.5), new Constant(2.4), 8.4),
            arguments(new Constant(4.2), new Constant(11.1), 46.62)
        );
    }

    @ParameterizedTest
    @MethodSource("twoConstantsAndResultOfMultiplicationNumber")
    void evaluate_WhenMultiplicationType_ReturnsMultiplicationOfNumbers(Expr number1, Expr number2, double expectedNumber) {
        Multiplication constant = new Multiplication(number1, number2);

        double result = constant.evaluate();

        assertThat(result).isEqualTo(expectedNumber);
    }

}
