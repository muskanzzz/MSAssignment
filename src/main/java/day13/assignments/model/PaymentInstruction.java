package com.multithreading.assignments.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentInstruction {
    private String debitAccount;
    private String creditAccount;
    private BigDecimal amount;
}
