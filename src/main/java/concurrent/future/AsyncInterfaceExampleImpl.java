package concurrent.future;

import java.util.concurrent.CompletableFuture;

/**
 * Created by useheart on 2020/7/1
 *
 * @author useheart
 */
public class AsyncInterfaceExampleImpl implements AsyncInterfaceExample {

    @Override
    public String computeSomeThine() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "hello, world";
    }

    @Override
    public CompletableFuture<String> computeSomeThingAsync() {
        return CompletableFuture.supplyAsync(this::computeSomeThine);
    }
}
