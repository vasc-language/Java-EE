/**
 * Created with IntelliJ IDEA.
 * Description: t.join() 方法简单介绍
 * User: 姚东名
 * Date: 2024-11-16
 * Time: 16:09
 */
public class Demo2 {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            for (int i = 0; i < 3000; i++) {
                System.out.println("hello thread");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("t 线程结束");
        });

        t.start();

        // 先等t 线程结束后，主线程 main才能结束
        // t.join(); // 这个方法也会抛异常

        t.join(3000, 500);
        System.out.println("main 线程结束");
    }
}
