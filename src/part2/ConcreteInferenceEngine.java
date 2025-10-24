package part2;

import interfaces.Expression;
import interfaces.InferenceEngine;
import interfaces.InferenceRule;
import java.util.ArrayList;
import java.util.List;

class ConcreteInferenceEngine implements InferenceEngine {
    private List<InferenceRule> rules; /// holds all the inference rules your engine knows (e.g., Modus Ponens, Modus Tollens...)
    private List<Expression> expressions; /// holds the input expressions given by the user.
    private String appliedRuleName; /// stores the name of the rule that was actually used (so you can print which rule got applied later).

    public ConcreteInferenceEngine() { /// create a new ConcreteInferenceEngine
        this.rules = new ArrayList<>();
        this.expressions = new ArrayList<>();
    }

    /// This method adds a new rule to the engine
    @Override
    public void addRule(InferenceRule rule) {
        rules.add(rule);
    }

    /// This adds logical expressions (like "P > Q", "P", "~Q") to the engine.
    @Override
    public void addExpression(Expression exp) {
        expressions.add(exp);
    }

    /// The engine only works when there are two expressions (because inference rules need two premises)
    /// it gets the first two of the list
    @Override
    public Expression applyRules() {
        if (expressions.size() < 2) {
            return null;
        }

        Expression exp1 = expressions.get(0);
        Expression exp2 = expressions.get(1);

        /// It goes through each rule added earlier.
        /// For each rule, it checks: does this rule fit the two expressions? (matches)
        /// If yes, it applies the rule using apply(), which returns the new inferred expression.
        for (InferenceRule rule : rules) {
            if (rule.matches(exp1, exp2)) {
                Expression result = rule.apply(exp1, exp2);

                /// This section identifies which exact rule matched, to be printed on the screen later as output.
                if (rule instanceof ModusPonens) appliedRuleName = "Modus Ponens";
                else if (rule instanceof ModusTollens) appliedRuleName = "Modus Tollens";
                else if (rule instanceof HypotheticalSyllogism) appliedRuleName = "Hypothetical Syllogism";
                else if (rule instanceof DisjunctiveSyllogism) appliedRuleName = "Disjunctive Syllogism";
                else if (rule instanceof Resolution) appliedRuleName = "Resolution";
                return result;
            }
        }

        return null;
    }

    public String getAppliedRuleName() {
        return appliedRuleName;
    }
}
