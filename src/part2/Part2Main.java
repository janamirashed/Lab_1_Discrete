package part2;

import interfaces.Expression;
import part1.ConcreteExpression;
import java.util.Scanner;

public class Part2Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter first expression: ");
        String expr1 = scanner.nextLine();
        System.out.println("Enter second expression: ");
        String expr2 = scanner.nextLine();

        /// This creates your custom inference engine: Know about the rules, Accept logical expressions, Try to infer a result.
        ConcreteInferenceEngine concreteInferenceEngine = new ConcreteInferenceEngine();

        /// Here you’re registering all five rules into your engine, so it can test each one automatically.
        concreteInferenceEngine.addRule(new ModusPonens());
        concreteInferenceEngine.addRule(new ModusTollens());
        concreteInferenceEngine.addRule(new HypotheticalSyllogism());
        concreteInferenceEngine.addRule(new DisjunctiveSyllogism());
        concreteInferenceEngine.addRule(new Resolution());

        /// You wrap each text expression (like "P > Q") into a ConcreteExpression object.
        /// Add both expressions to the engine’s list.
        concreteInferenceEngine.addExpression(new ConcreteExpression(expr1));
        concreteInferenceEngine.addExpression(new ConcreteExpression(expr2));

        /// So now your engine has: a set of rules, two expressions it's ready to inferring !!

        /// This line triggers the logic in ConcreteInferenceEngine:
        /// It loops through every rule.
        /// Calls matches() to see which one fits.
        /// If one fits, it uses apply() to get the inferred result.
        Expression result = concreteInferenceEngine.applyRules();
        if (result != null) {
            System.out.println(result.getRepresentation() + " (" + concreteInferenceEngine.getAppliedRuleName() + ")");
        } else {
            System.out.println("The input expression cannot be inferred");
        }

        scanner.close();
    }
}
