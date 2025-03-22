/**
 * Created with IntelliJ IDEA.
 * Description: 可重入（一个线程，一把锁，加锁多次）
 * User: 姚东名
 * Date: 2024-11-29
 * Time: 10:30
 */
class Counter {
    private static int count = 0;
    // count++ 操作
    public void add() {
        synchronized (this) {
            count++;
        }
    }
    public int getCount() {
        return count;
    }
}
public class Demo2 {
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();
        // Object locker = new Object();
        Thread t = new Thread(() -> {
            for (int i = 0; i < 50000; i++) {
                synchronized (counter) {
                    synchronized (counter) {
                        synchronized (counter) {
                            counter.add();
                        }
                    }
                }
            }
        });

        t.start();
        t.join();

        // 为什么第二次加锁，不会触发阻塞等待？ 这就是 可重入的概念
        System.out.println("count: " + counter.getCount()); // 50000
    }
}
