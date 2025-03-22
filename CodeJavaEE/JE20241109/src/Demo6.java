/**
 * Created with IntelliJ IDEA.
 * Description: 使用Runnable对象创建线程对象，并命名
 * User: 姚东名
 * Date: 2024-11-09
 * Time: 11:22
 */
public class Demo6 {
    public static void main(String[] args) throws InterruptedException {
        // 1. Thread(Runnable target, String name)   使用Runnable对象创建线程对象，并命名
        Thread t1 = new Thread(() -> {
           while (true) {
               System.out.println("hello t1");
               try {
                   Thread.sleep(1000);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
        }, "t1");
        // 2.1 创建一个线程t1
        t1.start();

        Thread t2 = new Thread(() -> {
            while (true) {
                System.out.println("hello t2");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t2");
        // 2.2 创建一个线程t2
        t2.start();

        Thread t3 = new Thread(() -> {
            while (true) {
                System.out.println("hello t3");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t3");
        // 2.3 创建一个线程t3
        t3.start();

        // 3. 主线程
        for (int i = 0; i < 3; i++) {
            System.out.println("hello main");
            Thread.sleep(1000);
        }
    }
}
