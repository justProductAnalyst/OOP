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

    /**
     * Test for polynomial multiplication.
     * Multiplies two polynomials and checks if the result is as expected.
     */
    @Test
    public void testMultiplication() {
        Polynomial p1 = new Polynomial(new int[]{2, 3});
        Polynomial p2 = new Polynomial(new int[]{3, 4});
        Polynomial result = p1.times(p2);

        Polynomial expected = new Polynomial(new int[]{6, 17, 12});
        assertEquals(expected, result);
    }

    /**
     * Test for multiple differentiations of a polynomial.
     * Computes the second derivative of a polynomial and checks if the result is as expected.
     */
    @Test
    public void testMultipleDifferentiations() {
        Polynomial p1 = new Polynomial(new int[]{5, 4, 3, 2});
        Polynomial result = p1.differentiate(2);

        Polynomial expected = new Polynomial(new int[]{6, 12});
        assertEquals(expected, result);
    }

    /**
     * Test for differentiation that results in a zero polynomial.
     * Computes the second derivative of a linear polynomial
     * and checks if the result is a zero polynomial.
     */
    @Test
    public void testDifferentiationToZeroPolynomial() {
        Polynomial p1 = new Polynomial(new int[]{5, 4});
        Polynomial result = p1.differentiate(2);

        Polynomial expected = new Polynomial(new int[]{0});
        assertEquals(expected, result);
        assertEquals(expected.hashCode(), result.hashCode());
    }

    /**
     * Test for evaluating a polynomial at different points.
     * Evaluates a polynomial at multiple points and checks if the results are as expected.
     */
    @Test
    public void testEvaluateAtDifferentPoints() {
        Polynomial p1 = new Polynomial(new int[]{5, 4, 3});
        assertEquals(5, p1.evaluate(0));
        assertEquals(12, p1.evaluate(1));
        assertEquals(25, p1.evaluate(2));
    }

    /**
     * Test for evaluating a zero polynomial at different points.
     * Evaluates a zero polynomial at multiple points and checks if the results are all zero.
     */
    @Test
    public void testEvaluateZeroPolynomialAtDifferentPoints() {
        Polynomial p1 = new Polynomial(new int[]{0, 0, 0, 0});
        assertEquals(0, p1.evaluate(0));
        assertEquals(0, p1.evaluate(5));
        assertEquals(0, p1.evaluate(-5));
    }

    /**
     * Test for converting various polynomials to string representation.
     * Checks if the string representations of different polynomials are as expected.
     */
    @Test
    public void testToStringVariousPolynomials() {
        Polynomial p1 = new Polynomial(new int[]{5, 0, 3});
        assertEquals("3x^2 + 5", p1.toString());

        Polynomial p2 = new Polynomial(new int[]{0, 0, 0});
        assertEquals("0", p2.toString());

        Polynomial p3 = new Polynomial(new int[]{5, 4, 3, 2});
        assertEquals("2x^3 + 3x^2 + 4x + 5", p3.toString());
    }
}
