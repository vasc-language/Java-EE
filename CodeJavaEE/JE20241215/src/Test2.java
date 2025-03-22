/**
 * Created with IntelliJ IDEA.
 * Description: 作业题，依次打印ABC
 * User: 姚东名
 * Date: 2024-12-15
 * Time: 14:09
 */

/**
 * 有三个线程，分别只能打印A，B，C
 * 要求按照顺序打印ABC，打印10次
 * 输出实例：
 * ABC
 * ABC
 * ABC
 */
public class Test2 {
    public static void main(String[] args) throws InterruptedException {
        Object locker1 = new Object();
        Object locker2 = new Object();
        Object locker3 = new Object();
        // t1 线程
        Thread t1 = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    synchronized (locker1) {
                        locker1.wait();
                    }
                    System.out.print("A");
                    // 在打印完A之后，接着唤醒t2线程，打印B
                    synchronized (locker2) {
                        locker2.notify();
                    }
                }
            } catch (InterruptedException e) {
                throw new RuntimeException();
            }
        });

        // t2 线程
        Thread t2 = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    synchronized (locker2) {
                        locker2.wait();
                    }
                    System.out.print("B");
                    // 在打印完B之后，接着唤醒t3线程，打印C
                    synchronized (locker3) {
                        locker3.notify();
                    }
                }
            } catch (InterruptedException e) {
                throw new RuntimeException();
            }
        });

        // t3 线程
        // t2 线程
        Thread t3 = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    synchronized (locker3) {
                        locker3.wait();
                    }
                    System.out.println("C");
                    // 在打印完C之后，接着唤醒t1线程，打印A 形成循环，直到打印完10次
                    synchronized (locker1) {
                        locker1.notify();
                    }
                }
            } catch (InterruptedException e) {
                throw new RuntimeException();
            }
        });

        // 启动线程
        t1.start();
        t2.start();
        t3.start();

        // 为了保证先wait 后才notify
        Thread.sleep(1000);
        // 主线程中, 先通知一次 locker1, 让上述逻辑从 t1 开始执行
        synchronized (locker1) {
            locker1.notify();
        }
    }
}
