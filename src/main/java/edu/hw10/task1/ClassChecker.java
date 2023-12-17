package edu.hw10.task1;

public class ClassChecker {
    public static boolean isByte(Class<?> fieldType) {
        return fieldType == Byte.class || fieldType == byte.class;
    }

    public static boolean isInteger(Class<?> fieldType) {
        return fieldType == Integer.class || fieldType == int.class;
    }

    public static boolean isLong(Class<?> fieldType) {
        return fieldType == Long.class || fieldType == long.class;
    }

    public static boolean isDouble(Class<?> fieldType) {
        return fieldType == Double.class || fieldType == double.class;
    }

    public static boolean isFloat(Class<?> fieldType) {
        return fieldType == Float.class || fieldType == float.class;
    }

    public static boolean isCharacter(Class<?> fieldType) {
        return fieldType == Character.class || fieldType == char.class;
    }

    public static boolean isBoolean(Class<?> fieldType) {
        return fieldType == Boolean.class || fieldType == boolean.class;
    }

    public static boolean isString(Class<?> fieldType) {
        return fieldType == String.class;
    }

    private ClassChecker() {
    }
}
