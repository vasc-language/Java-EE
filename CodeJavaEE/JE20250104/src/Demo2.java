import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created with IntelliJ IDEA.
 * Description: 实现一个有固定线程数的线程池
 * User: 姚东名
 * Date: 2025-01-04
 * Time: 18:31
 */
class MyThreadPool {
    // 首先得需要一个任务队列(阻塞队列)
    private BlockingQueue<Runnable> queue = null;

    public MyThreadPool(int n) {
        // 初始化线程 固定个数的线程
        this.queue = new ArrayBlockingQueue<>(1000); //任务队列的固定容量为1000

        // 创造 N 个线程
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
            //启动线程
            t.start();
        }
    }
    // 任务提交
    public void submit(Runnable task) throws InterruptedException {
        queue.put(task);
    }
}
public class Demo2 {
    public static void main(String[] args) throws InterruptedException {
        MyThreadPool threadPool = new MyThreadPool(100); // 这个是线程的个数
        // 线程池中提交的任务数是1000
        for (int i = 0; i < 1000; i++) {
            int id = i;
            threadPool.submit(() -> {
                System.out.println(Thread.currentThread().getName() + " id=" + id);
            });
        }
    }
}
