package guavalearn;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by useheart on 2019-06-07
 *
 * @author useheart
 */
public class GuavaTest {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        /*Cache<String, String> cache = CacheBuilder.newBuilder()
                .removalListener(notification -> {
                    System.out.println(notification.getKey() + "----" + notification.getValue());
                })
                .expireAfterAccess(3, TimeUnit.SECONDS)
                .build();
        cache.put("zqm", 196 + "");
        System.out.println(cache.getIfPresent("zqm"));
        while (true) {
            Thread.sleep(TimeUnit.SECONDS.toMillis(3));
            System.out.println("等待中。。。");
        }*/

        LoadingCache<String, String> cache = CacheBuilder
                .newBuilder()
                .refreshAfterWrite(3, TimeUnit.SECONDS)
                .removalListener(notification -> {
                    System.out.println(notification.getKey() + "----" + notification.getValue());
                })
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) throws Exception {
                        System.out.println(key);
                        return 196 + "";
                    }
                });
        System.out.println(cache.getUnchecked("key"));
        while (true) {
            Thread.sleep(TimeUnit.SECONDS.toMillis(3));
            //System.out.println(cache.getUnchecked("zqm"));
            System.out.println("等待中。。。");
        }
    }
}
