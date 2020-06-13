import org.junit.Test;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by useheart on 2019-03-13
 */
public class Data {

    @Test
    public void test() throws InterruptedException {
        System.out.println(Runtime.getRuntime().availableProcessors());
        AtomicInteger atomicInteger = new AtomicInteger();
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    System.out.println(atomicInteger.incrementAndGet() + " 我们好啊");
                    if (atomicInteger.get() == 6) {
                        System.out.println("准备抛异常");
                        throw new DuplicateFormatFlagsException("");
                    }
                } catch (Exception e) {
                    System.out.println("发送异常");
                    timer.cancel();
                }

            }
        }, 1000, 1000);
        while (true) {
            Thread.sleep(10000);
        }
    }

    private int getData() {
        try {
            System.out.println("我是谁啊?");
            return 1;
        } finally {
            System.out.println("你是?");
        }
    }

    @Test
    public void arrayCopy() {
        int[] number1 = new int[]{1, 2, 3, 7, 4, 6};
        int[] number2 = new int[]{67454, 3232, 33232, 2323323, 2324, 324, 43, 3223, 223232, 3232};
        System.arraycopy(number1, 0, number2, 0, 6);
        for (int i = 0; i < number2.length; i++) {
            System.out.println(number2[i]);
        }

        int i = 1;
        System.out.println(i << 1);

    }

    @Test
    public void mainTest() {
        boolean flag = true;
        if (flag) {
            System.out.println("Hello, Java!");
        }
        if (flag == true) {
            System.out.println("Hello, JVM!");
        }

        boolean rice = true; // 直接编译的话 javac 会报错
        if (rice) {
            System.out.println(" 吃了 ");
        }
        if (true == rice) {
            System.out.println(" 真吃了 ");
        }

    }

    @Test
    public void testList() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            iterator.remove();
        }
        ListIterator<Integer> listIterator = list.listIterator();
        while (listIterator.hasNext()) {
            System.out.println(listIterator.next().toString());
            System.out.println("previousIndex:" + listIterator.previousIndex());
            System.out.println("nextIndex:" + listIterator.nextIndex());
        }
        while (listIterator.hasPrevious()) {
            System.out.println(listIterator.previous().toString());
        }
    }

    @Test
    public void cacheFast() {
        // Generate data
        int arraySize = 32768;
        int[] data = new int[arraySize];

        Random rnd = new Random(0);
        for (int c = 0; c < arraySize; ++c) {
            data[c] = rnd.nextInt() % 256;
        }

        // !!! With this, the next loop runs faster
        Arrays.sort(data);

        // Test
        long start = System.nanoTime();
        long sum = 0;

        for (int i = 0; i < 100000; ++i) {
            // Primary loop
            for (int c = 0; c < arraySize; ++c) {
                if (data[c] >= 128) {
                    sum += data[c];
                }
            }
        }

        System.out.println((System.nanoTime() - start) / 1000000000.0);
        System.out.println("sum = " + sum);
    }

    @Test
    public void leftMostSort() {
        // Generate data
        int arraySize = 32768;
        int[] data = new int[arraySize];

        Random rnd = new Random(0);
        for (int c = 0; c < arraySize; ++c) {
            data[c] = rnd.nextInt() % 256;
        }

        // Test
        long start = System.nanoTime();
        long sum = 0;
        int left = 0;
        int right = data.length;
        for (int i = left, j = i; i < right; j = ++i) {
            //后一个
            int ai = data[i + 1];
            // 循环
            while (ai < data[j]) {
                data[j + 1] = data[j];
                if (j-- == left) {
                    break;
                }
            }
            data[j + 1] = ai;
            System.out.println(i);
        }

        // 冒泡排序
        int i = data.length;
        for (int j = 0; j < data.length; j++) {
            while (data[i] < data[j]) {
                int temp = data[i];
                data[i] = data[j];
                data[j] = temp;
            }
            System.out.println(data[j]);
        }

        System.out.println((System.nanoTime() - start) / 1000000000.0);
        System.out.println("sum = " + sum);
    }

    @Test
    public void wantTest() {
        int j = 0;
        if (j-- == 0) {
            System.out.println("true");
        }
        System.out.println(j);
        int left = 1, right = 10;
        for (int k = left; ++left <= right; k = ++left) {
            int a1 = k, a2 = left;
            System.out.println("a1:" + a1);
            System.out.println("a2:" + a2);
        }
    }

    @Test
    public void mergeSort() {
        // Generate data
        int arraySize = 32768;
        int[] arr = new int[arraySize];

        Random rnd = new Random(0);
        for (int c = 0; c < arraySize; ++c) {
            arr[c] = rnd.nextInt() % 256;
        }

        int[] aux = new int[arr.length];
        mergeSort(arr, aux, 0, arr.length - 1);
        for (int i = 0; i < aux.length; i++) {
            System.out.println(aux[i]);
        }
    }

    private static void mergeSort(int[] arr, int[] aux, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        int mid = (lo + hi) >> 1;
        //divide
        mergeSort(arr, aux, lo, mid);
        mergeSort(arr, aux, mid + 1, hi);
        //merge
        System.arraycopy(arr, lo, aux, lo, hi - lo + 1);
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                arr[k] = aux[j++];
            } else if (j > hi) {
                arr[k] = aux[i++];
            } else if (aux[i] < aux[j]) {
                arr[k] = aux[i++];
            } else {
                arr[k] = aux[j++];
            }
        }
        return;
    }

    @Test
    public void testArrayList() {
        /*List<Integer> elements = new ArrayList<>();
        elements.add(1);
        //System.out.println(elements.get(1));

        StringBuilder s1 = new StringBuilder("Java");
        String s2 = "Love";
        s1.append(s2);
        s1.substring(4);
        int foundAt = s1.indexOf(s2);
        System.out.println(foundAt);*/
        /*int size = 8;
        Random rnd = new Random();
        nextRand:
        for (int i = size; i > 1; i--) {
            for (int j = size; j > 1; j--) {
                //swap(arr, i - 1, rnd.nextInt(i));
                if (i == 3) {
                    System.out.println("3");
                    continue nextRand;
                }
            }
        }*/
    }

    @Test
    public void thread() throws InterruptedException {
        System.out.println(-8 << 1);
        System.out.println(-8 * 2);
        String s = "123";
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        AtomicBoolean isEnd = new AtomicBoolean(false);
        executorService.submit(() -> {
            System.out.println("单线程竞争开始。。。");
            synchronized (s.intern()) {
                try {
                    System.out.println("单线程抢到。。。，开始等待。。。");
                    s.intern().wait(0);
                    System.out.println("完成等待时间。。。");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                isEnd.set(true);
            }
        });


        Thread.sleep(1000);

        System.out.println("主线程竞争开始。。。");
        synchronized (s.intern()) {
            try {
                System.out.println("主线程睡眠开始。。。");
                Thread.sleep(6000);
                System.out.println("主线程睡眠结束。。。");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                s.intern().notify();
            }
        }

        while (!isEnd.get()) {
            System.out.println("没有结束");
            Thread.sleep(1000);
        }


        Thread t1 = new Thread(new MyRunnable(), "t1");
        Thread t2 = new Thread(new MyRunnable(), "t2");
        Thread t3 = new Thread(new MyRunnable(), "t3");

        t1.start();

        //start second thread after waiting for 2 seconds or if it's dead
        try {
            t1.join(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t2.start();

        //start third thread only when first thread is dead
        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t3.start();

        //let all threads finish execution before finishing main thread
        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("All threads are dead, exiting main thread");
    }

    private long overflowFree(long delay) {
        long headDelay = TimeUnit.SECONDS.toNanos(20);
        if (headDelay < 0 && (delay - headDelay < 0)) {
            delay = Long.MAX_VALUE + headDelay;
        }
        return delay;
    }

    long triggerTime(long delay) {
        return System.nanoTime() +
                ((delay < (Long.MAX_VALUE >> 1)) ? delay : overflowFree(delay));
    }

    @Test
    public void threadJoin() {
        Thread t1 = new Thread(new MyRunnable(), "t1");
        Thread t2 = new Thread(new MyRunnable(), "t2");
        Thread t3 = new Thread(new MyRunnable(), "t3");

        t1.start();

        //start second thread after waiting for 2 seconds or if it's dead
        try {
            t1.join(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t2.start();

        //start third thread only when first thread is dead
        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t3.start();

        //let all threads finish execution before finishing main thread
        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("All threads are dead, exiting main thread");
    }

    private class MyRunnable implements Runnable {
        @Override
        public void run() {
            System.out.println("Thread started:::" + Thread.currentThread().getName());
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread ended:::" + Thread.currentThread().getName());
        }
    }

    @Test
    public void threadJoin2() throws InterruptedException {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("1+1...");
                    Thread.sleep(6000);
                    System.out.println("6000结束");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                atomicBoolean.set(true);
            }
        });
        thread.start();
        thread.join(1000);
        while (!atomicBoolean.get()) {
            System.out.println("还没有结束啊");
            Thread.sleep(1000);
        }
        System.out.println("All threads are dead, exiting main thread");
    }

    @Test
    public void threadDaemon() {
        Thread daemonThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        System.out.println("i am alive");
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        System.out.println("finally block");
                    }
                }
            }
        });
        daemonThread.setDaemon(true);
        daemonThread.start();
        //确保main线程结束前能给daemonThread能够分到时间片
        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void reflect() {
        /*Class<?> callerClass = Reflection.getCallerClass();
        System.out.println(callerClass.getName());*/
        System.out.println(Runtime.getRuntime().availableProcessors());
    }

    @Test
    public void charSize() {
        char letter = 'A';
        char chinese = '中';
        System.out.println(letter);
    }
}
