package concurrent.sync;


import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by useheart on 2020-03-28
 *
 * @author useheart
 */
public interface UncaughtExceptionHandler {
    void uncaughtException(Thread t, Throwable e);
}

class UEHLogger implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        Logger logger = Logger.getAnonymousLogger();
        logger.log(Level.SEVERE, "Thread terminated with exception :" + t.getName(), e);
    }
}
