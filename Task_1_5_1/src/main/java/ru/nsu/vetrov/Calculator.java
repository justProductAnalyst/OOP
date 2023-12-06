package ru.nsu.vetrov;

import java.util.*;

/**
 * Interface for mathematical operations.
 * Each operation (like addition, sine, logarithm) must implement this interface.
 */
interface Operation {
    double apply(Double... operands);
}

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

/**
 * Class for parsing and evaluating expressions in prefix notation.
 */
class PrefixEvaluator {
    /**
     * Evaluates a given expression in prefix notation.
     *
     * @param expression The expression to evaluate.
     * @return The result of the evaluated expression.
     * @throws UnsupportedOperationException if an unsupported operation is encountered.
     * @throws NumberFormatException if a token cannot be parsed as a number.
     */
    public static double evaluate(String expression) {
        Stack<Double> stack = new Stack<>();
        String[] tokens = expression.split(" ");
        for (int i = tokens.length - 1; i >= 0; i--) {
            String token = tokens[i];
            try {
                double number = Double.parseDouble(token);
                stack.push(number);
            } catch (NumberFormatException e) {
                Operation op = OperationFactory.getOperation(token);
                ArrayList<Double> operands = new ArrayList<>();
                while (!stack.isEmpty() && operands.size() < 2) {
                    operands.add(stack.pop());
                }
                double result = op.apply(operands.toArray(new Double[0]));
                stack.push(result);
            }
        }
        return stack.pop();
    }
}

/**
 * Main class for the calculator.
 * It reads expressions from the standard input, evaluates them, and prints the results.
 */
public class Calculator {
    /**
     * The main method to start the calculator program.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter expression in prefix form:");
        String input = scanner.nextLine();
        try {
            double result = PrefixEvaluator.evaluate(input);
            System.out.println("Result: " + result);
        } catch (Exception e) {
            System.out.println("Error occurred: " + e.getMessage());
        }
    }
}
