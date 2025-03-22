/**
 * Created with IntelliJ IDEA.
 * Description: setDaemon：将前台线程改为后台线程
 * User: 姚东名
 * Date: 2024-11-09
 * Time: 12:08
 */
public class Demo7 {
    public static void main(String[] args) throws InterruptedException {
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
        // 这样的设置在start()之前
        t.setDaemon(true);
        t.start();

        //主线程：
        for (int i = 0; i < 3; i++) {
            System.out.println("hello main");
            Thread.sleep(1000);
        }

        System.out.println("main线程结束！");
    }
}
