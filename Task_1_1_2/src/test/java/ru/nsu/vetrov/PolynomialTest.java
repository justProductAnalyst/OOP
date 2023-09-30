package ru.nsu.vetrov;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class PolynomialTest {

    @Test
    public void testAddition() {
        Polynomial p1 = new Polynomial(new int[]{4, 3, 6, 7});
        Polynomial p2 = new Polynomial(new int[]{3, 2, 8});
        Polynomial result = p1.plus(p2.differentiate(1));

        Polynomial expected = new Polynomial(new int[]{6, 19, 6, 7});
        assertEquals(expected, result);
    }

    @Test
    public void testSubtraction() {
        Polynomial p1 = new Polynomial(new int[]{4, 3, 6, 7});
        Polynomial p2 = new Polynomial(new int[]{3, 2, 8});
        Polynomial result = p1.minus(p2);

        Polynomial expected = new Polynomial(new int[]{1, 1, -2, 7});
        assertEquals(expected, result);
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
