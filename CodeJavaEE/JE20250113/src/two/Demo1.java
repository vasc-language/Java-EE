package two;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-01-13
 * Time: 15:34
 */
class MyThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println(Thread.currentThread().getName() + " - " + i);
        }
    }
}
public class Demo1 {
    public static void main(String[] args) {
        // 创建并启动线程
        Thread t1 = new MyThread();
        Thread t2 = new MyThread();

        t1.start();
        t2.start();
    }
}
