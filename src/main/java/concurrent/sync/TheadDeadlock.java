package concurrent.sync;

import java.io.File;
import java.util.concurrent.*;

/**
 * Created by useheart on 2020-03-28
 *
 * @author useheart
 */
public class TheadDeadlock {

    ExecutorService exec = Executors.newSingleThreadExecutor();
    ExecutorService executorService = Executors.newCachedThreadPool();

    static ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(1));

    static {
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("我是谁的");
            }
        });
    }


    public class RenderPageTask implements Callable<String> {

        @Override
        public String call() throws Exception {
            Future<String> header, footer;
            header = exec.submit(new LoadFileTask("header.html"));
            footer = exec.submit(new LoadFileTask("footer.html"));
            String page = renderBody();
            // 将发现死锁 == 由于任务在等待子任务的结果
            return header.get() + page + footer.get();
        }

        private String renderBody() {
            return "body";
        }

        private class LoadFileTask implements Callable<String> {
            File file;

            public LoadFileTask(String filePath) {
                file = new File(filePath);
            }

            @Override
            public String call() throws Exception {
                return "";
            }
        }
    }
}
