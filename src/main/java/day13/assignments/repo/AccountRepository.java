package com.multithreading.assignments.repo;

import com.multithreading.assignments.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {

    public Optional<Account> findById(String accountId);

    @Query("SELECT a FROM Account a JOIN FETCH a.paymentMethods pm WHERE pm.identifier = :identifier")
    Account findAccountByPaymentMethod(@Param("identifier") String identifier);

}