package com.example.calculator.dto;

import lombok.Data;

@Data
public class ExpressionResponse {

    private String response;

    private ExpressionResponse(String response) {
        this.response = response;
    }

    public static ExpressionResponse valueOf(String response) {
        return new ExpressionResponse(response);
    }
}
