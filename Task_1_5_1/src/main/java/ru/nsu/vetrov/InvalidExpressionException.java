package ru.nsu.vetrov;

/**
 * Exception thrown for invalid expressions.
 */
public class InvalidExpressionException extends CalculatorException {
    public InvalidExpressionException(String expression) {
        super("Invalid expression: " + expression);
    }
}
