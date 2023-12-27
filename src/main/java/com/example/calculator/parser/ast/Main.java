package com.example.calculator.parser.ast;

public class Main {

    public static void main(String[] args) {
        TreeBuilder builder = new TreeBuilder();
        // (3 + 4) * 2 / 7
        ExpressionTree expressionTree = builder.buildTree(new String[]{"3", "4", "+", "2", "*", "7", "/"});
        System.out.println(expressionTree.evaluate());

        // (100 + 200) / 2 * 5 + 7
        expressionTree = builder.buildTree(new String[]{"100", "200", "+", "2", "/", "5", "*", "7", "+"});
        System.out.println(expressionTree.evaluate());
    }
}
