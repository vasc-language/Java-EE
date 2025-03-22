/**
 * Created with IntelliJ IDEA.
 * Description: 其他变形：匿名内部类创建Thread子类对象
 * User: 姚东名
 * Date: 2024-11-09
 * Time: 10:23
 */
public class Demo3 {
    public static void main(String[] args) throws InterruptedException {
        // 1. 匿名内部类创建Thread子类对象
        Thread t = new Thread() {
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
        };
        // 2. 创建一个线程
        t.start();

        while (true) {
            System.out.println("hello main");
            Thread.sleep(1000);
        }
    }
}
