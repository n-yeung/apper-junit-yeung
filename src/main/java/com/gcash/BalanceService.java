package com.gcash;

public class BalanceService {

    /**
     * NOTE: You are expected to use one repository instance in all methods, not one repository per method.
     *
     */
    private final AccountRepository userDetail;

    public BalanceService(AccountRepository userDetail){
        this.userDetail = userDetail;
    }

    public Double getBalance(String id) {
        Account account = userDetail.getAccount(id);
        if (account == null) return null;
        else return account.getBalance();
    }

    public void debit(String id, Double amount) {
        Account account = userDetail.getAccount(id);
        if (account != null && account.getBalance() >= amount) {
            account.setBalance(account.getBalance() - amount);
        }
    }

    public void credit(String id, Double amount) {
        Account account = userDetail.getAccount(id);
        if (account != null) {
            account.setBalance(account.getBalance() + amount);
        }
    }

    public void transfer(String from, String to, Double amount) {
        Account sender = userDetail.getAccount(from);
        Account receiver = userDetail.getAccount(to);
        if (sender != null && receiver != null) {
            if(sender.getBalance() >= amount){
                debit(from, amount);
                credit(to, amount);
            }
        }
    }
}

/*
package com.gcash;

import java.util.Objects;

public class BalanceService {
    private final AccountRepository accountRepository;

    public BalanceService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    public Double getBalance(String id) throws AccountNotFoundException { //use throws for the error message
        Account foundAccount = accountRepository.getAccount(id);
        if (Objects.isNull(foundAccount)) {
            throw new AccountNotFoundException("Account " + id + " not found"); //failure cases
        }
        return foundAccount.getBalance();
    }

    public void debit(String id, Double amount) throws InsufficientBalanceException, AccountNotFoundException {
        Account foundAccount = accountRepository.getAccount(id);
        if (Objects.isNull(foundAccount)) {
            throw new AccountNotFoundException("Account " + id + " not found");
        }

        if (foundAccount.getBalance() >= amount) {
            foundAccount.setBalance(foundAccount.getBalance() - amount);
        } else {
            throw new InsufficientBalanceException("Insufficient balance!");
        }
    }

    public void credit(String id, Double amount) {
        Account foundAccount = accountRepository.getAccount(id);
        if (Objects.nonNull(foundAccount)) {
            foundAccount.setBalance(foundAccount.getBalance() + amount);
        }
    }

    public void transfer(String from, String to, Double amount) throws InsufficientBalanceException, AccountNotFoundException {
        debit(from, amount); //debit first, check if sufficient balance to avoid needing to undo credit
        credit(to, amount);
    }
}
 */