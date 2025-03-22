/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2024-11-16
 * Time: 18:41
 */
public class Test {
    private static int count = 0;
    public static void main(String[] args) throws InterruptedException {
        //申请一把锁：
        Object locker = new Object();
        // 线程1：
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 50000; i++) {
                synchronized (locker) {
                    count++;
                }
            }
            System.out.println("t1线程结束");
        });

        // 线程2：
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 50000; i++) {
                synchronized (locker) {
                    count++;
                }
            }
            System.out.println("t2线程结束");
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        // 理论上应该是10w
        System.out.println("count：" + count); //count：100000
    }
}
