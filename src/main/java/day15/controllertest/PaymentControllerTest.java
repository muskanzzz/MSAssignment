package com.multithreading.springmultithreading.controllertest;

import com.multithreading.assignments.controller.PaymentController;
import com.multithreading.assignments.model.Account;
import com.multithreading.assignments.model.PaymentInstruction;
import com.multithreading.assignments.service.AccountService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpMethod.GET;

@ExtendWith({})
class PaymentControllerTest {

    @Mock
    private AccountService accountService;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private PaymentController paymentController;

    @Test
    void createPayment_Success() {
        // Mock the account details
        Account debitAccountDetails = new Account("123", "USD");
        Account creditAccountDetails = new Account("456", "USD");

        ResponseEntity<Account> debitAccountResponse = new ResponseEntity<>(debitAccountDetails, HttpStatus.OK);
        ResponseEntity<Account> creditAccountResponse = new ResponseEntity<>(creditAccountDetails, HttpStatus.OK);

        when(restTemplate.exchange(anyString(), eq(GET), any(), anyString())
                .thenReturn(debitAccountResponse)
                .thenReturn(creditAccountResponse));
        // Mock the account service behavior
        when(accountService.getAccountByPaymentMethod(anyString())).thenReturn(debitAccountDetails);

        // Create a PaymentInstruction
        PaymentInstruction paymentInstruction = new PaymentInstruction("123", "456", 100.0);

        // Call the createPayment method
        ResponseEntity<String> responseEntity = paymentController.createPayment(paymentInstruction);

        // Assert the result
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Payment instructions created successfully\n" +
                        "Debit Instruction: DebitInstruction{accountNumber='123', currency='USD', instructionType='Debit', amount=100.0}\n" +
                        "Credit Instruction: CreditInstruction{accountNumber='456', currency='USD', instructionType='Credit', amount=100.0}",
                responseEntity.getBody());
    }

    private Class<Object> eq(HttpMethod accountClass) {
        return null;
    }

    @Test
    void createPayment_AccountNotFound() {
        // Mock the response from restTemplate
        ResponseEntity<Account> debitAccountResponse = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        ResponseEntity<Account> creditAccountResponse = new ResponseEntity<>(HttpStatus.NOT_FOUND);

        OngoingStubbing<T> when = when(restTemplate.exchange(anyString(), any(), anyString())
                .thenReturn(debitAccountResponse)
                .thenReturn(creditAccountResponse);

        // Create a PaymentInstruction
        PaymentInstruction paymentInstruction = new PaymentInstruction("123", "456", 100.0);

        // Call the createPayment method
        ResponseEntity<String> responseEntity = paymentController.createPayment(paymentInstruction);

        // Assert the result
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Unable to fetch account details or account not found", responseEntity.getBody());
    }
}