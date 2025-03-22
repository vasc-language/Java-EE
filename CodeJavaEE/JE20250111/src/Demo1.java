import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-01-12
 * Time: 0:30
 */
public class Demo1 {
    // 使用原子类替代 int
    // private static int count = 0;
    private static AtomicInteger count = new AtomicInteger(0); // 初始值为 0

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 50000; i++) {
                // count++;
                count.getAndIncrement();

                // ++count;
                // count.incrementAndGet();

                // count += n;
                // count.addAndGet(n);
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 50000; i++) {
                // count++;
                count.getAndIncrement();
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        // System.out.println("count: " + count); // count: 59274 理论值是 10_0000
        System.out.println("count: " + count.get()); // count: 100000
    }
}
