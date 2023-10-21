package edu.hw2.task4;

public record CallingInfo(String className, String methodName) {
    static CallingInfo callingInfo() {

        Throwable throwable = new Throwable();
        StackTraceElement[] stack = throwable.getStackTrace();

        int lastStackElement = 1;

        return new CallingInfo(stack[lastStackElement].getClassName(), stack[lastStackElement].getMethodName());
    }
}
