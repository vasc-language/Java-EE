import java.util.concurrent.Semaphore;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-01-24
 * Time: 23:17
 */
public class Demo9 {
    private static final Semaphore semaphore = new Semaphore(2); // 允许最多 2 2个线程同时进行访问

    public static void main(String[] args) {
        Runnable task = () -> {
          try {
              try {
                  semaphore.acquire(); // 获取许可
                  System.out.println(Thread.currentThread().getName() + " acquired the permits.");
                  Thread.sleep(1000); // 模拟长时间占用资源
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
          }  finally {
              semaphore.release(); // 释放许可
              System.out.println(Thread.currentThread().getName() + " released the permits.");
          }
        };

        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(task, "Thread-" + i);
            thread.start();
        }
    }
}
