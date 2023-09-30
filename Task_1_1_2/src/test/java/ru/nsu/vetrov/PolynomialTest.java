package ru.nsu.vetrov;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

/**
 * Test class for the Polynomial class.
 */
public class PolynomialTest {

    /**
     * Tests the addition of two polynomials.
     */
    @Test
    public void testAddition() {
        Polynomial p1 = new Polynomial(new int[]{4, 3, 6, 7});
        Polynomial p2 = new Polynomial(new int[]{3, 2, 8});
        Polynomial result = p1.plus(p2.differentiate(1));

        Polynomial expected = new Polynomial(new int[]{6, 19, 6, 7});
        assertEquals(expected, result);
    }

    /**
     * Tests the subtraction of two polynomials.
     */
    @Test
    public void testSubtraction() {
        Polynomial p1 = new Polynomial(new int[]{4, 3, 6, 7});
        Polynomial p2 = new Polynomial(new int[]{3, 2, 8});
        Polynomial result = p1.minus(p2);

        Polynomial expected = new Polynomial(new int[]{1, 1, -2, 7});
        assertEquals(expected, result);
    }

    /**
     * Tests the evaluation of a polynomial at a specific value.
     */
    @Test
    public void testEvaluation() {
        Polynomial p1 = new Polynomial(new int[]{4, 3, 6, 7});
        Polynomial p2 = new Polynomial(new int[]{3, 2, 8});
        int result = p1.times(p2).evaluate(2);

        assertEquals(3510, result);
    }

    /**
     * Tests the equality of two polynomials.
     */
    @Test
    public void testEquality() {
        Polynomial p1 = new Polynomial(new int[]{4, 3, 6, 7});
        Polynomial p2 = new Polynomial(new int[]{4, 3, 6, 7});
        Polynomial p3 = new Polynomial(new int[]{3, 2, 8});

        assertEquals(p1, p2);
        assertNotEquals(p1, p3);
    }

    /**
     * Tests the subtraction of a polynomial with itself.
     */
    @Test
    public void testAdditionWithZeroPolynomial() {
        Polynomial p1 = new Polynomial(new int[]{4, 3, 6, 7});
        Polynomial p2 = new Polynomial(new int[]{0, 0, 0, 0});
        Polynomial result = p1.plus(p2);

        assertEquals(p1, result);
    }

    /**
     * Tests the subtraction of a polynomial with itself.
     */
    @Test
    public void testSubtractionWithSamePolynomial() {
        Polynomial p1 = new Polynomial(new int[]{4, 3, 6, 7});
        Polynomial result = p1.minus(p1);

        Polynomial expected = new Polynomial(new int[]{0, 0, 0, 0});
        assertEquals(expected, result);
    }

    /**
     * Tests the differentiation of a constant polynomial.
     */
    @Test
    public void testDifferentiationOfConstant() {
        Polynomial p1 = new Polynomial(new int[]{4});
        Polynomial result = p1.differentiate(1);
        System.out.println("12323");
        System.out.println(result);

        Polynomial expected = new Polynomial(new int[]{0});
        assertEquals(expected, result);
    }

    /**
     * Tests the differentiation of a linear polynomial.
     */
    @Test
    public void testDifferentiationOfLinearPolynomial() {
        Polynomial p1 = new Polynomial(new int[]{4, 3});
        Polynomial result = p1.differentiate(1);

        Polynomial expected = new Polynomial(new int[]{3});
        assertEquals(expected, result);
    }

    /**
     * Tests the evaluation of a zero polynomial.
     */
    @Test
    public void testEvaluationOfZeroPolynomial() {
        Polynomial p1 = new Polynomial(new int[]{0, 0, 0, 0});
        int result = p1.evaluate(5);

        assertEquals(0, result);
    }

    /**
     * Tests the equality of two polynomials of different lengths.
     */
    @Test
    public void testEqualityWithDifferentLengthPolynomials() {
        Polynomial p1 = new Polynomial(new int[]{4, 3, 6, 7});
        Polynomial p2 = new Polynomial(new int[]{4, 3, 6});

        assertNotEquals(p1, p2);
    }

    /**
     * Tests the string representation of a zero polynomial.
     */
    @Test
    public void testToStringOfZeroPolynomial() {
        Polynomial p1 = new Polynomial(new int[]{0, 0, 0, 0});
        String result = p1.toString();

        assertEquals("0", result);
    }
}
