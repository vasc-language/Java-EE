import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-01-23
 * Time: 17:15
 */
public class Demo3 {
    public static void main(String[] args) {
        Callable<Integer> callable = () -> {
            int result = 0;
            for (int i = 0; i < 10; i++) {
                result += i;
            }
            return result;
        };

        FutureTask<Integer> futureTask = new FutureTask<>(callable);

        Thread t = new Thread(futureTask);
        t.start();

        try {
            Integer result = futureTask.get();
            System.out.println("result: " + result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
