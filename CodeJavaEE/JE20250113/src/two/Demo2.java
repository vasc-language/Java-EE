package two;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-01-13
 * Time: 16:37
 */
class MyRunnable implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println(Thread.currentThread().getName() + "-" + i);
        }
    }
}
public class Demo2 {
    public static void main(String[] args) {
        MyRunnable myRunnable = new MyRunnable();
        // 创建线程并启动
        Thread t1 = new Thread(myRunnable);
        Thread t2 = new Thread(myRunnable);

        t1.start();
        t2.start();
    }
}
