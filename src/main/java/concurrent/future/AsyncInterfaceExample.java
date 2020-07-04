package concurrent.future;

import java.util.concurrent.CompletableFuture;

/**
 * Created by useheart on 2020/7/1
 *
 * @author useheart
 */
public interface AsyncInterfaceExample {

    String computeSomeThine();

    CompletableFuture<String> computeSomeThingAsync();
}
