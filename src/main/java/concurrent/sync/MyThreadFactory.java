package concurrent.sync;

import java.util.concurrent.ThreadFactory;

/**
 * Created by useheart on 2020-03-30
 *
 * @author useheart
 */
public class MyThreadFactory implements ThreadFactory {
    private final String poolName;

    public MyThreadFactory(String poolName) {
        this.poolName = poolName;
    }

    @Override
    public Thread newThread(Runnable r) {
        return new MyAppThread(r, poolName);
    }
}
