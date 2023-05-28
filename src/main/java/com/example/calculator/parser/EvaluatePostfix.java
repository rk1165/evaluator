package com.example.calculator.parser;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Queue;
import java.util.Stack;

@Component
@Slf4j
public class EvaluatePostfix {

    /**
     * Given a valid postfix expression, evaluate it.
     *
     * @param postfix a postfix mathematical expression
     * @return result after evaluating the given postfix expression
     */
    public int evaluate(Queue<String> postfix) {

        log.info("Evaluating {}", postfix);
        Stack<Integer> stack = new Stack<>();

        while (!postfix.isEmpty()) {
            String s = postfix.poll();
            if (isNumeric(s)) {
                stack.push(Integer.parseInt(s));
            } else {
                int num1 = stack.pop();
                int num2 = stack.pop();
                if (s.equals("/"))
                    stack.push(num2 / num1);
                else if (s.equals("*"))
                    stack.push(num1 * num2);
                else if (s.equals("+"))
                    stack.push(num1 + num2);
                else if (s.equals("-"))
                    stack.push(num2 - num1);
            }
        }

        log.info("Expression evaluates to {}", stack.peek());
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
