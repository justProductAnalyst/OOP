package ru.nsu.vetrov;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for checking the presence of non-prime numbers in an array of integers in parallel.
 */
public class PrimeCheckerParallel extends PrimeChecker {

    /**
     * Checks if the given array of numbers contains non-prime numbers using parallel processing.
     *
     * @param numbers     The array of integers to be checked.
     * @param threadCount The number of threads to be used for parallel processing.
     * @return true if a non-prime number is found, false otherwise.
     */
    public static boolean containsNonPrimeParallel(int[] numbers, int threadCount) {
        int chunkSize = (int) Math.ceil(numbers.length / (double) threadCount);
        List<Thread> threads = new ArrayList<>();
        BooleanWrapper nonPrimeFound = new BooleanWrapper(false);

        for (int i = 0; i < threadCount; i++) {
            int start = i * chunkSize;
            int end = Math.min(start + chunkSize, numbers.length);
            Thread thread = new Thread(() -> {
                for (int j = start; j < end; j++) {
                    if (notPrime(numbers[j])) {
                        synchronized (nonPrimeFound) {
                            nonPrimeFound.value = true;
                        }
                        return;
                    }
                }
            });
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return nonPrimeFound.value;
    }

    /**
     * A helper class to hold a boolean value which can be modified atomically.
     */
    private static class BooleanWrapper {
        volatile boolean value;

        /**
         * Constructs a new BooleanWrapper instance with the given initial value.
         *
         * @param value The initial boolean value.
         */
        BooleanWrapper(boolean value) {
            this.value = value;
        }
    }
}