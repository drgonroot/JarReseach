package watch;

/**
 * Created by useheart on 2019-04-18
 *
 * @author useheart
 */
public class Test2 extends Test {

    @Override
    public void test() {
        new inner();
    }

    static class inner {

    }
}
