import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * Description: wait 提供了一个“超时版本”
 * User: 姚东名
 * Date: 2024-12-10
 * Time: 9:46
 */
public class Demo4 {
    public static void main(String[] args) {
        Object locker = new Object();
        Thread t1 = new Thread(() -> {
            try {
                System.out.println("t1 在wait之前");
                synchronized (locker) {
                    locker.wait(10_000);
                }
                System.out.println("t1 在wait之后");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            System.out.println("任意输入，唤醒t1");
            scanner.next();
            synchronized (locker) {
                locker.notify();
            }
        });

        //启动线程
        t1.start();
        t2.start();
    }
}
