package concurrent.future;

import java.util.concurrent.CompletableFuture;

/**
 * Created by useheart on 2020/7/1
 *
 * @author useheart
 */
public class AsyncInterfaceExampleTest {

    private static String getOtherThing() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "other";
    }

    public static void main(String[] args) {
        AsyncInterfaceExample asyncInterfaceExample = new AsyncInterfaceExampleImpl();

        //case1 同步调用
        long start = System.currentTimeMillis();
        String someThing = asyncInterfaceExample.computeSomeThine();
        String other = getOtherThing();
        System.out.println("cost:" + (System.currentTimeMillis() - start) + "  result:" + someThing + other);

        //case2 异步调用，使用回调
        start = System.currentTimeMillis();
        CompletableFuture<String> someThingFuture = asyncInterfaceExample.computeSomeThingAsync();
        other = getOtherThing();

        long finalStart = start;
        String finalOther = other;
        someThingFuture.whenComplete((returnValue, exception) -> {
            if (exception == null) {
                System.out.println(
                        "cost:" + (System.currentTimeMillis() - finalStart) + "  result:" + returnValue + finalOther);
            } else {
                exception.printStackTrace();
            }
        });
    }
}
