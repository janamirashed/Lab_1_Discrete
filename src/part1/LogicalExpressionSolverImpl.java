package part1;

import interfaces.Expression;
import interfaces.LogicalExpressionSolver;
import java.util.*;

class LogicalExpressionSolverImpl implements LogicalExpressionSolver {

    private static final Map<Character, Integer> PRECEDENCE = Map.of(
            '~', 4,
            '^', 3,
            'v', 2,
            '>', 1
    );

    @Override
    public boolean evaluateExpression(Expression expression) {
        String expr = expression.getRepresentation().replaceAll("\\s+", "");
        List<String> postfix = infixToPostfix(expr);
        return evaluatePostfix(postfix);
    }

    public boolean isValid(String expr) {
        int balance = 0;
        for (char c : expr.toCharArray()) {
            if (c == '(') balance++;
            else if (c == ')') balance--;
            if (balance < 0) return false;
        }
        if (balance != 0) return false;

        for (char c : expr.toCharArray()) {
            boolean isVariable = (c >= 'A' && c <= 'Z');
            boolean isOperator = (c == 'v' || c == '^' || c == '~' || c == '>');
            boolean isParenthesis = (c == '(' || c == ')');
            if (!(isVariable || isOperator || isParenthesis)) return false;
        }

        char prev = 0;
        for (int i = 0; i < expr.length(); i++) {
            char c = expr.charAt(i);

            if (Character.isUpperCase(c)) {
                if (prev != 0 && (Character.isUpperCase(prev) || prev == ')')) return false;
            } else if (c == '(') {
                if (prev != 0 && (Character.isUpperCase(prev) || prev == ')')) return false;
            } else if (c == ')') {
                if (prev == 0 || "(^v>~".indexOf(prev) >= 0) return false;
            } else if ("^v>".indexOf(c) >= 0) {
                if (prev == 0 || "(^v>~".indexOf(prev) >= 0) return false;
            } else if (c == '~') {
                if (prev != 0 && (Character.isUpperCase(prev) || prev == ')')) return false;
            }
            prev = c;
        }

        if ("(^v>~".indexOf(prev) >= 0) return false;

        return true;
    }

    private List<String> infixToPostfix(String expr) {
        Stack<Character> stack = new Stack<>();
        List<String> output = new ArrayList<>();

        for (int i = 0; i < expr.length(); i++) {
            char c = expr.charAt(i);

            if (Character.isUpperCase(c)) {
                output.add(String.valueOf(c));
            }
            else if (c == '(') {
                stack.push(c);
            }
            else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    output.add(String.valueOf(stack.pop()));
                }
                if (!stack.isEmpty()) stack.pop();
            }
            else if (PRECEDENCE.containsKey(c)) {
                while (!stack.isEmpty() && stack.peek() != '(' &&
                        PRECEDENCE.get(stack.peek()) >= PRECEDENCE.get(c)) {
                    output.add(String.valueOf(stack.pop()));
                }
                stack.push(c);
            }
            else {
                throw new IllegalArgumentException("Invalid token: " + c);
            }
        }

        while (!stack.isEmpty()) {
            output.add(String.valueOf(stack.pop()));
        }

        return output;
    }

    private boolean evaluatePostfix(List<String> postfix) {
        Scanner sc = new Scanner(System.in);
        Map<String, Boolean> values = new HashMap<>();

        Stack<Boolean> stack = new Stack<>();

        for (String token : postfix) {
            if (token.matches("[A-Z]")) {
                if (!values.containsKey(token)) {
                    System.out.print(token + " = ");
                    values.put(token, sc.nextBoolean());
                }
                stack.push(values.get(token));
            }
            else if (token.equals("~")) {
                stack.push(!stack.pop());
            }
            else {
                boolean b2 = stack.pop();
                boolean b1 = stack.pop();
                switch (token) {
                    case "^":
                        stack.push(b1 && b2);
                        break;
                    case "v":
                        stack.push(b1 || b2);
                        break;
                    case ">":
                        stack.push(!b1 || b2);
                        break;
                }
            }
        }

        return stack.pop();
    }
}
