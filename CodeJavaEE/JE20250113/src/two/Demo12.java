package two;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created with IntelliJ IDEA.
 * Description: Timer 是普通的定时器类，用于在指定时间安排任务
 * User: 姚东名
 * Date: 2025-01-16
 * Time: 17:50
 */
public class Demo12 {
    public static void main(String[] args) {
        Timer timer = new Timer();

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("任务执行时间：" + System.currentTimeMillis());
            }
        };

        // 延迟 1 秒后执行，每 3 秒执行一次
        timer.scheduleAtFixedRate(task, 1000, 3000);
    }
}
