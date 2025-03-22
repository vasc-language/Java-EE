package two;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-01-14
 * Time: 17:14
 */
class Counter {
    private static int count = 0;

    /*public void increment() {
        count++; // 非原子的操作
    }*/
    public synchronized void increment() {
        count++; // 变成原子操作
    }

    public int getCount() {
        return count;
    }
}
public class Demo5 {
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();

        // 创建两个线程，分别执行 increment 操作
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 50000; i++) {
                counter.increment();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 50000; i++) {
                counter.increment();
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        // 理论上是 10w 的
        System.out.println("count: " + counter.getCount()); // count: 53131
    }
}
