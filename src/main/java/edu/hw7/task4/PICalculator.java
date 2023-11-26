package edu.hw7.task4;

import java.util.concurrent.ThreadLocalRandom;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("MagicNumber")
public class PICalculator {
    private static final Logger LOGGER = LogManager.getLogger();
    private int iterations;
    private int radius;
    private double multiTotalCount;
    private double multiCircleCount;

    public PICalculator(int iterations, int radius) {
        this.iterations = iterations;
        this.radius = radius;
    }

    public double calculatePiSingleThread() {
        double totalCount = 0;
        double circleCount = 0;

        int radiusSquared = radius * radius;

        for (int i = 0; i < iterations; i++) {
            int x = (int) (Math.random() * (radius + radius) + radius);
            int y = (int) (Math.random() * (radius + radius) + radius);

            double distance = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));

            if (distance < radiusSquared) {
                circleCount++;
            }

            totalCount++;
        }

        return 4.0 * (circleCount / totalCount);
    }

    public double calculatePiMultiThread(int numThreads) {
        Thread[] threads = new Thread[numThreads];
        long iterationsPerThread = iterations / numThreads;

        multiCircleCount = 0;
        multiTotalCount = 0;

        int radiusSquared = radius * radius;

        for (int i = 0; i < numThreads; i++) {
            threads[i] = new Thread(() -> {
                double totalCount = 0;
                double circleCount = 0;

                for (int j = 0; j < iterationsPerThread; j++) {
                    int x = ThreadLocalRandom.current().nextInt(-radius, radius + 1);
                    int y = ThreadLocalRandom.current().nextInt(-radius, radius + 1);

                    double distance = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));

                    if (distance < radiusSquared) {
                        circleCount++;
                    }

                    totalCount++;
                }

                multiCircleCount += circleCount;
                multiTotalCount += totalCount;
            });
        }

        for (Thread thread : threads) {
            thread.start();
        }

        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException exception) {
            return -1;
        }

        return 4.0 * (multiCircleCount / multiTotalCount);
    }

    public void printErrorValue() {
        int[] simulations = new int[] {10_000_000, 100_000_000, 1_000_000_000};
        int numThreads = Runtime.getRuntime().availableProcessors();
        int tmpIterations = iterations;

        double piActual = Math.PI;

        for (int i : simulations) {
            iterations = i;

            double piSingleThread = calculatePiSingleThread();
            double piMultiThread = calculatePiMultiThread(numThreads);

            double errorSingleThread = piSingleThread - piActual;
            double errorMultiThread = piMultiThread - piActual;

            LOGGER.info("Number of Simulations: " + i);
            LOGGER.info("Error (Single Thread): " + errorSingleThread);
            LOGGER.info("Error (Multi Thread): " + errorMultiThread);
        }

        iterations = tmpIterations;
    }

    public void printEfficiency(int[] numThreads) {
        for (int num : numThreads) {
            long singleThreadStartTime = System.nanoTime();
            calculatePiSingleThread();
            long singleThreadEndTime = System.nanoTime();
            double singleThreadExecutionTime = singleThreadEndTime - singleThreadStartTime;

            long multiThreadStartTime = System.nanoTime();
            calculatePiMultiThread(num);
            long multiThreadEndTime = System.nanoTime();
            double multiThreadExecutionTime = multiThreadEndTime - multiThreadStartTime;

            double efficiency = singleThreadExecutionTime / (num * multiThreadExecutionTime);

            LOGGER.info("Efficiency: " + efficiency);
        }
    }

}
