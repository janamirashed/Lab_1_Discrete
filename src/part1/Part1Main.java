package part1;

import interfaces.LogicalExpressionSolver;

import java.util.Scanner;

public class Part1Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        boolean finished = false;
        while (!finished) {
            System.out.println("Enter the Expression:");
            ConcreteExpression expression = new ConcreteExpression(input.nextLine());

            LogicalExpressionSolverImpl solver = new LogicalExpressionSolverImpl();
            boolean validate = solver.isValid(expression.getRepresentation().replaceAll("\\s+", ""));
            if (validate) {
                boolean result = solver.evaluateExpression(expression);
                System.out.println(result);
            }
            else {
                System.out.println("Wrong expression");
            }

            System.out.println("Another Expression(y/n):");
            String choice = input.nextLine();
            if (choice.equals("n") ||  choice.equals("N")) {
                finished = true;
            }
        }
    }
}
