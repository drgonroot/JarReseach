package concurrent.test;

import java.util.concurrent.*;

/**
 * Created by useheart on 2020/6/2
 *
 * @author useheart
 */
public class TestThreadAwaitTerminationRunner {

    public static void main(String[] args) throws InterruptedException {
        try {
            ExecutorService executorService = Executors.newSingleThreadExecutor();
            /**
             * finally 不调用 System.exit(0)，则main不退出直至最后一个线程结束
             * */
            executorService.submit(() -> {
                System.out.println("任务一开始");
                int seconds = 3;
                Thread.sleep(TimeUnit.SECONDS.toMillis(seconds));
                System.out.println("任务一" + seconds + "秒睡眠结束");
                throw new InterruptedException();
            });

            Future<String> futureTask = executorService.submit((Callable<String>) () -> {
                System.out.println("含返回值任务一开始");
                int seconds = 3;
                Thread.sleep(TimeUnit.SECONDS.toMillis(seconds));
                System.out.println("含返回值任务一" + seconds + "秒睡眠结束");
                return "加油";
            });
            try {
                System.out.println("含返回值任务");
                String task = futureTask.get();
                System.out.println("含返回值任务:" + task);
            } catch (ExecutionException e) {
                System.out.println("ExecutionException执行失败");
            }

            int task = 15;
            while (task-- > 0) {
                int finalTask = task;
                executorService.submit(() -> {
                    System.out.println("任务" + finalTask + "开始");
                    int seconds = 1;
                    Thread.sleep(TimeUnit.SECONDS.toMillis(seconds));
                    System.out.println("任务" + finalTask + "-" + seconds + "秒睡眠结束");
                    throw new InterruptedException();
                });
            }

            /**
             * 执行后，executorService不在接受任务了。但不保证之前任务完整执行完
             * */
            executorService.shutdown();
            try {
                executorService.submit(() -> {
                    System.out.println("任务二开始");
                    try {
                        Thread.sleep(TimeUnit.SECONDS.toMillis(4));
                        System.out.println("任务二4秒睡眠结束");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            } catch (RejectedExecutionException e) {
                System.out.println("executorService拒绝提交任务");
            }
            /**
             * 等待所有任务执行完成、或者时间超时了
             * 执行这个语句，会立刻等5秒钟
             * */
            executorService.awaitTermination(3, TimeUnit.SECONDS);
            int seconds = 10;
            while (seconds-- > 0) {
                Thread.sleep(1000);
                System.out.println("主线程睡眠一秒:" + executorService.isTerminated() + ":" + executorService.isShutdown());
            }
        } finally {
            System.out.println("主线程结束...");
            //System.exit(0);
        }
    }
}
