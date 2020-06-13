package concurrent.test;

import java.util.concurrent.TimeUnit;

/**
 * Created by useheart on 2020-03-14
 *
 * @author useheart
 */
public class InterruptThreadRunner {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            try {
                System.out.println("开始睡觉-" + "thread interrupt status:" + Thread.currentThread().isInterrupted());
                Thread.sleep(TimeUnit.SECONDS.toMillis(5));
            } catch (InterruptedException e) {
                System.out.println("thread interrupt status:" + Thread.currentThread().isInterrupted());
                System.out.println("打断睡觉");
                System.out.println("ex:" + e);
            }
            System.out.println("睡觉完成-" + "thread interrupt status:" + Thread.currentThread().isInterrupted());
        });
        thread.start();
        try {
            Thread.sleep(TimeUnit.SECONDS.toMillis(1));
        } catch (InterruptedException e) {
            System.out.println("主线程中断");
            e.printStackTrace();
        }
        System.out.println("开始中断");
        thread.interrupt();
    }
}
