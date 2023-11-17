package edu.hw4.task1;

import java.util.Objects;

public class ValidationError extends RuntimeException {
    private final ValidationError[] errors;

    public ValidationError(String message, ValidationError[] errors) {
        super(message);

        this.errors = errors;
    }

    public ValidationError[] getErrors() {
        return errors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ValidationError that = (ValidationError) o;
        return Objects.equals(getMessage(), that.getMessage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMessage());
    }
}
