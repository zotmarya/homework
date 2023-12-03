package edu.hw8.task3;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PasswordBreaker {

    private Map<String, String> hashUserMap;
    private Map<String, String> userPasswordMap;

    private static final int MAX_SYMBOLS_AMOUNT = 4;
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String CHARSET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private MessageDigest messageDigest;
    private int[] charIndexes;
    private int currentCharPosition;
    private static final String SPLIT_REGEX = "\\\\s+";

    public PasswordBreaker(List<String> usersInfo) {

        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException exception) {
            LOGGER.info(exception);
        }

        charIndexes = new int[MAX_SYMBOLS_AMOUNT];
        hashUserMap = new HashMap<>();

        for (int i = 0, size = usersInfo.size(); i < size; i++) {
            String[] userHash = usersInfo.get(i).split(SPLIT_REGEX);

            hashUserMap.put(userHash[1], userHash[0]);
        }
    }

    public Map<String, String> singleThreadPasswordBreak() {
        while (!hashUserMap.isEmpty()) {
            String password = nextPassword();

//            System.out.println("PASSWORD " + password);

            String hash = hashPasswordMD5(password);

//            System.out.println("HASH " + hash);

            if (hashUserMap.containsKey(hash)) {
                String user = hashUserMap.remove(hash);
                userPasswordMap.put(user, password);
            }
        }

        return userPasswordMap;
    }

    public Map<String, String> multiThreadPasswordBreak(int threadsAmount) {
        Thread[] threads = new Thread[threadsAmount];

        for (int i = 0; i < threadsAmount; i++) {
            threads[i] = new Thread(() -> {
                singleThreadPasswordBreak();
            });
        }

        return userPasswordMap;
    }

    private String nextPassword() {
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < MAX_SYMBOLS_AMOUNT; i++) {
            password.append(CHARSET.charAt(charIndexes[i]));
        }

        increaseIndex();

        return password.toString();
    }

    private synchronized void increaseIndex() {
        if (charIndexes[currentCharPosition] == CHARSET.length() - 1) {
            charIndexes[currentCharPosition] = 0;

            if (currentCharPosition == MAX_SYMBOLS_AMOUNT - 1) {
                currentCharPosition = 0;
            } else {
                currentCharPosition++;
            }
        } else {
            charIndexes[currentCharPosition]++;
        }
    }

    private String hashPasswordMD5(String password) {
        messageDigest.update(password.getBytes());

        byte[] hashBytes = messageDigest.digest();

        StringBuilder hash = new StringBuilder();

        for (byte symbol : hashBytes) {
            hash.append(String.format("%02x", symbol));
        }

        return hash.toString();
    }

    public void setInfo(List<String> usersInfo) {
        userPasswordMap.clear();
        hashUserMap.clear();

        for (int i = 0, size = usersInfo.size(); i < size; i++) {
            String[] userHash = usersInfo.get(i).split(SPLIT_REGEX);

            hashUserMap.put(userHash[1], userHash[0]);
        }
    }
}
