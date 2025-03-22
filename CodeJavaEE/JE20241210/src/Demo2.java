import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * Description: 使用notify唤醒wait
 * User: 姚东名
 * Date: 2024-12-10
 * Time: 8:26
 */
public class Demo2 {
    public static void main(String[] args) {
        Object locker1 = new Object();
        Object locker2 = new Object();
        // 线程1：wait阻塞等待
        Thread t1 = new Thread(() -> {
            try {
                // Thread.sleep(10_000);
                System.out.println("在wait之前");
                synchronized (locker1) {
                    locker1.wait();
                }
                System.out.println("在wait之后");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        // 线程2：notify唤醒wait
        Thread t2 = new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            System.out.println("任意输入，通知唤醒 t1 线程");
            scanner.next();
            synchronized (locker1) {
                locker1.notify();
            }
        });

        t1.start();
        t2.start();
    }
}
