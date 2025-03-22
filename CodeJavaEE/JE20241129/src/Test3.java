/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2024-11-29
 * Time: 11:33
 */
public class Test3 {
    public static void main(String[] args) throws InterruptedException {
        Object locker1 = new Object();
        Object locker2 = new Object();

        Thread t1 = new Thread(() -> {
            synchronized (locker1) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (locker2) {
                    System.out.println("t1 两个线程都获取到");
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (locker2) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (locker1) {
                    System.out.println("t2 两个线程都获取到");
                }
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }
}
