package ch.engenius.bank;

import java.math.BigDecimal;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Account {

    private double balance;
    private final Lock lock = new ReentrantLock();

    public Account(double initialBalance) {
        balance = initialBalance;
    }

    public Lock getLock() {
        return lock;
    }

    public boolean hasEnoughCredit(double amount) {
        return balance >= amount;
    }

    public void withdraw(double amount) {
        if (hasEnoughCredit(amount)) {
            changeBalance(balance - amount);
        } else {
            throw new IllegalStateException("not enough credits on account");
        }
    }

    public void deposit(double amount) {
        changeBalance(balance + amount);
    }

    private void changeBalance(double newAmount) {
        balance = newAmount;
    }

    public BigDecimal getBalanceAsBigDecimal() {
        return BigDecimal.valueOf(balance);
    }

}
