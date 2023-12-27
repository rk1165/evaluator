package com.example.calculator.parser;

import com.example.calculator.exceptions.ExpressionServiceException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EvaluatePostfixTest {

    private EvaluatePostfix evaluatePostfix;
    private InfixToPostfix infixToPostfix;

    @BeforeEach
    public void setUp() {
        evaluatePostfix = new EvaluatePostfix();
        infixToPostfix = new InfixToPostfix();
    }

    @Test
    public void whenGivenSingleDigitsExpression_evaluatesIt() {

        String expression = "1 + 2";
        int expected = 3;
        int actual = evaluatePostfix.evaluate(infixToPostfix.postfix(expression));
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void whenGivenMultiDigitsExpression_evaluatesIt() {

        String expression = "143 + 122 - 90 * 2";
        int expected = 85;
        int actual = evaluatePostfix.evaluate(infixToPostfix.postfix(expression));
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void whenGivenMultiDigitsExpressionWithBraces_evaluatesIt() {

        String expression = "23 - (17 * 2 - (10 / 2))";
        int expected = -6;
        int actual = evaluatePostfix.evaluate(infixToPostfix.postfix(expression));
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void whenGivenInputWithWrongCharacter_throwException() {
        String expression = "23 + 54 -A";
        Assertions.assertThrows(ExpressionServiceException.class, () -> evaluatePostfix.evaluate(infixToPostfix.postfix(expression)));
    }

    @Test
    public void whenGivenInputWithMismatchedBraces_throwException() {
        String expression = "12 + (4 - 2) + (9";
        Assertions.assertThrows(ExpressionServiceException.class, () -> evaluatePostfix.evaluate(infixToPostfix.postfix(expression)));
    }

    @Test
    public void whenGivenPowerOperator_evaluateIt() {
        String expression = "2 ^ 3 + 5 ^ 2";
        int expected = 33;
        int actual = evaluatePostfix.evaluate(infixToPostfix.postfix(expression));
        Assertions.assertEquals(expected, actual);
    }
}
