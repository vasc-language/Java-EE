/**
 * Created with IntelliJ IDEA.
 * Description: 两个线程，两把锁，每个线程获取到一把锁后（不解锁），继续尝试获取对方的锁
 * User: 姚东名
 * Date: 2024-11-29
 * Time: 11:25
 */
public class Demo3 {
    private static int count = 0;
    public static void main(String[] args) throws InterruptedException {
        Object locker1 = new Object();
        Object locker2 = new Object();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 50_000; i++) {
                synchronized (locker1) {
                    synchronized (locker2) {
                        count++;
                    }
                }
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 50_000; i++) {
                synchronized (locker2) {
                    synchronized (locker1) {
                        count++;
                    }
                }
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("count:" + count);
    }
}
