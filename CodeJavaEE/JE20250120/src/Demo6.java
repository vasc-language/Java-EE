import java.util.concurrent.locks.ReentrantLock;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-01-24
 * Time: 18:20
 */
public class Demo6 {
    private final ReentrantLock locker = new ReentrantLock();

    public void nestedLock() {
        locker.lock();
        try {
            System.out.println("First lock acquired.");
            locker.lock(); // 同一个线程再次获取同一把锁
            try {
                System.out.println("Second lock acquired.");
            } finally {
                locker.unlock(); // 释放第二次获取的锁
            }
        } finally {
            locker.unlock(); // 释放第一次获取的锁
        }
    }
    public static void main(String[] args) {
        Demo6 demo6 = new Demo6();
        demo6.nestedLock();
    }
}
