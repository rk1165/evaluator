package com.example.calculator.service;

import java.util.Map;

public interface ExpressionService {

    int calculate(String userId, String expression);

    char frequent(String userId);

    Map<String, Integer> frequency(String userId);
}
