/**
 * Created with IntelliJ IDEA.
 * Description: 实现 Runnable 接⼝
 * User: 姚东名
 * Date: 2024-11-09
 * Time: 10:16
 */
class MyRunnable implements Runnable {
    @Override
    public void run() {
        while (true) {
            System.out.println("hello runnable");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
public class Demo2 {
    public static void main(String[] args) throws InterruptedException {
        // 1. 创建一个Runnable的子类，实现Runnable接口
        Runnable runnable = new MyRunnable();
        Thread t = new Thread(runnable);
        // 2. 创建一个线程
        t.start();
        while (true) {
            System.out.println("hello main");
            Thread.sleep(1000);
        }
    }
}
