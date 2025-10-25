package part2;

import interfaces.Expression;
import interfaces.InferenceRule;
import part1.ConcreteExpression;

class Resolution implements InferenceRule {
    @Override
    public boolean matches(Expression expr1, Expression expr2) {
        String e1 = expr1.getRepresentation().replaceAll("\\s+", "");
        String e2 = expr2.getRepresentation().replaceAll("\\s+", "");

        if (e1.contains("v") && e2.contains("v")) {
            String[] parts1 = e1.split("v");
            String[] parts2 = e2.split("v");

            if (parts1.length == 2 && parts2.length == 2) {
                String p1 = parts1[0].trim();
                String q1 = parts1[1].trim();
                String p2 = parts2[0].trim();
                String q2 = parts2[1].trim();

                // Check if p1 and p2 are negations of each other
                if (isNegation(p1, p2) || isNegation(q1, p2) || isNegation(p1, q2) || isNegation(q1, q2)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean isNegation(String a, String b) {
        if (a.startsWith("~")) {
            return a.substring(1).equals(b);
        } else {
            return b.equals("~" + a);
        }
    }

    @Override
    public Expression apply(Expression expr1, Expression expr2) {
        String e1 = expr1.getRepresentation().replaceAll("\\s+", "");
        String e2 = expr2.getRepresentation().replaceAll("\\s+", "");

        String[] parts1 = e1.split("v");
        String[] parts2 = e2.split("v");

        String p1 = parts1[0].trim();
        String q1 = parts1[1].trim();
        String p2 = parts2[0].trim();
        String q2 = parts2[1].trim();

        if (isNegation(p1, p2)) {
            return new ConcreteExpression(q1 + " v " + q2);
        } else if (isNegation(p1, q2)) {
            return new ConcreteExpression(q1 + " v " + p2);
        } else if (isNegation(q1, p2)) {
            return new ConcreteExpression(p1 + " v " + q2);
        } else {
            return new ConcreteExpression(p1 + " v " + p2);
        }
    }
}
