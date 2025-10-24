package part2;

import interfaces.Expression;
import interfaces.InferenceRule;
import part1.ConcreteExpression;

/// Similar to ModucPonenes
public class ModusTollens implements InferenceRule {
    @Override
    public boolean matches(Expression expr1, Expression expr2) {
        String e1 = expr1.getRepresentation().replaceAll("\\s+", "");
        String e2 = expr2.getRepresentation().replaceAll("\\s+", "");

        if (e1.contains(">")) {
            String[] parts = e1.split(">");
            if (parts.length == 2) {
                String q = parts[1].trim();
                return e2.equals("~" + q) || (q.startsWith("~") && e2.equals(q.substring(1)));
            }
        }

        if (e2.contains(">")) {
            String[] parts = e2.split(">");
            if (parts.length == 2) {
                String q = parts[1].trim();
                return e1.equals("~" + q) || (q.startsWith("~") && e1.equals(q.substring(1)));
            }
        }

        return false;
    }

    @Override
    public Expression apply(Expression expr1, Expression expr2) {
        String e1 = expr1.getRepresentation().replaceAll("\\s+", "");
        String e2 = expr2.getRepresentation().replaceAll("\\s+", "");

        /// Chooses whichever expression is the implication (P > Q), splits it into P and Q, takes the left side (P), returns negated form.
        String impl = e1.contains(">") ? e1 : e2;
        String[] parts = impl.split(">");
        String p = parts[0].trim();

        if (p.startsWith("~")) {
            return new ConcreteExpression(p.substring(1));
        } else {
            return new ConcreteExpression("~" + p);
        }
    }
}
