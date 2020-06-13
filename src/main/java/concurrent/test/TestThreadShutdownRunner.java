package concurrent.test;

/**
 * Created by useheart on 2020/5/9
 *
 * @author useheart
 */
public class TestThreadShutdownRunner {

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000 * 5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程结束了");
            }
        }).start();
        System.out.println("结束了");
    }
}
