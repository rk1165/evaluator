package com.example.calculator.parser.ast;

import java.util.Stack;

public class TreeBuilder {

    ExpressionTree buildTree(String[] postfix) {
        Stack<ExpressionTree> stack = new Stack<>();
        for (String s : postfix) {
            if (isNumeric(s)) {
                stack.push(new ExpressionTree(s));
            } else {
                ExpressionTree right = stack.pop();
                ExpressionTree left = stack.pop();
                ExpressionTree node = new ExpressionTree(s);
                node.left = left;
                node.right = right;
                stack.push(node);
            }
        }
        return stack.pop();
    }

    private boolean isNumeric(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (!Character.isDigit(s.charAt(i)))
                return false;
        }
        return true;
    }
}