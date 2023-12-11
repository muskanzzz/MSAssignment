package com.multithreading.springmultithreading.controllertest;

import com.multithreading.assignments.controller.AccountController;
import com.multithreading.assignments.model.Account;
import com.multithreading.assignments.model.DebitCard;
import com.multithreading.assignments.service.AccountService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Optional;
import static org.mockito.Mockito.when;

public class AccountControllerSteps {

    @Mock
    private AccountService accountService;

    @InjectMocks
    private AccountController accountController;

    private String accountId;
    private String paymentMethodIdentifier;
    private ResponseEntity<Optional<Account>> responseWithOptional;
    private ResponseEntity<Account> response;

    @Given("an account with ID {string}")
    public void anAccountWithID(String accountId) {
        this.accountId = accountId;
    }

    @Given("an account with ID {string} does not exist")
    public void anAccountWithIDDoesNotExist(String accountId) {
        this.accountId = accountId;
        when(accountService.getAccountById(accountId)).thenReturn(Optional.empty());
    }

    @Given("an account with a payment method {string}")
    public void anAccountWithAPaymentMethod(String paymentMethodIdentifier) {
        this.paymentMethodIdentifier = paymentMethodIdentifier;
        Account mockAccount = new Account("1", "USD", "BankA", "Branch1");
        mockAccount.addPaymentMethod(new DebitCard("1111-2222-3333-4444"));
        when(accountService.getAccountByPaymentMethod(paymentMethodIdentifier)).thenReturn(mockAccount);
    }

    @Given("an account with a payment method {string} does not exist")
    public void anAccountWithAPaymentMethodDoesNotExist(String paymentMethodIdentifier) {
        this.paymentMethodIdentifier = paymentMethodIdentifier;
        when(accountService.getAccountByPaymentMethod(paymentMethodIdentifier)).thenReturn(null);
    }

    @When("the user requests account details")
    public void theUserRequestsAccountDetails() {
        responseWithOptional = accountController.getAccountDetails(accountId);
    }

    @When("the user requests account by payment method")
    public void theUserRequestsAccountByPaymentMethod() {
        response = accountController.getAccountByPaymentMethod(paymentMethodIdentifier);
    }

    @Then("the response status should be OK")
    public void theResponseStatusShouldBeOK() {
        Assertions.assertEquals(HttpStatus.OK, responseWithOptional.getStatusCode());
    }

    @Then("the response status should be NOT FOUND")
    public void theResponseStatusShouldBeNotFound() {
        Assertions.assertEquals(HttpStatus.NOT_FOUND, responseWithOptional.getStatusCode());
    }

    @Then("the response body should contain account details")
    public void theResponseBodyShouldContainAccountDetails() {
        Optional<Account> accountOptional = responseWithOptional.getBody();
        Assertions.assertNotNull(accountOptional);
        Assertions.assertTrue(accountOptional.isPresent());
        Assertions.assertEquals(accountId, accountOptional.get().getId());
    }

    @Then("the response body should be empty")
    public void theResponseBodyShouldBeEmpty() {
        Optional<Account> accountOptional = responseWithOptional.getBody();
        Assertions.assertNotNull(accountOptional);
        Assertions.assertTrue(accountOptional.isEmpty());
    }

    @Then("the response body should contain account details by payment method")
    public void theResponseBodyShouldContainAccountDetailsByPaymentMethod() {
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(paymentMethodIdentifier, response.getBody().getId());
    }
}
