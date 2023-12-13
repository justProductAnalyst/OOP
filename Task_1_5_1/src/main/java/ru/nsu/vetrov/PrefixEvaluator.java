package ru.nsu.vetrov;

import ru.nsu.vetrov.InvalidExpressionException;
import ru.nsu.vetrov.Operation;
import ru.nsu.vetrov.OperationFactory;
import ru.nsu.vetrov.UnsupportedCalculationException;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Class for parsing and evaluating expressions in prefix notation.
 */
class PrefixEvaluator {
    /**
     * Evaluates a given expression in prefix notation.
     *
     * @param expression The expression to evaluate.
     * @return The result of the evaluated expression.
     */
    public static double evaluate(String expression)
            throws InvalidExpressionException, UnsupportedCalculationException {
        Stack<Double> stack = new Stack<>();
        String[] tokens = expression.split(" ");
        for (int i = tokens.length - 1; i >= 0; i--) {
            String token = tokens[i];
            try {
                double number = Double.parseDouble(token);
                stack.push(number);
            } catch (NumberFormatException e) {
                try {
                    Operation op = OperationFactory.getOperation(token);
                    ArrayList<Double> operands = new ArrayList<>();
                    while (!stack.isEmpty() && operands.size() < 2) {
                        operands.add(stack.pop());
                    }
                    if (operands.size() < 2) {
                        throw new InvalidExpressionException(expression);
                    }
                    double result = op.apply(operands.toArray(new Double[0]));
                    stack.push(result);
                } catch (UnsupportedCalculationException ex) {
                    throw new UnsupportedCalculationException(token);
                }
            }
        }
        return stack.pop();
    }
}