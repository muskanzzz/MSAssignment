package com.multithreading.assignments.controller;


import com.multithreading.assignments.model.Account;
import com.multithreading.assignments.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    AccountService accountService;

        @GetMapping("/{accountId}")
        public ResponseEntity<Optional<Account>> getAccountDetails(@PathVariable String accountId) {
            // Logic to retrieve account details by accountId
            Optional<Account> account = accountService.getAccountById(accountId);

            if (account.isPresent()) {
                return ResponseEntity.ok(account);
            } else {
                return ResponseEntity.notFound().build();
            }
        }

    @GetMapping("/payment-method/{identifier}")
    public ResponseEntity<Account> getAccountByPaymentMethod(@PathVariable String identifier) {
        // Logic to retrieve account details by payment method identifier
        Account account = accountService.getAccountByPaymentMethod(identifier);

        if (account != null) {
            return ResponseEntity.ok(account);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    }
