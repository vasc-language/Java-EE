import java.util.Timer;
import java.util.TimerTask;

/**
 * Created with IntelliJ IDEA.
 * Description: 定时器
 * User: 姚东名
 * Date: 2025-01-04
 * Time: 20:09
 */
public class Demo3 {
    /**
     * Timer()：创建一个新的定时器，其相关线程不作为守护线程运行。(作为前台线程运行)
     * schedule(TimerTask task, long delay)：安排一个 TimerTask ，在指定延长时间后执行
     * TimerTask()：一个由Timer安排的任务。作为一个抽象类，继承它并实现 run() 方法用来操作要执行的任务
     * @param args
     */
    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("hello 3000");
            }
        }, 3000);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("hello 2000");
            }
        }, 2000);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("hello 1000");
            }
        }, 1000);

        System.out.println("hello main");
    }
}
