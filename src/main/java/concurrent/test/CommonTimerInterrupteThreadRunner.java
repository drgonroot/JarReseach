package concurrent.test;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by useheart on 2020-03-22
 *
 * @author useheart
 */
public class CommonTimerInterrupteThreadRunner {

    private static final ScheduledExecutorService cancelExec = Executors.newScheduledThreadPool(1);

    public static void timeRun(final Runnable r, long timeout, TimeUnit timeUnit) throws Throwable {
        class RethrowableTask implements Runnable {
            private volatile Throwable t;

            @Override
            public void run() {
                try {
                    r.run();
                } catch (Throwable t) {
                    this.t = t;
                }
            }

            void rethrow() throws Throwable {
                if (t != null) {
                    throw t;
                }
            }
        }

        RethrowableTask task = new RethrowableTask();
        final Thread taskThread = new Thread(task);
        taskThread.start();
        cancelExec.schedule(new Runnable() {
            @Override
            public void run() {
                taskThread.interrupt();
            }
        }, timeout, timeUnit);
        taskThread.join(timeUnit.toMillis(timeout));
        task.rethrow();
    }

    public static String test() {
        try {
            System.out.println("test1");
            return "true";
        } finally {
            System.out.println("test2");
            return "false";
        }
    }

    public static void main(String[] args) {
        System.out.println(test());
    }
}
