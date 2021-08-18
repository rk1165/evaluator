package com.example.calculator.parser.ast;

public class ExpressionTree {

    String val;
    ExpressionTree left;
    ExpressionTree right;

    public ExpressionTree(String val) {
        this.val = val;
    }

    public int evaluate() {
        if (this == null)
            return 0;
        if (this.left == null && this.right == null)
            return Integer.parseInt(this.val);

        int left = this.left.evaluate();
        int right = this.right.evaluate();

        if (this.val.equals("+"))
            return left + right;
        else if (this.val.equals("-"))
            return left - right;
        else if (this.val.equals("*"))
            return left * right;
        else
            return left / right;

    }
}
