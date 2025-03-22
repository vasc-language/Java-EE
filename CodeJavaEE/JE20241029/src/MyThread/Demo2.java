package MyThread;

/**
 * Created with IntelliJ IDEA.
 * Description: 创建进程方法2
 * User: 姚东名
 * Date: 2024-10-29
 * Time: 12:11
 */
class MyRunnable implements Runnable {
    @Override
    public void run() {
        while (true) {
            System.out.println("hello thread1");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }
}
public class Demo2 {
    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = new MyRunnable();// 向上转型
        Thread thread = new Thread(runnable);
        thread.start();
        while (true) {
            System.out.println("hello main");
            Thread.sleep(1000);
        }
    }
}
