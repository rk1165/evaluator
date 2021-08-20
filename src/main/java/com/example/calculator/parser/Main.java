package com.example.calculator.parser;

import java.util.Queue;

public class Main {


    public static void main(String[] args) {

        InfixToPostfix infixToPostfix = new InfixToPostfix();
        EvaluatePostfix evaluatePostfix = new EvaluatePostfix();

        Queue<String> postfix = infixToPostfix.postfix("3+4*2/(6-2)"); // [3, 4, 2, *, 6, 2, -, /, +]
        evaluatePostfix.evaluate(postfix);

        postfix = infixToPostfix.postfix("5*(3+(1-5))/5"); // [5, 3, 1, 5, -, +, *, 5, /]
        evaluatePostfix.evaluate(postfix);

        postfix = infixToPostfix.postfix("143 + 122 - 90 * 2");
        evaluatePostfix.evaluate(postfix);

        postfix = infixToPostfix.postfix("23 - (17 * 2 - (10 / 2))");
        evaluatePostfix.evaluate(postfix);

        postfix = infixToPostfix.postfix("1 + (3 -2)");
    }
}
