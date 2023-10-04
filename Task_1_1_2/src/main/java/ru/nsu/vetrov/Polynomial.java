package ru.nsu.vetrov;


/**
 * Represents a polynomial.
 */
public class Polynomial {
    /**
     * Coefficients of the polynomial.
     */
    private final int[] coefficients;

    /**
     * Constructs a polynomial with given coefficients.
     *
     * @param coefficients the coefficients of the polynomial
     */
    public Polynomial(int[] coefficients) {
        this.coefficients = coefficients.clone();
    }

    /**
     * Adds this polynomial with another polynomial.
     *
     * @param other the other polynomial
     * @return the sum as a new polynomial
     */
    public Polynomial plus(Polynomial other) {
        int maxDegree = Math.max(this.coefficients.length, other.coefficients.length);
        int[] result = new int[maxDegree];

        for (int i = 0; i < maxDegree; i++) {
            if (i < this.coefficients.length) {
                result[i] += this.coefficients[i];
            }
            if (i < other.coefficients.length) {
                result[i] += other.coefficients[i];
            }
        }

        return new Polynomial(result);
    }

    /**
     * Subtracts another polynomial from this polynomial.
     *
     * @param other the other polynomial
     * @return the difference as a new polynomial
     */
    public Polynomial minus(Polynomial other) {
        int maxDegree = Math.max(this.coefficients.length, other.coefficients.length);
        int[] result = new int[maxDegree];

        for (int i = 0; i < maxDegree; i++) {
            if (i < this.coefficients.length) {
                result[i] += this.coefficients[i];
            }
            if (i < other.coefficients.length) {
                result[i] -= other.coefficients[i];
            }
        }

        return new Polynomial(result);
    }

    /**
     * Multiplies this polynomial with another polynomial.
     *
     * @param other the other polynomial
     * @return the product as a new polynomial
     */
    public Polynomial times(Polynomial other) {
        int[] result = new int[this.coefficients.length + other.coefficients.length - 1];

        for (int i = 0; i < this.coefficients.length; i++) {
            for (int j = 0; j < other.coefficients.length; j++) {
                result[i + j] += this.coefficients[i] * other.coefficients[j];
            }
        }

        return new Polynomial(result);
    }

    /**
     * Evaluates the polynomial at a given point.
     *
     * @param x the point at which the polynomial is to be evaluated
     * @return the value of the polynomial at the given point
     */
    public int evaluate(int x) {
        int result = 0;
        int power = 1;

        for (int coefficient : this.coefficients) {
            result += coefficient * power;
            power *= x;
        }

        return result;
    }

    /**
     * Differentiates the polynomial a given number of times.
     *
     * @param order the number of times the polynomial is to be differentiated
     * @return the derivative as a new polynomial
     */
    public Polynomial differentiate(int order) {
        int[] result = this.coefficients;

        for (int k = 0; k < order; k++) {
            if (result.length == 0) {
                return new Polynomial(new int[]{0});
            }
            int[] newResult = new int[Math.max(result.length - 1, 0)];
            for (int i = 1; i < result.length; i++) {
                newResult[i - 1] = result[i] * i;
            }
            result = newResult;
        }
        if (result.length == 0) {
            return new Polynomial(new int[]{0});
        }
        return new Polynomial(result);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Polynomial)) {
            return false;
        }
        Polynomial other = (Polynomial) obj;
        if (this.coefficients.length != other.coefficients.length) {
            return false;
        }
        for (int i = 0; i < this.coefficients.length; i++) {
            if (this.coefficients[i] != other.coefficients[i]) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = 1;
        for (int coefficient : coefficients) {
            result = 31 * result + coefficient;
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        boolean isFirst = true;
        for (int i = this.coefficients.length - 1; i >= 0; i--) {
            if (this.coefficients[i] == 0 && isFirst && i == 0) {
                result.append("0");
                break;
            }
            if (this.coefficients[i] == 0) {
                continue;
            }

            if (!isFirst) {
                result.append(" + ");
            }
            isFirst = false;

            if (i > 1) {
                result.append(this.coefficients[i]).append("x^").append(i);
            } else if (i == 1) {
                result.append(this.coefficients[i]).append("x");
            } else {
                result.append(this.coefficients[i]);
            }
        }

        return result.toString();
    }

}
