/**
 * Created with IntelliJ IDEA.
 * Description: 变形：lambda表达式创建Runnable对象
 * User: 姚东名
 * Date: 2024-11-09
 * Time: 10:35
 */
public class Demo4 {
    public static void main(String[] args) throws InterruptedException {
        // 1. lambda表达式创建Runnable对象
        Thread t = new Thread(() -> {
            while (true) {
                System.out.println("hello thread");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        // 2. 创建一个线程
        t.start();
        // 3. 主线程
        while (true) {
            System.out.println("hello main");
            Thread.sleep(1000);
        }
    }
}
