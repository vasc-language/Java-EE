/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2024-11-25
 * Time: 19:34
 */
public class Test2 {
    private static int count = 0;

    public static void main(String[] args) throws InterruptedException {
        Object locker = new Object();
        Thread t1 = new Thread(() -> {
            synchronized (locker) {
                for (int i = 0; i < 50000; i++) {
                    count++;
                }
            }
            System.out.println("t1 线程结束");
        });
        Thread t2 = new Thread(() -> {
            synchronized (locker) {
                for (int i = 0; i < 50000; i++) {
                    count++;
                }
            }
            System.out.println("t2 线程结束");
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("count：" + count);
    }
}
