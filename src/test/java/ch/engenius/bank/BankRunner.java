package ch.engenius.bank;

import java.math.BigDecimal;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

class BankRunner {

    private static final ExecutorService executor = Executors.newFixedThreadPool(8);

    private final Random random = new Random(43);
    private final Bank bank = new Bank();

    void registerAccounts(int number, int defaultMoney) {
        for (int i = 0; i < number; i++) {
            bank.registerAccount(i, defaultMoney);
        }
    }

    int getBalance(int accountMaxNumber) {
        return IntStream.range(0, accountMaxNumber)
                .mapToObj(bank::getAccount)
                .map(Account::getBalanceAsBigDecimal)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .intValue();
    }

    void runBank(int iterations, int maxAccount) {
        for (int i = 0; i < iterations; i++) {
            executor.submit(() -> runRandomOperation(maxAccount));
        }

        try {
            executor.shutdown();
            executor.awaitTermination(100, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void runRandomOperation(int maxAccount) {
        double transfer = random.nextDouble() * 100.0;
        int accountInNumber = random.nextInt(maxAccount);
        int accountOutNumber = random.nextInt(maxAccount);
        Account accIn = bank.getAccount(accountInNumber);
        Account accOut = bank.getAccount(accountOutNumber);

        Transaction transaction = new Transaction(accOut, accIn, transfer);
        transaction.execute();
    }

}
