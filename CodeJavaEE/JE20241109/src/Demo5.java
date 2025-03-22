/**
 * Created with IntelliJ IDEA.
 * Description: 变形：匿名内部类创建Runnable子类对象
 * User: 姚东名
 * Date: 2024-11-09
 * Time: 10:42
 */
public class Demo5 {
    public static void main(String[] args) throws InterruptedException {
        // 1. 匿名内部类创建Runnable子类对象
        Runnable runnable = new Runnable() {
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
        };
        // 2. 把runnable对象放到Thread中
        Thread t = new Thread(runnable);
        // 3. 创建一个线程
        t.start();

        // 4. 主线程
        while (true) {
            System.out.println("hello main");
            Thread.sleep(1000);
        }
    }
}
