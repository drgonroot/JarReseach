package concurrent.lock;

/**
 * Created by useheart on 2020-04-06
 *
 * @author useheart
 */
public class Account implements Comparable<Integer> {
    @Override
    public int compareTo(Integer o) {
        return 0;
    }

    public Comparable<TransferMoneyLock.DollarAmount> getBalance() {
        return null;
    }

    public void debit(TransferMoneyLock.DollarAmount amount) {

    }

    public void credit(TransferMoneyLock.DollarAmount amount) {

    }
}
