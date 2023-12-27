package com.example.calculator.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;


@Data
@Builder
@Table(name = "user")
public class User {

    @Id
    private Long id;

    private String userId;
    private int plusCount;
    private int minusCount;
    private int multiplyCount;
    private int divisionCount;
    private int powerCount;
}
