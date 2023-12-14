package ru.nsu.vetrov;

/**
 * Exception thrown for division by zero.
 */
public class DivisionByZeroException extends CalculatorException {
    public DivisionByZeroException() {
        super("Division by zero is not allowed.");
    }
}
