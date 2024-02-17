package ru.nsu.vetrov;

import java.util.Arrays;

/**
 * Class for checking the presence of non-prime numbers in an array of integers in parallel.
 */
public class PrimeCheckerParallelStream extends PrimeChecker {

    /**
     * Checks if the given array of numbers contains non-prime numbers using parallel processing.
     */
    public static boolean containsNonPrimeParallelStream(int[] numbers) {
        return Arrays.stream(numbers).parallel().anyMatch(PrimeChecker::notPrime);
    }
}
