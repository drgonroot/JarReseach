package concurrent.lock;

/**
 * Created by useheart on 2020-03-31
 * KITE 注意：容易发生死锁
 * 可以通过限制获取锁的顺序方式，来避免这种情况发生
 *
 * @author useheart
 */
public class LeftRightDeadlock {
    private final Object left = new Object();
    private final Object right = new Object();

    public void leftRight() {
        synchronized (left) {
            synchronized (right) {
                doSomething();
            }
        }
    }

    public void rightLeft() {
        synchronized (right) {
            synchronized (left) {
                doSomethingElse();
            }
        }
    }

    private void doSomethingElse() {

    }

    private void doSomething() {


    }
}
