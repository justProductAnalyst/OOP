package ru.nsu.vetrov;

/**
 * Interface for mathematical operations.
 * Each operation (like addition, sine, logarithm) must implement this interface.
 */
interface Operation {
    double apply(Double... operands) throws DivisionByZeroException;
}