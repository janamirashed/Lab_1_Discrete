# CS214 Lab 1 - Inference Rules

Java implementation of a logical expression solver and inference engine for propositional logic.

## 👥 Team Members
- Jana Mohamed Rashed
- Mohamed Ahmed Salama

## 📖 What This Does

### Part 1: Expression Solver
Evaluates logical expressions like `(P ^ Q) v ~R`
- **Operators:** `~` (NOT), `^` (AND), `v` (OR), `>` (IMPLIES)
- **Example:** 
  - Input: `(P ^ Q) v ~R` where P=true, Q=false, R=true
  - Output: `false`

### Part 2: Inference Engine
Applies logical rules to derive conclusions from premises.
- **Example:**
  - Input: `~X v Y` and `X v Z`
  - Output: `Y v Z (Resolution)`

## 📁 Project Structure
```
src/
├── interfaces/          # Core interfaces
│   ├── Expression.java
│   ├── LogicalExpressionSolver.java
│   ├── InferenceRule.java
│   └── InferenceEngine.java
├── part1/               # Expression solver
│   ├── ConcreteExpression.java
│   ├── LogicalSolver.java
│   └── Part1Main.java
└── part2/               # Inference engine
    ├── ConcreteInferenceEngine.java
    ├── ModusPonens.java
    ├── ModusTollens.java
    ├── HypotheticalSyllogism.java
    ├── DisjunctiveSyllogism.java
    ├── Resolution.java
    └── Part2Main.java
```

## 🎯 Supported Inference Rules

1. **Modus Ponens:** P > Q, P ⊢ Q
2. **Modus Tollens:** P > Q, ~Q ⊢ ~P
3. **Hypothetical Syllogism:** P > Q, Q > R ⊢ P > R
4. **Disjunctive Syllogism:** P v Q, ~P ⊢ Q
5. **Resolution:** P v Q, ~P v R ⊢ Q v R

## 🔧 Features

✅ Full expression parsing with parentheses support  
✅ Correct operator precedence  
✅ Error handling for invalid expressions  
✅ All 5 inference rules implemented  
✅ Clean interface-based design  

## 📝 Notes

- Variables are case-sensitive (P ≠ p)
- Part 2 works with simple expressions (no parentheses, max one binary operator)
- Whitespace is automatically handled

## 📄 License

Academic project for CS214 Discrete Structures - Alexandria University
