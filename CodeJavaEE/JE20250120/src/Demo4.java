import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-01-23
 * Time: 17:27
 */
public class Demo4 {
    public static void main(String[] args) {
        Callable<String> callable = () -> {
            if (Math.random() > 0.5) {
                throw new Exception("Random error");
            }
            return "Success";
        };

        FutureTask<String> futureTask = new FutureTask<>(callable);

        Thread t = new Thread(futureTask);
        t.start();

        try {
            String result = futureTask.get();
            System.out.println("result: " + result);
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Exception caught: " + e.getCause());
        }
    }
}
