package ru.nsu.vetrov;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;


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
            throws UnsupportedCalculationException, DivisionByZeroException {
        assertEquals(3.0, PrefixEvaluator.evaluate("+ 1 2"), DELTA);
    }

    /**
     * Tests substraction operation.
     */
    @Test
    public void testSubtraction()
            throws UnsupportedCalculationException, DivisionByZeroException {
        assertEquals(-1.0, PrefixEvaluator.evaluate("- 1 2"), DELTA);
    }

    /**
     * Multiplication.
     */
    @Test
    public void testMultiplication()
            throws UnsupportedCalculationException, DivisionByZeroException {
        assertEquals(2.0, PrefixEvaluator.evaluate("* 1 2"), DELTA);
    }

    /**
     * Division.
     */
    @Test
    public void testDivision()
            throws UnsupportedCalculationException, DivisionByZeroException {
        assertEquals(0.5, PrefixEvaluator.evaluate("/ 1 2"), DELTA);
    }

    /**
     * Sin.
     */
    @Test
    public void testSin()
            throws UnsupportedCalculationException, DivisionByZeroException {
        assertEquals(Math.sin(Math.toRadians(30)),
                PrefixEvaluator.evaluate("sin 30"), DELTA);
    }

    /**
     * Cosin.
     */
    @Test
    public void testCosin()
            throws UnsupportedCalculationException, DivisionByZeroException {
        assertEquals(Math.cos(Math.toRadians(60)),
                PrefixEvaluator.evaluate("cos 60"), DELTA);
    }

    /**
     * Log.
     */
    @Test
    public void testLogarithm()
            throws UnsupportedCalculationException, DivisionByZeroException {
        assertEquals(Math.log(2), PrefixEvaluator.evaluate("log 2"), DELTA);
    }

    /**
     * SQRT.
     */
    @Test
    public void testSquareRoot()
            throws UnsupportedCalculationException, DivisionByZeroException {
        assertEquals(Math.sqrt(4), PrefixEvaluator.evaluate("sqrt 4"), DELTA);
    }

    /**
     * Power.
     */
    @Test
    public void testPower()
            throws UnsupportedCalculationException, DivisionByZeroException {
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
