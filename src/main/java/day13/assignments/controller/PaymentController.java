package com.multithreading.assignments.controller;

import com.multithreading.assignments.model.Account;
import com.multithreading.assignments.model.CreditInstruction;
import com.multithreading.assignments.model.DebitInstruction;
import com.multithreading.assignments.model.Instruction;
import com.multithreading.assignments.model.PaymentInstruction;
import com.multithreading.assignments.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private RestTemplate restTemplate;


    @PostMapping("/create")
    public ResponseEntity<String> createPayment(@RequestBody PaymentInstruction instruction) {
        String debitAccount = instruction.getDebitAccount();
        String creditAccount = instruction.getCreditAccount();

        // Fetch account details for the debit account
        ResponseEntity<Account> debitAccountResponse = restTemplate.getForEntity(
                "http://your-account-api-url/api/accounts/payment-method/{identifier}",
                Account.class,
                debitAccount
        );

        // Fetch account details for the credit account
        ResponseEntity<Account> creditAccountResponse = restTemplate.getForEntity(
                "http://your-account-api-url/api/accounts/payment-method/{identifier}",
                Account.class,
                creditAccount
        );

        if (debitAccountResponse.getStatusCode().is2xxSuccessful() &&
                creditAccountResponse.getStatusCode().is2xxSuccessful()) {

            Account debitAccountDetails = debitAccountResponse.getBody();
            Account creditAccountDetails = creditAccountResponse.getBody();

            // Create DebitInstruction
            Instruction debitInstruction = new DebitInstruction();
            debitInstruction.setAccountNumber(debitAccountDetails.getAccountNumber());
            debitInstruction.setCurrency(debitAccountDetails.getCurrency());
            debitInstruction.setInstructionType("Debit");
            debitInstruction.setAmount(instruction.getAmount());


            // Create CreditInstruction
            Instruction creditInstruction = new CreditInstruction();
            creditInstruction.setAccountNumber(creditAccountDetails.getAccountNumber());
            creditInstruction.setCurrency(creditAccountDetails.getCurrency());
            creditInstruction.setInstructionType("Credit");
            creditInstruction.setAmount(instruction.getAmount());


            return ResponseEntity.ok("Payment instructions created successfully\n" +
                    "Debit Instruction: " + debitInstruction + "\n" +
                    "Credit Instruction: " + creditInstruction);
        } else {
            // Account not found or API call failed
            return ResponseEntity.badRequest().body("Unable to fetch account details or account not found");
        }
    }
}


