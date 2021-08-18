package com.example.calculator.service.impl;

import com.example.calculator.exceptions.ExpressionServiceException;
import com.example.calculator.model.User;
import com.example.calculator.parser.EvaluatePostfix;
import com.example.calculator.parser.InfixToPostfix;
import com.example.calculator.repository.ExpressionRepository;
import com.example.calculator.service.ExpressionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.Optional;
import java.util.Queue;

@Service
public class ExpressionServiceImpl implements ExpressionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExpressionServiceImpl.class);

    @Autowired
    private ExpressionRepository expressionRepository;

    @Autowired
    private InfixToPostfix infixToPostfix;

    @Autowired
    private EvaluatePostfix evaluatePostfix;

    @Override
    public int calculate(String userId, String expression) {

        Queue<String> postfix;
        try {
            postfix = infixToPostfix.postfix(expression);
            countAndPersistUser(postfix, userId);
            return evaluatePostfix.evaluate(postfix);
        } catch (IllegalArgumentException ex) {
            LOGGER.error("Received incorrect expression {}", expression);
            throw new ExpressionServiceException("Given expression is invalid");
        }
    }

    private void countAndPersistUser(Queue<String> postfix, String userId) {
        Iterator<String> iterator = postfix.iterator();
        int plus = 0, minus = 0, multiply = 0, divide = 0;
        while (iterator.hasNext()) {
            String elem = iterator.next();
            if (elem.equals("+"))
                plus++;
            else if (elem.equals("-"))
                minus++;
            else if (elem.equals("*"))
                multiply++;
            else if (elem.equals("/"))
                divide++;
        }

        Optional<User> optionalUser = expressionRepository.findById(userId);
        User user = null;
        if (optionalUser.isPresent()) {
            user = optionalUser.get();
            user.setPlusCount(user.getPlusCount() + plus);
            user.setMinusCount(user.getMinusCount() + minus);
            user.setMultiplyCount(user.getMultiplyCount() + multiply);
            user.setDivisionCount(user.getDivisionCount() + divide);
        } else {
            user = new User(userId, plus, minus, multiply, divide);
        }
        expressionRepository.save(user);
    }

    @Override
    public char frequent(String userId) {
        Optional<User> optionalUser = expressionRepository.findById(userId);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            char mostUsed = ' ';
            int maxCount = Integer.MIN_VALUE;
            if (user.getPlusCount() > maxCount) {
                mostUsed = '+';
                maxCount = user.getPlusCount();
            }
            if (user.getMinusCount() > maxCount) {
                mostUsed = '-';
                maxCount = user.getMinusCount();
            }
            if (user.getMultiplyCount() > maxCount) {
                mostUsed = '*';
                maxCount = user.getMinusCount();
            }
            if (user.getDivisionCount() > maxCount) {
                mostUsed = '/';
            }
            return mostUsed;
        } else {
            LOGGER.error("User with id {} doesn't exist", userId);
            throw new ExpressionServiceException("User with id " + userId + " doesn't exist");
        }
    }
}
