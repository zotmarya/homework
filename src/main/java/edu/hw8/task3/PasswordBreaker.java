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

    private static final int SYMBOLS_AMOUNT = 4;
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String CHARSET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private MessageDigest messageDigest;
    private int[] charIndexes;
    private int currentCharPosition;
    private int passwordsAmount;
    private static final String SPLIT_REGEX = "\\\s+";
    private static final String MD5 = "MD5";

    public PasswordBreaker(List<String> usersInfo) {

        try {
            messageDigest = MessageDigest.getInstance(MD5);
        } catch (NoSuchAlgorithmException exception) {
            LOGGER.info(exception);
        }

        passwordsAmount = (int) Math.pow(CHARSET.length(), SYMBOLS_AMOUNT);

        charIndexes = new int[SYMBOLS_AMOUNT];
        userPasswordMap = new HashMap<>();
        hashUserMap = new HashMap<>();

        for (int i = 0, size = usersInfo.size(); i < size; i++) {
            String[] userHash = usersInfo.get(i).split(SPLIT_REGEX);

            hashUserMap.put(userHash[1], userHash[0]);
        }
    }

    public Map<String, String> singleThreadPasswordBreak() {
        for (int i = 0; i < passwordsAmount && !hashUserMap.isEmpty(); i++) {
            String password = nextPassword(i);

//            System.out.println("PASSWORD " + password);

            String hash = hashPasswordMD5(password, messageDigest);

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

        int part = passwordsAmount / threadsAmount;

        for (int i = 0; i < threadsAmount; i++) {
            int index = i;

            threads[i] = new Thread(() -> threadPasswordBreak(part, index));

            threads[i].start();
        }

        try {
            for (int i = 0; i < threadsAmount; i++) {
                threads[i].join();
            }
        } catch (InterruptedException exception) {
            LOGGER.info(exception);
        }

        return userPasswordMap;
    }

    public void threadPasswordBreak(int part, int threadNumber) {
        int startIndex = threadNumber * part;
        int endIndex = startIndex + part;

        try {
            MessageDigest md = MessageDigest.getInstance(MD5);

            for (int i = threadNumber * part; i < endIndex && !hashUserMap.isEmpty(); i++) {
                String password = nextPassword(i);

                String hash = hashPasswordMD5(password, md);

                if (hashUserMap.containsKey(hash)) {
                    String user = hashUserMap.remove(hash);
                    userPasswordMap.put(user, password);
                }
            }
        } catch (NoSuchAlgorithmException exception) {
            LOGGER.info(exception);
        }

    }

    private String nextPassword(int passwordNumber) {
        StringBuilder password = new StringBuilder();

        int tmpPassNum = passwordNumber;
        int charsAmount = CHARSET.length();

        for (int i = 0; i < SYMBOLS_AMOUNT; i++) {
            int index = tmpPassNum % charsAmount;
            tmpPassNum = (tmpPassNum - index) / charsAmount;

            password.append(CHARSET.charAt(index));
        }

        return password.toString();
    }

    private String hashPasswordMD5(String password, MessageDigest messageDigest) {

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
