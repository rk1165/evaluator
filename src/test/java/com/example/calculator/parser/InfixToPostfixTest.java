package com.example.calculator.parser;

import com.example.calculator.exceptions.ExpressionServiceException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

public class InfixToPostfixTest {

    private InfixToPostfix infixToPostfix;

    @BeforeEach
    public void setUp() {
        infixToPostfix = new InfixToPostfix();
    }

    @Test
    public void whenGivenSingleDigitCorrectExpressionWithoutBraces_returnsPostfix() {

        String expression = "1 + 2";
        String expected = "12+";
        Queue<String> postfix = infixToPostfix.postfix(expression);

        StringBuilder actual = new StringBuilder();
        while (!postfix.isEmpty())
            actual.append(postfix.poll());
        Assertions.assertEquals(expected, actual.toString());
    }

    @Test
    public void whenGivenSingleDigitCorrectExpressionWithBraces_returnsPostfix() {

        String expression = "3+4*2/(6-2)";
        String expected = "342*62-/+";

        Queue<String> postfix = infixToPostfix.postfix(expression);
        StringBuilder actual = new StringBuilder();
        while (!postfix.isEmpty())
            actual.append(postfix.poll());
        Assertions.assertEquals(expected, actual.toString());
    }

    @Test
    public void whenGivenMultipleDigitCorrectExpressionWithoutBraces_returnsPostfix() {

        String expression = "143 + 122 - 90 * 2";
        List<String> expected = Arrays.asList("143", "122", "+", "90", "2", "*", "-");
        Queue<String> postfix = infixToPostfix.postfix(expression);
        List<String> actual = new ArrayList<>(postfix.size());

        while (!postfix.isEmpty())
            actual.add(postfix.poll());
        Assertions.assertIterableEquals(expected, actual);
    }

    @Test
    public void whenGivenMultipleDigitCorrectExpressionWithBraces_returnsPostfix() {

        String expression = "23 - (17 * 2 - (10 / 2))";
        List<String> expected = Arrays.asList("23", "17", "2", "*", "10", "2", "/", "-", "-");
        Queue<String> postfix = infixToPostfix.postfix(expression);
        List<String> actual = new ArrayList<>(postfix.size());

        while (!postfix.isEmpty())
            actual.add(postfix.poll());
        Assertions.assertIterableEquals(expected, actual);
    }

    @Test
    public void whenGivenInputWithWrongCharacter_throwException() {
        String expression = "23 + 54 -A";
        Assertions.assertThrows(ExpressionServiceException.class, () -> infixToPostfix.postfix(expression));
    }

    @Test
    public void whenGivenInputWithMismatchedBraces_throwException() {
        String expression = "12 + (4 - 2) + (9";
        Assertions.assertThrows(ExpressionServiceException.class, () -> infixToPostfix.postfix(expression));
    }
}
