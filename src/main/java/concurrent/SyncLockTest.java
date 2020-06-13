package concurrent;

/**
 * Created by useheart on 2019-12-28
 * 可重入锁 继承方法含有锁 研究
 *
 * @author useheart
 */
public class SyncLockTest {

    public static void main(String[] args) throws InterruptedException {
        final TestChild t = new TestChild();

        new Thread(new Runnable() {
            @Override
            public void run() {
                t.doSomething();
            }
        }).start();
        Thread.sleep(100);
        t.doSomethingElse();
    }

    public synchronized void doSomething() {
        System.out.println("something sleepy!");
        try {
            Thread.sleep(1000);
            System.out.println("woke up!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static class TestChild extends SyncLockTest {
        @Override
        public void doSomething() {
            super.doSomething();
        }

        public synchronized void doSomethingElse() {
            System.out.println("something else");
        }
    }

}
