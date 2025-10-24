package part2;

import interfaces.Expression;
import interfaces.InferenceRule;
import part1.ConcreteExpression;

class DisjunctiveSyllogism implements InferenceRule {
    @Override
    public boolean matches(Expression exp1, Expression exp2) {
        String e1 = exp1.getRepresentation().replaceAll("\\s+", "");
        String e2 = exp2.getRepresentation().replaceAll("\\s+", "");

        /// Case 1 — When the first expression is a disjunction (v).
        if (e1.contains("v")) {
            String[] parts = e1.split("v");
            if (parts.length == 2) {
                String p = parts[0].trim();
                String q = parts[1].trim();
                if (e2.equals("~" + p) || (p.startsWith("~") && e2.equals(p.substring(1)))) {
                    return true;
                }
                if (e2.equals("~" + q) || (q.startsWith("~") && e2.equals(q.substring(1)))) {
                    return true;
                }
            }
        }

        /// Case 2 — When the second expression is a disjunction (v).
        if (e2.contains("v")) {
            String[] parts = e2.split("v");
            if (parts.length == 2) {
                String p = parts[0].trim();
                String q = parts[1].trim();
                if (e1.equals("~" + p) || (p.startsWith("~") && e1.equals(p.substring(1)))) {
                    return true;
                }
                if (e1.equals("~" + q) || (q.startsWith("~") && e1.equals(q.substring(1)))) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public Expression apply(Expression exp1, Expression exp2) {
        String e1 = exp1.getRepresentation().replaceAll("\\s+", "");
        String e2 = exp2.getRepresentation().replaceAll("\\s+", "");

        String disj, neg;

        if (e1.contains("v")) {
            disj = e1;  /// e1 is the disjunction ("P v Q")
            neg = e2;   /// e2 is the negated expression ("~P")
        } else {
            disj = e2;  /// e2 is the disjunction ("P v Q")
            neg = e1;   /// e1 is the negated expression ("~P")
        }

        /// Split the disjunction
        String[] parts = disj.split("v");
        String p = parts[0].trim();
        String q = parts[1].trim();

        /// Clearer with ex on paper
        if (neg.equals("~" + p) || (p.startsWith("~") && neg.equals(p.substring(1)))) {
            return new ConcreteExpression(q);
        } else {
            return new ConcreteExpression(p);
        }
    }
}