package two;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-01-16
 * Time: 18:32
 */
class MyTimerTask implements Comparable<MyTimerTask> {
    // 任务
    private Runnable task;
    // 任务当前执行的时间
    private long time;

    public MyTimerTask(Runnable task, long time) {
        this.task = task;
        this.time = time;
    }

    // 优先级队列判断的逻辑
    @Override
    public int compareTo(MyTimerTask o) {
        return (int) (this.time - o.time);
    }

    public long getTime() {
        return time;
    }

    public void run() {
        task.run(); // Runnable task
    }
}

// 手动实现一个 定时器
class MyTimer {
    // 需要一个优先级队列来装这些任务
}
public class Demo14 {
    public static void main(String[] args) {

    }
}
