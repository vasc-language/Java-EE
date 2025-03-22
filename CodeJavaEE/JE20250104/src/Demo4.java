import java.util.PriorityQueue;

/**
 * Created with IntelliJ IDEA.
 * Description: 手动模拟实现一个简单的定时器
 * User: 姚东名
 * Date: 2025-01-04
 * Time: 20:59
 */

// 写一个TimerTask
class MyTimerTask implements Comparable<MyTimerTask> {
    private Runnable task; // 队列中要执行的任务
    private long time; // 记录任务要执行的时刻

    public MyTimerTask(Runnable task, long time) {
        this.task = task;
        this.time = time;
    }

    //优先级队列要比较的顺序
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

// 写一个定时器
class MyTimer {
    // 1. 需要一个数据结构来管理队列中的任务（优先级队列）
    private PriorityQueue<MyTimerTask> queue = new PriorityQueue<>();
    // 2. 多个线程操作一个队列，势必会存在线程安全问题（当前调用 schedule 把任务添加到队列中，还有一个线程执行队列中的任务）
    private Object locker = new Object();

    // 3. 实现一个 schedule 方法把任务添加到队列中
    public void schedule(Runnable task, long delay) {
        synchronized (locker) {
            // 以入队列这个时刻作为时间基准
            MyTimerTask myTimerTask = new MyTimerTask(task, System.currentTimeMillis() + delay); // 计算出任务的执行时间（系统时间+延迟时间）
            queue.offer(myTimerTask);
            // 在执行 schedule 方法时唤醒 wait
            locker.notify();
        }
    }

    // 4. 构造方法：创建一个线程，来执行队列中的任务
    public MyTimer() {
        Thread t = new Thread(() -> {
            try {
                while (true) {
                    synchronized (locker) {
                        // 取出栈顶元素
                        while (queue.isEmpty()) {
                            // continue;
                            locker.wait(); // 与 while 搭配在一起使用
                        }
                        MyTimerTask task = queue.peek();
                        if (System.currentTimeMillis() < task.getTime()) {
                            // 还没到时间
                            // continue;
                            locker.wait(task.getTime() - System.currentTimeMillis()); // 解决忙等问题
                        } else {
                            task.run(); // 执行队列中的任务
                            queue.poll();
                        }
                    }
                }
            } catch (InterruptedException e) {
                throw new RuntimeException();
            }
        });
        // 启动线程
        t.start();
    }
}

public class Demo4 {
    public static void main(String[] args) {
        MyTimer myTimer = new MyTimer();
        myTimer.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("hello 3000");
            }
        }, 3000);

        myTimer.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("hello 2000");
            }
        }, 2000);

        myTimer.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("hello 1000");
            }
        }, 1000);
    }
}
