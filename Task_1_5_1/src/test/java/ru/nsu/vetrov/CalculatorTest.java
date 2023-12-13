package ru.nsu.vetrov;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the calculator functionality.
 * This class contains various test cases to validate the functionality of the calculator.
 */
public class CalculatorTest {

    private static final double DELTA = 1e-6;

    /**
     * Tests addition operation.
     */
    @Test
    public void testAddition()
            throws UnsupportedCalculationException {
        assertEquals(3.0, PrefixEvaluator.evaluate("+ 1 2"), DELTA);
    }

    /**
     * Tests substraction operation.
     */
    @Test
    public void testSubtraction()
            throws UnsupportedCalculationException {
        assertEquals(-1.0, PrefixEvaluator.evaluate("- 1 2"), DELTA);
    }

    /**
     * Multiplication.
     */
    @Test
    public void testMultiplication()
            throws UnsupportedCalculationException {
        assertEquals(2.0, PrefixEvaluator.evaluate("* 1 2"), DELTA);
    }

    /**
     * Division.
     */
    @Test
    public void testDivision()
            throws UnsupportedCalculationException {
        assertEquals(0.5, PrefixEvaluator.evaluate("/ 1 2"), DELTA);
    }

    /**
     * Sin.
     */
    @Test
    public void testSin()
            throws UnsupportedCalculationException {
        assertEquals(Math.sin(Math.toRadians(30)),
                PrefixEvaluator.evaluate("sin 30"), DELTA);
    }

    /**
     * Cosin.
     */
    @Test
    public void testCosin()
            throws UnsupportedCalculationException {
        assertEquals(Math.cos(Math.toRadians(60)),
                PrefixEvaluator.evaluate("cos 60"), DELTA);
    }

    /**
     * Log.
     */
    @Test
    public void testLogarithm()
            throws UnsupportedCalculationException {
        assertEquals(Math.log(2), PrefixEvaluator.evaluate("log 2"), DELTA);
    }

    /**
     * SQRT.
     */
    @Test
    public void testSquareRoot()
            throws UnsupportedCalculationException {
        assertEquals(Math.sqrt(4), PrefixEvaluator.evaluate("sqrt 4"), DELTA);
    }

    /**
     * Power.
     */
    @Test
    public void testPower()
            throws UnsupportedCalculationException {
        assertEquals(Math.pow(2, 3), PrefixEvaluator.evaluate("pow 2 3"), DELTA);
    }

    /**
     * Tests division by zero.
     * Expects a DivisionByZeroException to be thrown.
     */
    @Test
    public void testDivisionByZero() {
        assertThrows(DivisionByZeroException.class, () -> {
            PrefixEvaluator.evaluate("/ 10 0");
        });
    }
}
