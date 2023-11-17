package edu.hw2.task1;

public sealed interface Expr {
    double evaluate();

    record Constant(double number) implements Expr {
        @Override
        public double evaluate() {
            return number;
        }
    }

    record Negate(Expr number) implements Expr {
        @Override
        public double evaluate() {
            return -number.evaluate();
        }
    }

    record Exponent(Expr base, double exponent) implements Expr {
        @Override
        public double evaluate() {
            return Math.pow(base.evaluate(), exponent);
        }
    }

    record Addition(Expr addend1, Expr addend2) implements Expr {
        @Override
        public double evaluate() {
            return addend1.evaluate() + addend2.evaluate();
        }
    }

    record Multiplication(Expr multiplicand, Expr multiplier) implements Expr {
        @Override
        public double evaluate() {
            return multiplicand.evaluate() * multiplier.evaluate();
        }
    }
}
