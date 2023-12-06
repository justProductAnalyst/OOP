package ru.nsu.vetrov;

/**
 * Factory class for creating instances of mathematical operations.
 * It provides a method to get an operation instance based on the operation symbol.
 */
class OperationFactory {

    /**
     * Returns an operation instance based on the given operator symbol.
     *
     * @param operator The operator symbol (e.g., "+", "-", "sin").
     * @return The corresponding operation.
     * @throws UnsupportedOperationException if the operator is unsupported.
     */
    public static Operation getOperation(String operator) {
        switch (operator) {
            case "+":
                return (operands) -> operands[0] + operands[1];
            case "-":
                return (operands) -> operands[0] - operands[1];
            case "*":
                return (operands) -> operands[0] * operands[1];
            case "/":
                return (operands) -> operands[0] / operands[1];
            case "sin":
                return (operands) -> Math.sin(Math.toRadians(operands[0]));
            case "cos":
                return (operands) -> Math.cos(Math.toRadians(operands[0]));
            case "log":
                return (operands) -> Math.log(operands[0]);
            case "sqrt":
                return (operands) -> Math.sqrt(operands[0]);
            case "pow":
                return (operands) -> Math.pow(operands[0], operands[1]);
            default:
                throw new UnsupportedOperationException("Unsupported operation: " + operator);
        }
    }
}