package com.example.calculator.parser.ast;

public class ExpressionTree {

    String val;
    ExpressionTree left;
    ExpressionTree right;

    public ExpressionTree(String val) {
        this.val = val;
    }

    public int evaluate() {
        if (this.left == null && this.right == null)
            return Integer.parseInt(this.val);

        int left = this.left.evaluate();
        int right = this.right.evaluate();

        return switch (this.val) {
            case "+" -> left + right;
            case "-" -> left - right;
            case "*" -> left * right;
            case "/" -> left / right;
            case "^" -> (int) Math.pow(left, right);
            default -> throw new RuntimeException("Unknown Operator");
        };

    }
}
