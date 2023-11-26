package edu.hw7.task1;

import java.util.concurrent.atomic.AtomicInteger;

public class Counter implements Runnable {
    public static final AtomicInteger COUNTER = new AtomicInteger();
    private Thread thread;

    @Override
    public void run() {
        incrementCounter();
    }

    public Counter() {
        thread = new Thread(this);
    }

    private void incrementCounter() {
        COUNTER.incrementAndGet();
    }

    public Thread getThread() {
        return thread;
    }
}
