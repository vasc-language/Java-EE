package two.test;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-01-14
 * Time: 22:11
 */
class SharedBuffer {
    private int[] buffer = new int[10]; // 缓冲区
    private int count = 0; // 当前缓冲区的数据量

    // 生产者线程
    public synchronized void produce(int value) throws InterruptedException {
        // 缓冲区数据量满了，生产者线程就阻塞等待
        if (count == buffer.length) {
            wait();
        }
        // 向缓冲区添加数据
        buffer[count++] = value;
        System.out.println("produce: " + value);
        // 唤醒消费者线程
        notify();
    }

    // 消费者线程
    public synchronized int consumer() throws InterruptedException {
        // 缓冲区数据量为 0，等待生产者生产
        if (count == 0) {
            wait();
        }
        // 从缓冲区取出数据
        int value = buffer[--count];
        System.out.println("consumer: " + value);
        // 唤醒生产者线程
        notify();
        return value;
    }
}
public class Demo8 {
    public static void main(String[] args) throws InterruptedException {
        SharedBuffer buffer = new SharedBuffer();
        // 生产者-消费者模型，需要两个线程来实现

        // 生产者线程
        Thread producer = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                try {
                    buffer.produce(i);
                    Thread.sleep(1000); // 模拟生产时间
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        // 消费者线程
        Thread consumer = new Thread(() -> {
            for (int i = 0; i < 150; i++) {
                try {
                    buffer.consumer();
                    Thread.sleep(1500); // 模拟消费时间
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();
    }
}
