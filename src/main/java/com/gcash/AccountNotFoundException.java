package com.gcash;

public class AccountNotFoundException extends Exception {
    //exception is built in, default error message outside of if else conditions
    public AccountNotFoundException(String message) {
        super(message);
    }
}