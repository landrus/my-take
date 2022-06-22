package ch.engenius.bank;

import java.util.concurrent.locks.Lock;

public class Transaction {

    private final Account debit;
    private final Account credit;
    private final double amount;

    public Transaction(Account debit, Account credit, double amount) {
        this.debit = debit;
        this.credit = credit;
        this.amount = amount;
    }

    public void execute() {
        Lock in = debit.getLock();
        Lock out = credit.getLock();

        try {
            in.lock();
            out.lock();

            if (debit.hasEnoughCredit(amount)) {
                debit.withdraw(amount);
                credit.deposit(amount);
            }
        } finally {
            out.unlock();
            in.unlock();
        }
    }

}
