package com.example.calculator.parser.ast;

import java.util.Stack;

public class TreeBuilder {

    ExpressionTree buildTree(String[] postfix) {
        Stack<ExpressionTree> stack = new Stack<>();
        for (int i = 0; i < postfix.length; i++) {
            if (isNumeric(postfix[i])) {
                stack.push(new ExpressionTree(postfix[i]));
            } else {
                ExpressionTree right = stack.pop();
                ExpressionTree left = stack.pop();
                ExpressionTree node = new ExpressionTree(postfix[i]);
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