/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2024-11-25
 * Time: 20:08
 */
public class Demo3 {
    private static int count = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            Thread cur = Thread.currentThread(); // Thread[Thread-0,5,main]
            for (int i = 0; i < 50000; i++) {
                synchronized (cur) {
                    count++;
                }
            }
            System.out.println("t1 线程结束");
        });
        Thread t2 = new Thread(() -> {
            Thread cur = Thread.currentThread(); // Thread[Thread-1,5,main]
            for (int i = 0; i < 50000; i++) {
                synchronized (cur) {
                    count++;
                }
            }
            System.out.println("t2 线程结束");
        });

        // 证明二者的锁对象不同，结果就产生线程安全问题
        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("count：" + count);
    }
}
