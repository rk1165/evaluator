package com.example.calculator.parser;

import com.example.calculator.exceptions.ExpressionServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class InfixToPostfix {

    private static final Logger LOGGER = LoggerFactory.getLogger(InfixToPostfix.class);

    private static final Map<Character, Integer> map = new HashMap<>();

    static {
        map.put('/', 4);
        map.put('*', 4);
        map.put('+', 3);
        map.put('-', 3);
    }

    /**
     * Convert from infix to postfix operation. Also handles multiple digits
     *
     * @param infix an infix mathematical expression
     * @return a queue having postfix expression
     */
    public Queue<String> postfix(String infix) {

        if (!isValid(infix)) {
            LOGGER.error("{} is not a valid infix", infix);
            throw new ExpressionServiceException("Given infix expression is invalid");
        }

        Queue<String> output = new ArrayDeque<>();
        Stack<Character> operatorStack = new Stack<>();

        for (int i = 0; i < infix.length(); i++) {
            char ch = infix.charAt(i);
            if (Character.isDigit(ch)) {
                StringBuilder sb = new StringBuilder();
                while (i < infix.length() && Character.isDigit(infix.charAt(i)))
                    sb.append(infix.charAt(i++));
                output.add(sb.toString());
                if (i < infix.length())
                    i -= 1;
            } else if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                while (!operatorStack.isEmpty() && operatorStack.peek() != '(' && precedence(operatorStack.peek(), ch)) {
                    output.add("" + operatorStack.pop());
                }
                operatorStack.push(ch);
            } else if (ch == '(') {
                operatorStack.push(ch);
            } else if (ch == ')') {
                while (!operatorStack.isEmpty() && operatorStack.peek() != '(') {
                    output.add("" + operatorStack.pop());
                }
                operatorStack.pop();
            }
        }

        while (!operatorStack.isEmpty())
            output.add("" + operatorStack.pop());

        LOGGER.info("Infix {} is converted to postfix : {}", infix, output);
        return output;
    }

    /**
     * Checks the precedence of operator1 and operator2
     *
     * @param op1 operator +, -, *, /
     * @param op2 operator +, -, *, /
     * @return true if op1 has higher precedence than op2
     */
    private boolean precedence(char op1, char op2) {
        return map.get(op1) >= map.get(op2);
    }

    /**
     * Check if the given expression is valid
     *
     * @param expression a mathematical expression
     * @return true if the expression is valid
     */
    private boolean isValid(String expression) {
        int left = 0, right = 0;
        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);
            if (!(Character.isDigit(ch) || Character.isSpaceChar(ch) || ch == '(' || ch == ')' ||
                    ch == '+' || ch == '-' || ch == '*' || ch == '/'))
                return false;
            if (ch == '(')
                left++;
            else if (ch == ')')
                right++;
        }
        return left == right;
    }
}
