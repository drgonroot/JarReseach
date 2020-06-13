package concurrent.sync;

/**
 * Created by useheart on 2019-12-28
 *
 * @author useheart
 */
public class ChildSyncLockTest extends SyncLockTest {

    public static synchronized void doSomething() throws InterruptedException {
        System.out.println("ChildSyncLockTest something start");
        Thread.sleep(3000);
        SyncLockTest.doSomething();
        System.out.println("ChildSyncLockTest something end ");
    }

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            try {
                System.out.println("thread start");
                SyncLockTest.doSomething();
                System.out.println("thread end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        ChildSyncLockTest.doSomething();
    }
}
