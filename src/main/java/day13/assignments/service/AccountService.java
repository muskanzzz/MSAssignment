package com.multithreading.assignments.service;


import com.multithreading.assignments.model.Account;
import com.multithreading.assignments.model.CreditCard;
import com.multithreading.assignments.model.DebitCard;
import com.multithreading.assignments.repo.AccountRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Optional<Account> getAccountById(String accountId) {
        return accountRepository.findById(accountId);
    }

    public Account getAccountByPaymentMethod(String identifier) {
        return accountRepository.findAccountByPaymentMethod(identifier);
    }

    @PostConstruct
    public void init() {

        Account account1 = new Account("1", "USD", "BankA", "Branch1");
        account1.addPaymentMethod(new DebitCard("1111-2222-3333-4444"));

        Account account2 = new Account("2", "EUR", "BankB", "Branch2");
        account2.addPaymentMethod(new CreditCard("5555-6666-7777-8888"));

        accountRepository.save(account1);
        accountRepository.save(account2);
    }
    }

