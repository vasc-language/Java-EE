/**
 * Created with IntelliJ IDEA.
 * Description: volatile 是解决“内存可见性问题”，不是解决原子性问题
 * User: 姚东名
 * Date: 2024-11-30
 * Time: 22:13
 */
public class Demo5 {
    public volatile static int count = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 50_000; i++) {
                count++;
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 50_000; i++) {
                count++;
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("count: " + count); // count: 72798 随机数
    }
}
