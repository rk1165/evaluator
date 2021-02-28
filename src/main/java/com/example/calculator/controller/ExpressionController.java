package com.example.calculator.controller;

import com.example.calculator.exceptions.ExpressionServiceException;
import com.example.calculator.model.ExpressionRequest;
import com.example.calculator.model.ExpressionResponse;
import com.example.calculator.service.ExpressionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ExpressionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExpressionController.class);

    @Autowired
    private ExpressionService calculatorService;

    @PostMapping("/expression")
    public ExpressionResponse calculate(@RequestBody ExpressionRequest request) {
        if (request.getUserId() == null || request.getUserId().length() == 0) {
            LOGGER.debug("User ID is null or empty");
            throw new ExpressionServiceException("User ID is null or empty");
        }
        if (request.getExpression() == null || request.getExpression().length() == 0)
            return ExpressionResponse.valueOf("0");

        LOGGER.debug("Calculating result of {} for user with id {}", request.getExpression(), request.getUserId());
        int value = calculatorService.calculate(request.getUserId(), request.getExpression());
        return ExpressionResponse.valueOf(String.valueOf(value));
    }

    @GetMapping("/frequent")
    public ExpressionResponse frequent(@RequestParam String userId) {
        if (userId == null || userId.length() == 0) {
            LOGGER.debug("User ID is null or empty");
            throw new ExpressionServiceException("User ID is null or empty");
        }
        LOGGER.debug("Getting most used operator for user with id {}", userId);
        char frequent = calculatorService.frequent(userId);
        return ExpressionResponse.valueOf(frequent + "");
    }
}