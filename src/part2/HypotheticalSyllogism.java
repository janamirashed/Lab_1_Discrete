package part2;

import interfaces.Expression;
import interfaces.InferenceRule;
import part1.ConcreteExpression;

class HypotheticalSyllogism implements InferenceRule {
    @Override
    public boolean matches(Expression expr1, Expression expr2) {
        String e1 = expr1.getRepresentation().replaceAll("\\s+", "");
        String e2 = expr2.getRepresentation().replaceAll("\\s+", "");

        if(e1.contains(">") && e2.contains(">")) {
            String[] parts1 = e1.split(">");
            String[] parts2 = e2.split(">");
            if(parts1.length == 2 && parts2.length == 2) {
                return parts1[1].trim().equals(parts2[0].trim());
            }
        }

        return false;
    }

    @Override
    public Expression apply(Expression exp1, Expression exp2) {
        String e1 = exp1.getRepresentation().replaceAll("\\s+", "");
        String e2 = exp2.getRepresentation().replaceAll("\\s+", "");

        String[] parts1 = e1.split(">");
        String[] parts2 = e2.split(">");

        String p = parts1[0].trim();
        String r = parts2[1].trim();

        return new ConcreteExpression(p + " > " + r);
    }
}