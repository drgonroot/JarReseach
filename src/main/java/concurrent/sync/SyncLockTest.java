package concurrent.sync;

/**
 * Created by useheart on 2019-12-28
 *
 * @author useheart
 */
public class SyncLockTest {

    public static synchronized void doSomething() throws InterruptedException {
        System.out.println("SyncLockTest something !");
    }
}
