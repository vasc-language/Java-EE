import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-01-23
 * Time: 17:51
 */
public class Demo5 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // 我们创建了一个固定大小为 4 的线程池。
        ExecutorService executor = Executors.newFixedThreadPool(4);

        // 创建了 10 个 Callable 任务，每个任务计算一个简单的整数结果。
        List<Callable<Integer>> tasks = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            final int taskId = i;
            tasks.add(() -> {
                int result = taskId * 10;
                System.out.println("Task " + taskId + " completed with result: " + result);
                return result;
            });
        }

        // 把任务提交到线程池中使用并等待完成
        List<Future<Integer>> futures = executor.invokeAll(tasks);

        int total = 0;

        // 遍历 futures 列表，通过 future.get() 获取每个任务的结果，并计算总和。
        for (Future<Integer> future : futures) {
            total += future.get();
        }

        System.out.println("Total result: " + total);
        executor.shutdown();
    }
}
