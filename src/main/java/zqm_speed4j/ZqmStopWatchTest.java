package zqm_speed4j;

import com.ecyrd.speed4j.StopWatchFactory;
import org.junit.Test;

/**
 * Created by useheart on 2019-03-17
 */
public class ZqmStopWatchTest {

    @Test
    public void toStringTest() {
        StopWatchFactory.getInstance("facotryLogger");
        ZqmStopWatch zqmStopWatch = new ZqmStopWatch();
        while (true) {
            System.out.println(zqmStopWatch.toString());
        }
    }
}
