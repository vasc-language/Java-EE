import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-01-24
 * Time: 21:54
 */
public class Demo8 {
    private final ReentrantLock locker = new ReentrantLock();

    // 该方法尝试在 5 秒内获取锁，并在获取锁后模拟长时间持有锁的操作
    public void tryLockWithInterrupt() throws InterruptedException {
        if (locker.tryLock(5, TimeUnit.SECONDS)) {
            try {
                System.out.println(Thread.currentThread().getName() + " acquired the lock.");
                Thread.sleep(2000);
            } finally {
                locker.unlock();
            }
        } else {
            System.out.println(Thread.currentThread().getName() + " failed to acquired the lock.");
        }
    }
    public static void main(String[] args) throws InterruptedException {
        Demo8 demo8 = new Demo8();
        // 创建两个线程，都尝试获取锁
        Thread t1 = new Thread(() -> {
            try {
                demo8.tryLockWithInterrupt();
                System.out.println(Thread.currentThread().getName() + " was interrupted.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                demo8.tryLockWithInterrupt();
                System.out.println(Thread.currentThread().getName() + " was interrupted.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // t1 线程先启动，尝试获取锁
        t1.start();

        Thread.sleep(1000);

        // t2 线程启动线程后立即被打断，并抛出 InterruptedException 异常，打印异常信息
        t2.start();
        t2.interrupt();

    }
}
