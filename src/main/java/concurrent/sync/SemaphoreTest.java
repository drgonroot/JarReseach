package concurrent.sync;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Created by useheart on 2020-03-30
 *
 * @author useheart
 */
public class SemaphoreTest {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(1);
        new Thread(() -> {
            try {
                semaphore.acquire();
                System.out.println("获取semaphore");
                Thread.sleep(TimeUnit.SECONDS.toMillis(3));
                System.out.println("睡眠了。。。。");
                //semaphore.acquire();
            } catch (InterruptedException e) {
                System.out.println("\"获取semaphore\"打断");
            } finally {
                semaphore.release();
            }
        }).start();

        System.out.println("主线输出");
        try {
            semaphore.acquire();
            System.out.println("主线输出semaphore");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }
}
