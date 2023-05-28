package com.example.calculator.controller;

import com.example.calculator.exceptions.ExpressionServiceException;
import com.example.calculator.dto.ExpressionRequest;
import com.example.calculator.dto.ExpressionResponse;
import com.example.calculator.service.ExpressionService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
@Slf4j
public class ExpressionController {

    @Autowired
    private ExpressionService calculatorService;

    @PostMapping("/expression")
    public ExpressionResponse calculate(@RequestBody ExpressionRequest request) {
        if (request.getUserId() == null || request.getUserId().length() == 0) {
            log.info("User ID is null or empty");
            throw new ExpressionServiceException("User ID is null or empty");
        }

        if (request.getExpression() == null || request.getExpression().length() == 0)
            return ExpressionResponse.valueOf("0");

        log.info("Calculating result of {} for user with id {}", request.getExpression(), request.getUserId());
        int value = calculatorService.calculate(request.getUserId(), request.getExpression());
        return ExpressionResponse.valueOf(String.valueOf(value));
    }

    @GetMapping("/frequent")
    public ExpressionResponse frequent(@RequestParam String userId) {
        if (userId == null || userId.length() == 0) {
            log.info("User ID is null or empty");
            throw new ExpressionServiceException("User ID is null or empty");
        }
        log.info("Getting most used operator for user with id {}", userId);
        char frequent = calculatorService.frequent(userId);
        return ExpressionResponse.valueOf(frequent + "");
    }

    @GetMapping("/count")
    public Map<String, Integer> frequency(@RequestParam String userId) {
        if (userId == null || userId.length() == 0) {
            log.info("User ID is null or empty");
            throw new ExpressionServiceException("User ID is null or empty");
        }
        log.info("Getting counts of operators for user={}", userId);

        return calculatorService.frequency(userId);
    }
}