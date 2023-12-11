package com.multithreading.springmultithreading.controllertest;

import com.multithreading.assignments.controller.AccountController;
import com.multithreading.assignments.model.Account;
import com.multithreading.assignments.model.DebitCard;
import com.multithreading.assignments.service.AccountService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith({})
class AccountControllerTest {

    @Mock
    private AccountService accountService;

    @InjectMocks
    private AccountController accountController;

    @Test
    void getAccountDetails_AccountExists_ReturnsAccount() {
        // Arrange
        String accountId = "123";
        Account mockAccount = new Account("1", "USD", "BankA", "Branch1");
        mockAccount.addPaymentMethod(new DebitCard("1111-2222-3333-4444"));
        when(accountService.getAccountById(accountId)).thenReturn(Optional.of(mockAccount));

        // Act
        ResponseEntity<Optional<Account>> response = accountController.getAccountDetails(accountId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockAccount, response.getBody().orElse(null));
    }

    @Test
    void getAccountDetails_AccountNotExists_ReturnsNotFound() {
        // Arrange
        String accountId = "456";
        when(accountService.getAccountById(accountId)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Optional<Account>> response = accountController.getAccountDetails(accountId);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(Optional.empty(), response.getBody());
    }

    @Test
    void getAccountByPaymentMethod_AccountExists_ReturnsAccount() {
        // Arrange
        String paymentMethodIdentifier = "credit-card";
        Account mockAccount = new Account("1", "USD", "BankA", "Branch1");
        mockAccount.addPaymentMethod(new DebitCard("1111-2222-3333-4444"));
        when(accountService.getAccountByPaymentMethod(paymentMethodIdentifier)).thenReturn(mockAccount);

        // Act
        ResponseEntity<Account> response = accountController.getAccountByPaymentMethod(paymentMethodIdentifier);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockAccount, response.getBody());
    }

    @Test
    void getAccountByPaymentMethod_AccountNotExists_ReturnsNotFound() {
        // Arrange
        String paymentMethodIdentifier = "paypal";
        when(accountService.getAccountByPaymentMethod(paymentMethodIdentifier)).thenReturn(null);

        // Act
        ResponseEntity<Account> response = accountController.getAccountByPaymentMethod(paymentMethodIdentifier);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }
}