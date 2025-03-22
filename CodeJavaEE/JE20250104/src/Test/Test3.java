package Test;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * Description: 定时器正常写法
 * User: 姚东名
 * Date: 2025-01-04
 * Time: 20:27
 */
public class Test3 {
    // 定时执行任务(延迟执行一个任务)
    public static void main(String[] args) {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(4);
        Runnable task = () -> {
            System.out.println("hello executor");
        };
        // 延迟两秒钟 执行任务
        executor.schedule(task, 2, TimeUnit.SECONDS);
        // 关闭线程池(也会关闭未完成任务的线程)
        executor.shutdown();

        System.out.println("hello main");
    }
    public static void main1(String[] args) {
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("hello 3000");
            }
        };
        timer.schedule(timerTask, 3000);
    }
}
