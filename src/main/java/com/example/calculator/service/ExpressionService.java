package com.example.calculator.service;

public interface ExpressionService {

    int calculate(String userId, String expression);

    char frequent(String userId);
}
