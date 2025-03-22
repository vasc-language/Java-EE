/**
 * Created with IntelliJ IDEA.
 * Description: wait 提供了一个“超时版本”
 * User: 姚东名
 * Date: 2024-12-10
 * Time: 9:57
 */
public class Test1 {
    public static void main(String[] args) {
        Object locker = new Object();
        Thread t1 = new Thread(() -> {
            synchronized (locker) {
                try {
                    System.out.println("在t1 wait之前");
                    locker.wait(10_000);
                    System.out.println("在t1 wait之后");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        // 启动线程
        t1.start();
    }
}
