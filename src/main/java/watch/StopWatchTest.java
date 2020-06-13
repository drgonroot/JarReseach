package watch;

import com.ecyrd.speed4j.StopWatch;
import com.ecyrd.speed4j.StopWatchFactory;

import java.util.concurrent.TimeUnit;

/**
 * Created by useheart on 2019-03-16
 */
public class StopWatchTest {

    private static StopWatchFactory myStopWatchFactory = StopWatchFactory.getInstance("loggingFactory");

    public static void watch() {
        Test test = new Test();
        StopWatch sw = myStopWatchFactory.getStopWatch();
        try {
            // Do the busy thing
            TimeUnit.SECONDS.sleep(1);
            // Notice that sw.stop() automatically logs if the Factory is configured so
            sw.stop("busyThing:success");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            sw.stop("busything:failure");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int iterations = 10000000;
        for (int i = 0; i < iterations; i++) {
            watch();
            TimeUnit.SECONDS.sleep(10);
        }
    }
}
