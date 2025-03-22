package two;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created with IntelliJ IDEA.
 * Description: 模拟实现一个线程池
 * User: 姚东名
 * Date: 2025-01-16
 * Time: 8:50
 */
// 实现一个固定线程个数的线程池
class MyThreadPool {
    //阻塞队列
    private ArrayBlockingQueue<Runnable> queue = null;

    public MyThreadPool(int n) {
        // 初始化线程，固定数目的线程数
        // 以 ArrayBlockingQueue 作为任务队列，固定容量为 1000
        queue = new ArrayBlockingQueue<>(1000);

        // 创建 N 个线程
        for (int i = 0; i < n; i++) {
            Thread t = new Thread(() -> {
                try {
                    while (true) {
                        Runnable task = queue.take();
                        task.run();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            t.start();
        }
    }
    //提交任务
    public void submit(Runnable task) throws InterruptedException {
        // 把任务丢到队列中
        queue.put(task);
    }

}
public class Demo11 {
    public static void main(String[] args) throws InterruptedException {
        MyThreadPool threadPool = new MyThreadPool(10);
        for (int i = 0; i < 100; i++) {
            int id = i;
            threadPool.submit(() -> {
                System.out.println(Thread.currentThread().getName() + " id=" + id);
            });
        }
    }
}
