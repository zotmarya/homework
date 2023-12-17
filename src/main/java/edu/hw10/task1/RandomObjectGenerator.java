package edu.hw10.task1;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.RecordComponent;
import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RandomObjectGenerator {
    private static final Random RANDOM = new Random();
    private static final Logger LOGGER = LogManager.getLogger();
    private static final int UNICODE_RANGE = 65535;

    private static final int MAX_STRING_LEN = 12;
    private static final int MIN_STRING_LEN = 3;

    private static final double NULL_VALUE_PROBABILITY = 0.3;

    public <T> T nextObject(Class<T> objectClass, String factoryMethod) {
        T newObject = null;

        try {
            Field[] fields = objectClass.getDeclaredFields();
            Object[] objects = new Object[fields.length];

            Class<?>[] parametersClasses = getParametersClass(fields);

            Method method = objectClass.getDeclaredMethod(factoryMethod, parametersClasses);
            method.setAccessible(true);

            for (int i = 0; i < objects.length; i++) {
                objects[i] = generateValue(fields[i]);
            }

            newObject = (T) method.invoke(null, objects);
        } catch (Exception exception) {
            LOGGER.info(exception);
        }

        return newObject;
    }

    public <T> T nextObject(Class<T> objectClass) {
        T newObject = null;

        try {
            Constructor<T> constructor = objectClass.getDeclaredConstructor();
            constructor.setAccessible(true);

            newObject = generateObject(constructor);
        } catch (Exception exceptionNoArgs) {

            try {
                Constructor<T> constructor =
                    objectClass.getDeclaredConstructor(getParametersClass(objectClass.getDeclaredFields()));
                constructor.setAccessible(true);

                RecordComponent[] recordComponents = objectClass.getRecordComponents();

                if (recordComponents == null) {
                    newObject = generateObject(constructor);
                } else {
                    newObject = generateRecordObject(recordComponents, constructor);
                }

            } catch (Exception exceptionArgs) {
                LOGGER.info(exceptionArgs);
            }

            LOGGER.info(exceptionNoArgs);
        }

        return newObject;
    }

    private <T> T generateRecordObject(RecordComponent[] recordComponents, Constructor<T> constructor)
        throws Exception {
        Object[] objects = new Object[recordComponents.length];

        for (int i = 0; i < objects.length; i++) {
            RecordComponent component = recordComponents[i];

            Field field = constructor.getDeclaringClass().getDeclaredField(component.getName());
            field.setAccessible(true);

            objects[i] = generateValue(field);
        }

        return constructor.newInstance(objects);
    }

    private Class<?>[] getParametersClass(Field[] parameters) {
        Class<?>[] classes = new Class[parameters.length];

        for (int i = 0; i < classes.length; i++) {
            classes[i] = parameters[i].getType();
        }

        return classes;
    }

    private <T> T generateObject(Constructor<T> constructor) throws Exception {
        Class<T> objectClass = constructor.getDeclaringClass();
        T instance = constructor.newInstance();

        for (Field field : objectClass.getDeclaredFields()) {
            field.setAccessible(true);

            Object value = generateValue(field);

            field.set(instance, value);
        }

        return instance;
    }

    private Object generateValue(Field field) throws Exception {
        Object value;

        if (field.isAnnotationPresent(NotNull.class)) {
            value = generateRandomValue(field.getType(), field);
        } else {
            value = generateValueWithNullPossibility(field.getType(), field);
        }

        return value;
    }

    private Object generateRandomValue(Class<?> fieldType, Field field) {
        Object value = new Object();

        int lowerBound = Integer.MIN_VALUE;
        int upperBound = Integer.MAX_VALUE;

        if (field.isAnnotationPresent(Max.class)) {
            upperBound = field.getAnnotation(Max.class).value() + 1;
        }

        if (field.isAnnotationPresent(Min.class)) {
            lowerBound = field.getAnnotation(Min.class).value();
        }

        if (ClassChecker.isInteger(fieldType)) {
            value = RANDOM.nextInt(lowerBound, upperBound);
        } else if (ClassChecker.isLong(fieldType)) {
            value = RANDOM.nextLong(lowerBound, upperBound);
        } else if (ClassChecker.isByte(fieldType)) {
            if (lowerBound < Byte.MIN_VALUE) {
                lowerBound = Byte.MIN_VALUE;
            }

            if (upperBound > Byte.MAX_VALUE) {
                upperBound = Byte.MAX_VALUE;
            }

            value = RANDOM.nextInt(lowerBound, upperBound);

        } else if (ClassChecker.isFloat(fieldType)) {
            value = RANDOM.nextFloat(lowerBound, upperBound);
        } else if (ClassChecker.isDouble(fieldType)) {
            value = RANDOM.nextDouble(lowerBound, upperBound);
        } else if (ClassChecker.isCharacter(fieldType)) {
            value = RANDOM.nextInt(lowerBound, upperBound);
        } else if (ClassChecker.isBoolean(fieldType)) {
            upperBound = UNICODE_RANGE + 1;

            value = (char) RANDOM.nextInt(upperBound);
        } else if (ClassChecker.isString(fieldType)) {
            value = createRandomString();
        }

        return value;
    }

    private Object generateValueWithNullPossibility(Class<?> fieldType, Field field) {
        Object value;

        if (shouldReturnNull() && !fieldType.isPrimitive()) {
            value = null;
        } else {
            value = generateRandomValue(fieldType, field);
        }

        return value;
    }

    private boolean shouldReturnNull() {
        double chanceValue = RANDOM.nextDouble();

        return chanceValue <= NULL_VALUE_PROBABILITY;
    }

    private String createRandomString() {
        StringBuilder stringBuilder = new StringBuilder();

        int stringLength = RANDOM.nextInt(MIN_STRING_LEN, MAX_STRING_LEN + 1);

        for (int i = 0; i < stringLength; i++) {
            char symbol = (char) RANDOM.nextInt(0, UNICODE_RANGE + 1);

            stringBuilder.append(symbol);
        }

        return stringBuilder.toString();
    }

}
