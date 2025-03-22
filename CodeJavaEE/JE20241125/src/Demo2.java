/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2024-11-25
 * Time: 18:58
 */
public class Demo2 {
    private static int count = 0;

    public static void main(String[] args) throws InterruptedException {
        Object locker1 = new Object();
        // Object locker2 = new Object();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 50000; i++) {
                synchronized (locker1) {
                    count++;
                }
            }
            System.out.println("t1 线程结束");
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 50000; i++) {
                synchronized (locker1) {
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
