/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2024-11-25
 * Time: 10:20
 */
public class Demo1 {
    public static void main(String[] args) throws InterruptedException {
        Thread mainThread = Thread.currentThread(); // Thread[main,5,main]
        Thread t1 = new Thread(() -> {
            try {
                System.out.println("开始等待 main");
                // mainThread 插队（等待主线程结束 再执行 t1线程）
                mainThread.join();
                System.out.println("结束等待 main");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        // 启动线程
        t1.start();

        Thread.sleep(3 * 1000);
        System.out.println("main 线程结束");
    }
}
