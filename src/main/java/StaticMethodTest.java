/**
 * Created by useheart on 2019-03-16
 */
public class StaticMethodTest {

    static {
        System.out.println("静态块。。。。");
    }

    public static void staticMethod() {
        System.out.println("静态方法。。。。");
    }

    {
        System.out.println("方法块");
    }

    public void commonMethod() {
        System.out.println("普通方法");
    }

    public static void main(String[] args) {
        System.out.println("main执行开始。。。");
    }
}
