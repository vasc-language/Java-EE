import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-01-20
 * Time: 22:18
 */
public class Demo2 {
    public static void main(String[] args) {
        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return null;
            }
        };

        FutureTask<Integer> futureTask = new FutureTask<>(callable);
    }
}
