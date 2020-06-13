package concurrent.sync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by useheart on 2020-03-30
 *
 * @author useheart
 */
public class CustomExecutorService {
    private ExecutorService executorService = Executors.newCachedThreadPool();

    public void setCorePoolSize() {
        if (executorService instanceof ThreadPoolExecutor) {
            ((ThreadPoolExecutor) executorService).setCorePoolSize(10);
        } else {
            throw new AssertionError("Oops, bad assumption");
        }

    }


}
