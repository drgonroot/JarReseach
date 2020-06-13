package concurrent.test;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by useheart on 2020-03-16
 *
 * @author useheart
 */
public class TimerInterruptThreadRunner {

    private static final ScheduledExecutorService cancelExec = Executors.newScheduledThreadPool(1);

    public static void timedRun(Runnable r, long timeout, TimeUnit unit) {
        final Thread taskThread = Thread.currentThread();
        ScheduledFuture<?> scheduledFuture = cancelExec.schedule(() -> {
            System.out.println("中断开始");
            taskThread.interrupt();
        }, timeout, unit);
        r.run();
        scheduledFuture.cancel(true);
    }

    public static void main(String[] args) throws InterruptedException {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        timedRun(() -> {
            System.out.println(atomicInteger.incrementAndGet());
        }, 3, TimeUnit.SECONDS);

        AtomicInteger endAtomicInteger = new AtomicInteger(0);
        System.out.println("结束开始。。。");
        while (true) {
            Thread.sleep(500);
            System.out.println("结束:" + endAtomicInteger.get());
        }

    }
}
