package ch.engenius.bank;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class BankRunnerTest {

    @Test
    void executeBank() {
        BankRunner runner = new BankRunner();
        int accounts = 100;
        int defaultDeposit = 1000;
        int iterations = 10000;
        runner.registerAccounts(accounts, defaultDeposit);
        assertEquals(accounts * defaultDeposit, runner.getBalance(accounts));
        runner.runBank(iterations, accounts);
        assertEquals(accounts * defaultDeposit, runner.getBalance(accounts));
    }

}
