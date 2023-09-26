package ru.nsu.vetrov;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class PolynomialTest {

    @Test
    public void testAddition() {
        Polynomial p1 = new Polynomial(new int[]{4, 3, 6, 7});
        Polynomial p2 = new Polynomial(new int[]{3, 2, 8});
        Polynomial result = p1.plus(p2.differentiate(1));

        assertEquals("7x^3 + 6x^2 + 19x + 6", result.toString());
    }

    @Test
    public void testSubtraction() {
        Polynomial p1 = new Polynomial(new int[]{4, 3, 6, 7});
        Polynomial p2 = new Polynomial(new int[]{3, 2, 8});
        Polynomial result = p1.minus(p2);

        assertEquals("7x^3 + -2x^2 + 1x + 1", result.toString());
    }

    @Test
    public void testEvaluation() {
        Polynomial p1 = new Polynomial(new int[]{4, 3, 6, 7});
        Polynomial p2 = new Polynomial(new int[]{3, 2, 8});
        int result = p1.times(p2).evaluate(2);

        assertEquals(3510, result);
    }

    @Test
    public void testEquality() {
        Polynomial p1 = new Polynomial(new int[]{4, 3, 6, 7});
        Polynomial p2 = new Polynomial(new int[]{4, 3, 6, 7});
        Polynomial p3 = new Polynomial(new int[]{3, 2, 8});

        assertEquals(p1, p2);
        assertNotEquals(p1, p3);
    }
}
