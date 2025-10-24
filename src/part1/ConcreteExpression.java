package part1;

import interfaces.Expression;

public class ConcreteExpression implements Expression {
    private String representation;

    public ConcreteExpression(String representation) {
        this.representation = representation;
    }

    @Override
    public String getRepresentation() {
        return representation;
    }

    @Override
    public void setRepresentation(String representation) {
        this.representation = representation;
    }

    @Override
    public String toString() {
        return representation;
    }
}
