import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-01-24
 * Time: 18:43
 */
public class Demo7 {
    private final ReentrantLock locker = new ReentrantLock();
    private final Condition condition = locker.newCondition();
    private boolean ready = false;

    //等待
    public void waitForSignal() throws InterruptedException {
        locker.lock();
        try {
            while (!ready) {
                condition.await(); // 等待信号
            }
            System.out.println("Received signal, proceeding...");
        } finally {
            locker.unlock();
        }
    }

    //唤醒
    public void Signal() {
        locker.lock();
        try {
            ready = true;
            condition.signal(); // 发送信号
        } finally {
            locker.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Demo7 demo7 = new Demo7();
        Thread waiter = new Thread(() -> {
            try {
                demo7.waitForSignal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        //启动线程
        waiter.start();

        Thread.sleep(1000); // 模拟延迟

        demo7.Signal();
    }
}
