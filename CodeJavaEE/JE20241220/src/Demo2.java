import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created with IntelliJ IDEA.
 * Description: 分别在生产者和消费者中加上sleep，看看阻塞效果
 * User: 姚东名
 * Date: 2024-12-28
 * Time: 17:26
 */
public class Demo2 {
    public static void main(String[] args) {
        // 需要两个线程，组成生产者消费者模型
        BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(100);
        Thread producer = new Thread(() -> {
            int n = 0;
            while (true) {
                try {
                    queue.put(n);
                    System.out.println("生产元素：" + n);
                    n++;
                    // Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "producer");

        Thread consumer = new Thread(() -> {
            while (true) {
                try {
                    Integer n = queue.take();
                    System.out.println("消费元素：" + n);

                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "consumer");

        // 启动线程
        producer.start();
        consumer.start();
    }
}
