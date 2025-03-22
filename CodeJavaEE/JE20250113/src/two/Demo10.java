package two;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-01-15
 * Time: 22:56
 */
public class Demo10 {
    private static final int QUEUE_CAPACITY = 5; // 阻塞队列中的容纳的量
    private static final ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(QUEUE_CAPACITY ); // 阻塞队列

    public static void main(String[] args) {
        Thread producer = new Thread(new producer());
        Thread consumer = new Thread(new consumer());
        // 启动线程
        producer.start();
        consumer.start();
    }

    // 生产者线程
    static class producer implements Runnable {
        @Override
        public void run() {
            int value = 0; // 数据量
            try {
                while (true) {
                    System.out.println("生产者准备生产数据~");
                    queue.put(value);
                    System.out.println("生产者生产数据：" + value);
                    value++;
                    Thread.sleep(1000); // 模拟生产过程中的耗时操作
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    // 消费者线程
    static class consumer implements Runnable {
        @Override
        public void run() {
            try {
                while (true) {
                    System.out.println("消费者准备消费数据~");
                    Integer value = queue.take();
                    System.out.println("消费者消费数据：" + value);
                    Thread.sleep(1500); // 模拟消费过程中耗时操作
                }
            } catch (InterruptedException e) {

            }
        }
    }
}
