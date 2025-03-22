import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created with IntelliJ IDEA.
 * Description: Executor 线程池
 * User: 姚东名
 * Date: 2025-01-04
 * Time: 18:13
 */
public class Demo1 {
    public static void main(String[] args) {
        // Executor创建出固定线程数的线程池 或 自动扩容线程数的线程池
        // ExecutorService threadPool1 = Executors.newFixedThreadPool(10);
        ExecutorService threadPool1 = Executors.newCachedThreadPool(); // 自动扩容线程数的线程池
        for (int i = 0; i < 50; i++) {
            int id = i;
            threadPool1.submit(() -> {
                System.out.println("hello " + id + ", " + Thread.currentThread().getName());
            });
        }

        // shutdown能够将线程池的任务全部关闭，但是不能保证线程池中的任务全部执行完毕
        // 如果需要等待线程池的任务全部执行完毕，可调用 awaitTermination 方法
        threadPool1.shutdown();
    }
}
