package edu.hw8.task2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FixedThreadPool implements ThreadPool {
    private Thread[] threads;
    private int threadsAmount;

    private static final Logger LOGGER = LogManager.getLogger();

    private FixedThreadPool() {
    }

    private FixedThreadPool(int threadsAmount) {
        this.threadsAmount = threadsAmount;
        this.threads = new Thread[threadsAmount];
    }

    public static FixedThreadPool createFixedThreadPool(int threadsAmount) {
        return new FixedThreadPool(threadsAmount);
    }

    @Override
    public void start() {
        for (int i = 0; i < threadsAmount; i++) {
            threads[i].start();
        }

        try {
            for (int i = 0; i < threadsAmount; i++) {
                threads[i].join();
            }
        } catch (InterruptedException exception) {
            LOGGER.info(exception);
        }

    }

    @Override
    public void execute(Runnable runnable) {
        for (int i = 0; i < threadsAmount; i++) {
            threads[i] = new Thread(runnable);
        }
    }

    @Override
    public void close() throws Exception {
        for (int i = 0; i < threadsAmount; i++) {
            threads[i].interrupt();
        }
    }

}
