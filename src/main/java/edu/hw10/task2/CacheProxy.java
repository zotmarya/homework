package edu.hw10.task2;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CacheProxy implements InvocationHandler {
    private Object currentObject;
    private static final String PATH = "src/test/resources/hw10/";
    private static final String FILE_NAME = "results.txt";
    private static Map<String, Object> objectMap;
    private static final Logger LOGGER = LogManager.getLogger();

    static {
        loadMap();
    }

    private static void loadMap() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(PATH + FILE_NAME))) {
            objectMap = (Map<String, Object>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException exception) {
            objectMap = new HashMap<>();
        }
    }

    public CacheProxy(Object currentObject) {
        this.currentObject = currentObject;
    }

    public static <T> T create(T object, Class<?> objectInterface) {
        return (T) Proxy.newProxyInstance(
            objectInterface.getClassLoader(),
            new Class[] {objectInterface},
            new CacheProxy(object)
        );
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;

        if (method.isAnnotationPresent(Cache.class)) {
            Cache cacheAnnotation = method.getAnnotation(Cache.class);
            if (cacheAnnotation.persist()) {
                String key = generateKey(method, args);

                if (objectMap.containsKey(key)) {
                    result = objectMap.get(key);
                } else {
                    result = method.invoke(currentObject, args);
                    saveResultToMap(key, result);
                }

            } else {
                result = method.invoke(currentObject, args);
            }
        }

        return result;
    }

    private void saveResultToMap(String key, Object value) {
        objectMap.put(key, value);

        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(PATH + FILE_NAME))) {
            outputStream.writeObject(objectMap);
        } catch (IOException exception) {
            LOGGER.info(exception);
        }
    }

    private String generateKey(Method method, Object[] args) {
        Arrays.sort(args, Comparator.comparingInt(Object::hashCode));

        return method.getName() + "-" + Arrays.hashCode(args);
    }
}
