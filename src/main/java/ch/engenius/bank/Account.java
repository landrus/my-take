package ch.engenius.bank;

import java.math.BigDecimal;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Account {

    private double money;
    private final Lock lock = new ReentrantLock();

    public Account(double initialAmount) {
        money = initialAmount;
    }

    public Lock getLock() {
        return lock;
    }

    public boolean hasEnoughCredit(double amount) {
        return money >= amount;
    }

    public void withdraw(double amount) {
        if (!hasEnoughCredit(amount)) {
            throw new IllegalStateException("not enough credits on account");
        }

        changeMoney(money - amount);
    }

    public void deposit(double amount) {
        changeMoney(money + amount);
    }

    private void changeMoney(double newAmount) {
        money = newAmount;
    }

    public BigDecimal getMoneyAsBigDecimal() {
        return BigDecimal.valueOf(money);
    }

}
