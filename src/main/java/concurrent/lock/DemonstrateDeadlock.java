package concurrent.lock;

import java.util.Random;

/**
 * Created by useheart on 2020-04-06
 *
 * @author useheart
 */
public class DemonstrateDeadlock {
    private static final int NUM_THREADS = 20;
    private static final int NUM_ACCOUNTS = 5;
    private static final int NUM_ITERATIONS = 1000000;

    public static void main(String[] args) {
        final Random rnd = new Random();
        final Account[] accounts = new Account[NUM_ACCOUNTS];

        for (int i = 0; i < accounts.length; i++) {
            accounts[i] = new Account();
        }

        class TransferThread extends Thread {
            @Override
            public void run() {
                for (int i = 0; i < NUM_ITERATIONS; i++) {
                    int fromAcct = rnd.nextInt(NUM_ACCOUNTS);
                    int toAcct = rnd.nextInt(NUM_ACCOUNTS);
                    TransferMoneyLock.DollarAmount amount = new TransferMoneyLock.DollarAmount(rnd.nextInt(1000));
                    try {
                        new TransferMoneyLock().transferMoney(accounts[fromAcct], accounts[toAcct], amount);
                    } catch (InsufficientFundsException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        for (int i = 0; i < NUM_THREADS; i++) {
            new TransferThread().start();
        }
    }
}
