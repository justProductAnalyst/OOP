package ru.nsu.vetrov;

/**
 * The PrimeChecker class provides methods to check if an array contains non-prime numbers sequentially.
 */
public class PrimeChecker {

    /**
     * Checks if the given array contains any non-prime numbers sequentially.
     *
     * @param numbers an array of integers to be checked
     * @return true if the array contains a non-prime number, false otherwise
     */
    public static boolean containsNonPrimeSequential(int[] numbers) {
        for (int number : numbers) {
            if (notPrime(number)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if a given number is not prime.
     *
     * @param number the number to be checked
     * @return true if the number is not prime, false otherwise
     */
    static boolean notPrime(int number) {
        if (number <= 1) {
            return true;
        }
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return true;
            }
        }
        return false;
    }
}
