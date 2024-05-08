/**
 * Test class.
 */
package ru.nsu.vetrov;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PrimeCheckerTest {

    /**
     * Tests the PrimeChecker.containsNonPrimeSequential method.
     */
    @Test
    public void testContainsNonPrimeSequential() {
        int[] primes = {2, 3, 5, 7, 11};
        int[] nonPrimes = {4, 6, 8, 9, 10};

        assertFalse(PrimeChecker.containsNonPrimeSequential(primes));
        assertTrue(PrimeChecker.containsNonPrimeSequential(nonPrimes));
    }

    /**
     * Tests the PrimeChecker.notPrime method.
     */
    @Test
    public void testNotPrime() {
        assertTrue(PrimeChecker.notPrime(4));
        assertFalse(PrimeChecker.notPrime(5));
    }

    /**
     * Tests the PrimeCheckerParallel.containsNonPrimeParallel method.
     */
    @Test
    public void testContainsNonPrimeParallel() {
        int[] primes = {2, 3, 5, 7, 11};
        int[] nonPrimes = {4, 6, 8, 9, 10};

        assertFalse(PrimeCheckerParallel.containsNonPrimeParallel(primes, 2));
        assertTrue(PrimeCheckerParallel.containsNonPrimeParallel(nonPrimes, 2));
    }

    /**
     * Tests the PrimeCheckerParallelStream.containsNonPrimeParallelStream method.
     */
    @Test
    public void testContainsNonPrimeParallelStream() {
        int[] primes = {2, 3, 5, 7, 11};
        int[] nonPrimes = {4, 6, 8, 9, 10};

        assertFalse(PrimeCheckerParallelStream.containsNonPrimeParallelStream(primes));
        assertTrue(PrimeCheckerParallelStream.containsNonPrimeParallelStream(nonPrimes));
    }
}
