import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * Description: 使用notifyAll 一次唤醒所有wait
 * User: 姚东名
 * Date: 2024-12-10
 * Time: 9:15
 */
public class Demo3 {
    public static void main(String[] args) {
        Object locker = new Object();
        Thread t1 = new Thread(() -> {
            try {
                System.out.println("t1 在wait之前");
                synchronized (locker) {
                    locker.wait();
                }
                System.out.println("t1 在wait之后");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                System.out.println("t2 在wait之前");
                synchronized (locker) {
                    locker.wait();
                }
                System.out.println("t2 在wait之后");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // 使用notifyAll 一次唤醒两个线程的wait
        Thread t3 = new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            System.out.println("任意输入，一次唤醒所有线程");
            scanner.next();

            synchronized (locker) {
                locker.notifyAll();
            }
        });

        // 启动线程
        t1.start();
        t2.start();
        t3.start();
    }
}
