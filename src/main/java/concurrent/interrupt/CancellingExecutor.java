package concurrent.interrupt;

import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * Created by useheart on 2020-03-22
 *
 * @author useheart
 */
interface CancelableTask<T> extends Callable<T> {
    void cancel();

    RunnableFuture<T> newTask();
}

@ThreadSafe
public class CancellingExecutor extends ThreadPoolExecutor {


    public CancellingExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    @Override
    protected <T> RunnableFuture<T> newTaskFor(Callable<T> callable) {
        if (callable instanceof CancelableTask) {
            return ((CancelableTask<T>) callable).newTask();
        } else {
            return super.newTaskFor(callable);
        }
    }

    public abstract class SocketUsingTask<T> implements CancelableTask<T> {
        @GuardedBy("this")
        private Socket socket;

        protected synchronized void setSocket(Socket socket) {
            this.socket = socket;
        }

        @Override
        public synchronized void cancel() {
            try {
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException e) {

            }
        }

        @Override
        public RunnableFuture<T> newTask() {
            return new FutureTask<T>(this) {

                @Override
                public boolean cancel(boolean mayInterruptIfRunning) {
                    try {
                        SocketUsingTask.this.cancel();
                    } finally {
                        return super.cancel(mayInterruptIfRunning);
                    }

                }
            };
        }
    }
}
