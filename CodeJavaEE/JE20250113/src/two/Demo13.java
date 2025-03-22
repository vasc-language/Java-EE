package two;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * Description: ScheduledExecutorService 是有更加灵活和线程安全的定时任务功能
 * User: 姚东名
 * Date: 2025-01-16
 * Time: 18:12
 */
public class Demo13 {
    public static void main(String[] args) {
        ScheduledExecutorService schedule = Executors.newScheduledThreadPool(2);

        Runnable task = () -> {
            System.out.println("任务执行，时间：" + System.currentTimeMillis());
        };
        // 延后 1 秒执行任务，每 3 秒执行一次
        schedule.scheduleAtFixedRate(task, 1, 3, TimeUnit.SECONDS);
    }
}
