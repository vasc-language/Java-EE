import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * Description: 模拟实现一个阻塞队列，并基于阻塞队列写一个生产者消费者模型
 * User: 姚东名
 * Date: 2024-12-29
 * Time: 13:54
 */
class MyBlockingQueue {
    private String[] data = null; // 数组
    private int head; // 队首
    private int tail; // 队尾
    private int size; // 队列中有效个数

    public MyBlockingQueue(int Capacity) {
        this.data = new String[Capacity];
    }

    // 进队列
    public void put(String elem) throws InterruptedException {
        synchronized (this) {
            // 虚假唤醒(spurious wakeup)
            while (size >= data.length) {
                // 队列满，就不允许进队列了
                this.wait();
            }
            data[tail] = elem;
            tail++;
            if (tail >= data.length) {
                tail = 0;
            }
            size++;
            this.notify();
        }
    }

    // 出队列
    public String take() throws InterruptedException {
        synchronized (this) {
            // 虚假唤醒(spurious wakeup)
            while (size == 0) {
                // 队列空，就不允许出队列了
                this.wait();
            }
            String ret = data[head];
            head++;
            if (head >= data.length) {
                head = 0;
            }
            size--;
            this.notify();
            return ret;
        }
    }
}
public class Demo1 {
    public static void main(String[] args) {
        // 需要两个线程，来实现生产者消费者模型
        MyBlockingQueue queue = new MyBlockingQueue(10);
        // 生产者
        Thread producer = new Thread(() -> {
            int n = 0;
            while (true) {
                try {
                    queue.put(n + "");
                    System.out.println("生产元素：" + n);
                    n++;
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Producer");

        // 消费者
        Thread consumer = new Thread(() -> {
            String n = null;
            while (true) {
                try {
                    n = queue.take();
                    System.out.println("消费元素：" + n);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Consumer");

        // 启动线程
        producer.start();
        consumer.start();
    }
}
