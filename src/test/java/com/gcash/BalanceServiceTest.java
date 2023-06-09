package com.gcash;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Assertions;

class BalanceServiceTest {

    @Test
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