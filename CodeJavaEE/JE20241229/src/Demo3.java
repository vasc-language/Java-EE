import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2024-12-29
 * Time: 17:56
 */
public class Demo3 {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 1000; i++) {
            int id = i;
            threadPool.submit(() -> {
                System.out.println("hello " + id + ", " + Thread.currentThread());
            });
        }
    }
}
