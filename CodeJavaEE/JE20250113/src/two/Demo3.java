package two;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-01-13
 * Time: 16:49
 */
class SleepThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            try {
                // 休眠 1 秒
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + "-" + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
public class Demo3 {
    public static void main(String[] args) {
        SleepThread sleepThread = new SleepThread();
        sleepThread.start();
    }
}
