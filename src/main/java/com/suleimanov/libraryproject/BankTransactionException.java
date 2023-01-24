package com.suleimanov.libraryproject;

public class BankTransactionException extends Exception {
    private static final long serialVersionUID = -3128681006635769411L;

    public BankTransactionException(String message) {
        super(message);
    }
}
