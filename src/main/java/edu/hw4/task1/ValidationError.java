package edu.hw4.task1;

public class ValidationError extends RuntimeException{
    private ValidationError[] errors;

    public ValidationError(String message, ValidationError[] errors) {
        super(message);

        this.errors = errors;
    }

    public ValidationError[] getErrors() {
        return errors;
    }
}
