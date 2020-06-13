package concurrent.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by useheart on 2020-03-09
 *
 * @author useheart
 */
public class TestShutDownRunner {

    public static void main(String[] args) {
        try {
            ExecutorService executorService = Executors.newSingleThreadExecutor();

            executorService.submit(() -> {
                System.out.println("辅助线程1执行开始");
                try {
                    Thread.sleep(TimeUnit.SECONDS.toMillis(4));
                    System.out.println("辅助线程1睡眠结束");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

            //Thread.sleep(TimeUnit.SECONDS.toMillis(3));
            executorService.submit(() -> {
                System.out.println("辅助线程2执行开始");
                try {
                    Thread.sleep(TimeUnit.SECONDS.toMillis(4));
                    System.out.println("辅助线程2睡眠结束");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

            /**
             * 仅awaitTermination：
             * 辅助线程1执行开始
             * 主线程结束...
             * 辅助线程1睡眠结束
             * 辅助线程2执行开始
             * 钩子执行完成
             *
             *
             * 仅shutdown：
             * 辅助线程1执行开始
             * 主线程结束...
             * 钩子执行完成
             * */
            Runtime.getRuntime().addShutdownHook(
                    new Thread(() -> {
                        executorService.shutdown();
                        try {
                            executorService.awaitTermination(12, TimeUnit.SECONDS);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        System.out.println("钩子执行完成");
                    }));

            System.out.println("主线程睡眠开始");
            try {
                Thread.sleep(1000 * 3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("主线程睡眠结束");

            executorService.submit(() -> {
                System.out.println("辅助线程3执行开始");
                try {
                    Thread.sleep(TimeUnit.SECONDS.toMillis(4));
                    System.out.println("辅助线程3睡眠结束");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        } finally {
            System.out.println("主线程结束...");
            // System.exit(0);
        }
    }
}
