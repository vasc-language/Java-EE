/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2024-11-25
 * Time: 10:33
 */
public class Test1 {
    public static int count = 0;
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 50000; i++) {
                count++;
            }
            System.out.println("t1 线程结束");
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 50000; i++) {
                count++;
            }
            System.out.println("t2 线程结束");
        });

        // 启动线程
        t1.start();
        t2.start();

        t1.join();
        t2.join();

        // 一个线程自增5w，两个线程总共自增10w，理论上count等于10w
        System.out.println("count：" + count); // count：61865
    }
}
