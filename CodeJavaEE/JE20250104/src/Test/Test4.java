package Test;

import java.util.PriorityQueue;
import java.util.concurrent.Executors;

/**
 * Created with IntelliJ IDEA.
 * Description: 重新写一遍定时器
 * User: 姚东名
 * Date: 2025-01-05
 * Time: 16:58
 */
//创建一个类，表示一个任务
class MyTimerTask implements Comparable<MyTimerTask> {
    // 队列中要执行的任务
    private Runnable task;
    // 记录要执行任务的时刻
    private long time;

    public MyTimerTask(Runnable task, long time) {
        this.task = task;
        this.time = time;
    }

    // 优先级队列的排序规则
    @Override
    public int compareTo(MyTimerTask o) {
        return (int) (this.time - o.time);
    }

    // 获取添加任务的当前时间 和 执行队列中的任务方法
    public long getTime() {
        return time;
    }
    public void run() {
        task.run(); // Runnable task
    }
}

// 手动模拟一个定时器
class MyTimer {
    // 1. 需要一个数据结构来管理队列中的任务(优先级队列)
    private PriorityQueue<MyTimerTask> queue = new PriorityQueue<>();
    // 2. 需要一把锁来解决多线程安全问题
    private Object locker = new Object();

    // 3. 写一个方法，把任务添加到队列中
    public void schedule(Runnable task, long delay) {
        synchronized (locker) {
            MyTimerTask myTimerTask = new MyTimerTask(task, System.currentTimeMillis() + delay);
            // 添加任务
            queue.offer(myTimerTask);
            // 解决忙等问题(循环地检查条件)
            locker.notify();
        }
    }

    // 4. 构造方法：额外地创建一个线程，执行队列中的任务
    public MyTimer() {
        Thread t = new Thread(() -> {
            try {
                while (true) {
                    synchronized (locker) {
                        // 队列为空时，线程阻塞等待
                        while (queue.isEmpty()) {
                            locker.wait();
                        }
                        MyTimerTask task = queue.peek(); // 执行任务
                        if (System.currentTimeMillis() < task.getTime()) {
                            // 还没到任务执行时间，就阻塞等待到要执行任务的时间(及时性)
                            locker.wait(task.getTime() - System.currentTimeMillis());
                        } else {
                            // 时间到就执行任务
                            task.run();
                            queue.poll();
                        }
                    }
                }
            } catch (InterruptedException e) {
                throw new RuntimeException();
            }
        });
        // 启动执行任务线程
        t.start();
    }
}

public class Test4 {
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

        System.out.println("hello main");
        // 定时执行任务
        // Executors.newScheduledThreadPool(4);
    }
}
