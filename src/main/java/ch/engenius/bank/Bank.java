package ch.engenius.bank;

import java.util.HashMap;

public class Bank {

    private final HashMap<Integer, Account> accounts = new HashMap<>();

    public void registerAccount(int accountNumber, int initialBalance) {
        accounts.put(accountNumber, new Account(initialBalance));
    }

    public Account getAccount(int number) {
        return accounts.get(number);
    }

}
