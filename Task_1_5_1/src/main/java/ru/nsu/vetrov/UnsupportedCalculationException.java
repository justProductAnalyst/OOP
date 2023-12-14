package ru.nsu.vetrov;

/**
 * Exception thrown for unsupported operations.
 */
public class UnsupportedCalculationException extends CalculatorException {
    public UnsupportedCalculationException(String operation) {
        super("Unsupported operation: " + operation);
    }
}
