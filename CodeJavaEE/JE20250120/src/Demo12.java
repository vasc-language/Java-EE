import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description: HashMap
 * User: 姚东名
 * Date: 2025-01-26
 * Time: 13:29
 */
public class Demo12 {
    private static final Map<String, String> map = new HashMap<>();

    public static void main(String[] args) throws InterruptedException {
        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                map.put(Thread.currentThread().getName() + "-" + i, "value" + i);
            }
        };

        Thread t1 = new Thread(task, "t1");
        Thread t2 = new Thread(task, "t2");

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Final size: " + map.size()); // 理论上是 2000 但是实际上比 2000 小
    }
}
