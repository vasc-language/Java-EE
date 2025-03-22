/**
 * Created with IntelliJ IDEA.
 * Description: 继承 Thread 来创建⼀个线程类.
 * User: 姚东名
 * Date: 2024-11-09
 * Time: 10:09
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
            }
        }
    }
}
public class Demo1 {
    public static void main(String[] args) throws InterruptedException {
        // 1. 创建Thread类的子类，在子类中重写run方法
        Thread t = new MyThread();
        // 2. 创建一个线程
        t.start();
        while (true) {
            System.out.println("hello main");
            Thread.sleep(1000);
        }
    }
}
