package two;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-01-13
 * Time: 17:10
 */

class InterruptThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            if (Thread.interrupted()) {
                System.out.println(Thread.currentThread().getName() + "was interrupt");
                break;
            }
            System.out.println(Thread.currentThread().getName() + "-" + i);
        }
    }
}
public class Demo4 {
    public static void main(String[] args) throws InterruptedException {
        InterruptThread interruptThread = new InterruptThread();
        interruptThread.start();

        Thread.sleep(1000);
        // 中断线程
        interruptThread.interrupt();
    }
}
