package ru.nsu.vetrov;

import java.util.Scanner;


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
