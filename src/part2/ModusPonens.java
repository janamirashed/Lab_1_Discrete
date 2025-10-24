package part2;

import interfaces.Expression;
import part1.ConcreteExpression;

public class ModusPonens {
    /// This method checks whether the two expressions fit the Modus Ponens pattern.
    public boolean matches(Expression expr1, Expression expr2) {

        /// It first cleans up any extra spaces so "  P > Q   " becomes "P > Q", making comparisons easier.
        String e1 = expr1.getRepresentation().replaceAll("\\s+", " ");
        String e2 = expr2.getRepresentation().replaceAll("\\s+", " ");

        /// Checks if e1 is an implication (P > Q).
        if(e1.contains(">")) {
            String[] parts = e1.split(">"); /// Splits it into two parts: parts[0] = left side (P), parts[1] = right side (Q)
            if (parts.length == 2) {
                String p = parts[0].trim();
                return p.equals(e2); /// Checks if the other expression (e2) equals the left side (P).
            }
        }

        /// Checks if e2 is an implication (P > Q).
        if (e2.contains(">")) {
            String[] parts = e2.split(">");
            if (parts.length == 2) {
                String p = parts[0].trim();
                return p.equals(e1);
            }
        }

        return false;
    }

    /// Once matches() is true, apply() is called to actually infer the result.
    @Override
    public Expression apply(Expression exp1, Expression exp2) {
        /// It removes all spaces for simpler string splitting.
        String e1 = exp1.getRepresentation().replaceAll("\\s+", "");
        String e2 = exp2.getRepresentation().replaceAll("\\s+", "");

        /// Finds which of the two expressions is the implication (P > Q)
        /// Takes the right side (Q), trims it, and creates a new ConcreteExpression with it.
        /// For printing your final result.
        if (e1.contains(">")) {
            String[] parts = e1.split(">");
            return new ConcreteExpression(parts[1].trim());
        } else {
            String[] parts = e2.split(">");
            return new ConcreteExpression(parts[1].trim());
        }
    }
}
