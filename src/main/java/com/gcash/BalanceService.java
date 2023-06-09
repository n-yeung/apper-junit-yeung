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