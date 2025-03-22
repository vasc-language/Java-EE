import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created with IntelliJ IDEA.
 * Description: CAS 中的 ABA 问题
 * User: 姚东名
 * Date: 2025-01-20
 * Time: 11:24
 */
public class Demo1 {
    // 原子类AtomicInteger  value 初始值为 0
    private static AtomicInteger value = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        // 线程 A 尝试将 value 自增 1
        Thread t1 = new Thread(() -> {
            int expectValue = value.get();

            while (!value.compareAndSet(expectValue, expectValue + 1)) {
                expectValue = value.get();
            }

            System.out.println("Thread A: Value updated to " + value.get());
        });

        // 线程 B 先将 value 设为 1 ，然后又改为 0
        Thread t2 = new Thread(() -> {
            value.set(1);
            value.set(0);
            System.out.println("Thread B: Value set to 1 then back to 0");
        });

        t1.start();
        //先让 A 线程先跑起来
        Thread.sleep(1000);
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Final Value: " + value.get());
    }
}
