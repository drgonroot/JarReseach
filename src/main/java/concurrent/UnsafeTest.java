package concurrent;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by useheart on 2019-04-27
 *
 * @author useheart
 */
public class UnsafeTest {

    public static class StaticClass {
        private final int value;

        public StaticClass(int value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        // 正常使用，回包安全异常
        //Unsafe unsafe = Unsafe.getUnsafe();
        System.out.println();
        ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue<>();
        queue.offer(789);
        System.out.println(queue.poll());
    }
}
