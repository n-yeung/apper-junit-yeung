package com.gcash;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Assertions;

class BalanceServiceTest {

    //future uses
    //assertions have a third parameter, putting a message. If it fails the message will be seen
    //used for debugging, discriptive error

    @Test
    @DisplayName("Successful Balance Retrieval")// instead of displaying the method name in testing, it shows this
    void successGetBalance() {
        AccountRepository repository = new AccountRepository();
        BalanceService balService = new BalanceService(repository);
        String accountId = repository.createAccount("Nathan", 89.9);
        String testEmpty = "does not exist";

        Assertions.assertNotNull(balService.getBalance(accountId));
        Assertions.assertEquals(89.9, balService.getBalance(accountId));
        Assertions.assertNull(balService.getBalance(testEmpty));
    }

    @Test
    void successDebit() {
        AccountRepository repository = new AccountRepository();
        BalanceService balService = new BalanceService(repository);
        String accountId = repository.createAccount("Nathan", 89.9);

        balService.debit(accountId,39.0);
        Assertions.assertEquals(50.9, balService.getBalance(accountId), 0.001);
    }

    @Test
    void successCredit() {
        AccountRepository repository = new AccountRepository();
        BalanceService balService = new BalanceService(repository);
        String accountId = repository.createAccount("Nathan", 89.9);

        balService.credit(accountId,10.1);
        Assertions.assertEquals(100, balService.getBalance(accountId));
    }

    @Test
    void successTransfer() {
        AccountRepository repository = new AccountRepository();
        BalanceService balService = new BalanceService(repository);
        String sender = repository.createAccount("Nathan", 50.0);
        String receiver = repository.createAccount("Orvyl", 50.0);

        balService.transfer(sender, receiver, 25.0);

        Assertions.assertEquals(25.0, balService.getBalance(sender));
        Assertions.assertEquals(75.0, balService.getBalance(receiver));
    }
}

/*
package com.gcash;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BalanceServiceTest {

    AccountRepository accountRepository;
    BalanceService balanceService;

    @BeforeEach
    void setup() {
        System.out.println("Setting up...");
        accountRepository = new AccountRepository();
        balanceService = new BalanceService(accountRepository);
    }

    @AfterEach
    void cleanup() {
        System.out.println("Cleaning up...");
        accountRepository.deleteAllAccounts();
    }

    @BeforeAll
    static void globalSetup() {
        System.out.println("Global setup");
    }

    @AfterAll
    static void globalCleaning() {
        System.out.println("Global cleaning");
    }

    @Test
    void testGetBalance() throws AccountNotFoundException {
        double initialBalance = 1000.0;
        String id = accountRepository.createAccount("Orvyl", initialBalance);

        Double balance = balanceService.getBalance(id);

        Assertions.assertEquals(initialBalance, balance);
    }

    @Test
    void testGetBalanceAccountNotFound() {
        double initialBalance = 1000.0;
        accountRepository.createAccount("Orvyl", initialBalance);

        Assertions.assertThrows(AccountNotFoundException.class, () -> balanceService.getBalance("random id"));
    }

    @Test
    void testDebit() throws InsufficientBalanceException, AccountNotFoundException {
        double initialBalance = 1000.0;
        String id = accountRepository.createAccount("Orvyl", initialBalance);

        double debitedAmount = 50.0;
        balanceService.debit(id, debitedAmount);

        Double currentBalance = accountRepository.getAccount(id).getBalance();

        Assertions.assertEquals(initialBalance - debitedAmount, currentBalance);
    }

    @Test
    void testDebitInsufficientBalance() {
        double initialBalance = 1000.0;
        String id = accountRepository.createAccount("Orvyl", initialBalance);

        double debitedAmount = 50_000.0;
        Assertions.assertThrows(InsufficientBalanceException.class, () -> balanceService.debit(id, debitedAmount));
    }

    @Test
    void testDebitAccountNotFound() {
        double initialBalance = 1000.0;
        String id = accountRepository.createAccount("Orvyl", initialBalance);

        double debitedAmount = 50_000.0;
        Assertions.assertThrows(AccountNotFoundException.class, () -> balanceService.debit("random id", debitedAmount));
    }

    @Test
    void testTransfer() throws InsufficientBalanceException, AccountNotFoundException {
        double initialBalance = 1000.0;
        String id0 = accountRepository.createAccount("Orvyl", initialBalance);
        String id1 = accountRepository.createAccount("Eishi", initialBalance);

        double transferAmount = 50.0;
        balanceService.transfer(id0, id1, transferAmount);

        Assertions.assertAll(
                () -> Assertions.assertEquals(initialBalance - transferAmount, accountRepository.getAccount(id0).getBalance()),
                () -> Assertions.assertEquals(initialBalance + transferAmount, accountRepository.getAccount(id1).getBalance())
        );
    }
}
 */