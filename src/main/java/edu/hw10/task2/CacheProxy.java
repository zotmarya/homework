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
    private static final String RESULTS_FILE = "results.txt";
    private static final String METHODS_FILE = "methods.txt";
    private static Map<String, Object> objectMap;
    private static Map<String, Boolean> methodCacheMap;

    private static final Logger LOGGER = LogManager.getLogger();

    static {
        loadResultsMap();
        loadMethodsMap();
    }

    private static void loadResultsMap() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(PATH + RESULTS_FILE))) {
            objectMap = (Map<String, Object>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException exception) {
            objectMap = new HashMap<>();
        }
    }

    private static void loadMethodsMap() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(PATH + METHODS_FILE))) {
            methodCacheMap = (Map<String, Boolean>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException exception) {
            methodCacheMap = new HashMap<>();
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
        Object result;
        Boolean isCached = methodCacheMap.get(method.getName());

        if (isCached == null) {
            isCached = saveCacheMethodToMap(method);
        }

        if (isCached) {
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

        return result;
    }

    private synchronized void saveResultToMap(String key, Object value) {
        objectMap.put(key, value);

        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(PATH + RESULTS_FILE))) {
            outputStream.writeObject(objectMap);
        } catch (IOException exception) {
            LOGGER.info(exception);
        }
    }

    private synchronized Boolean saveCacheMethodToMap(Method method) {
        Cache cacheAnnotation = method.getAnnotation(Cache.class);
        boolean isCached = false;

        if (cacheAnnotation != null && cacheAnnotation.persist()) {
            isCached = true;
        }

        methodCacheMap.put(method.getName(), isCached);

        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(PATH + METHODS_FILE))) {
            outputStream.writeObject(methodCacheMap);
        } catch (IOException exception) {
            LOGGER.info(exception);
        }

        return isCached;
    }

    private String generateKey(Method method, Object[] args) {
        Arrays.sort(args, Comparator.comparingInt(Object::hashCode));

        return method.getName() + "-" + Arrays.hashCode(args);
    }
}
