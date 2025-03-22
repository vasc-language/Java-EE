import java.util.concurrent.CountDownLatch;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-01-25
 * Time: 21:57
 */

class Worker implements Runnable {
    private final CountDownLatch latch;
    private final int taskId;

    public Worker(CountDownLatch latch, int taskId) {
        this.latch = latch;
        this.taskId = taskId;
    }

    @Override
    public void run() {
        try {
            System.out.println("Task " + taskId + " is running. ");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            latch.countDown(); // 完成一个子任务就操作计数器减一
            System.out.println("Task " + taskId + " is completed. ");
        }
    }
}
public class Demo10 {
    public static void main(String[] args) {
        int numberOfTasks = 5;
        CountDownLatch latch = new CountDownLatch(numberOfTasks);

        for (int i = 0; i < numberOfTasks; i++) {
            new Thread(new Worker(latch, i)).start(); // 启动线程
        }

        try {
            latch.await(); // 主线程等待所有子任务完成
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("All tasks completed.");
    }
}
