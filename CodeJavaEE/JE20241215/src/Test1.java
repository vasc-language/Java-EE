import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * Description: notifyAll 的使用
 * User: 姚东名
 * Date: 2024-12-15
 * Time: 14:00
 */
public class Test1 {
    public static void main(String[] args) {
        Object locker = new Object();
        Thread t1 = new Thread(() -> {
            try {
                System.out.println("t1 线程在wait之前");
                synchronized (locker) {
                    locker.wait();
                }
                System.out.println("t1 线程在wait之后");
            } catch (InterruptedException e){
                throw new RuntimeException();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                System.out.println("t2 线程在wait之前");
                synchronized (locker) {
                    locker.wait();
                }
                System.out.println("t2 线程在wait之后");
            } catch (InterruptedException e){
                throw new RuntimeException();
            }
        });

        // t3线程唤醒t1 t2 线程
        Thread t3 = new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            System.out.println("任意输入，唤醒所有线程");
            scanner.next();
            synchronized (locker) {
                locker.notifyAll();
            }
        });

        t1.start();
        t2.start();
        t3.start();
    }
}
