package MyThread;

/**
 * Created with IntelliJ IDEA.
 * Description: 多线程1
 * User: 姚东名
 * Date: 2024-10-29
 * Time: 12:01
 */
class MyThread extends Thread {
    @Override
    public void run() {
        while (true) {
            System.out.println("hello thread");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }
}
public class Demo1 {
    public static void main(String[] args) throws InterruptedException {
        // 1. 创建一个Thread的子类，在类中重写run方法
        Thread thread = new MyThread();// 向上转型
        thread.start();// 创建一个进程
        //thread.run();

        while (true) {
            System.out.println("hello main");
            Thread.sleep(1000);
        }
    }
}
