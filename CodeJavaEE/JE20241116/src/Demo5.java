/**
 * Created with IntelliJ IDEA.
 * Description: 观察线程不安全
 * User: 姚东名
 * Date: 2024-11-16
 * Time: 18:03
 */
public class Demo5 {
    private static int count = 0;
    public static void main(String[] args) throws InterruptedException {
        // 对 count 变量进⾏⾃增 5w 次
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 50000; i++) {
                count++;
            }
        });

        // 对 count 变量进⾏⾃增 5w 次
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 50000; i++) {
                count++;
            }
        });

        t1.start();
        t2.start();

        // 没有join 一定是不行的，还没有自增完，就开始打印
        t1.join();
        t2.join();

        // 理论上应该是10w
        System.out.println("count：" + count); // count：75491
    }
}
