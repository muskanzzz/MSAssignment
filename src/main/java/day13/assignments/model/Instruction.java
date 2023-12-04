package com.multithreading.assignments.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Instruction {
    private String accountNumber;
    private String currency;
    private String instructionType;
    private BigDecimal amount;

    // Constructors, getters, setters, etc.
}
