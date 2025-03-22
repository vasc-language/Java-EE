/**
 * Created with IntelliJ IDEA.
 * Description: 简单模拟实现一个阻塞队列， 并基于这个阻塞队列实现生产者消费者模型
 * User: 姚东名
 * Date: 2024-12-28
 * Time: 18:15
 */
// 此处不使用泛型了. String
class MyBlockingQueue {
    private String[] data = null; // 数组
    private int head = 0; //队首
    private int tail = 0; // 队尾
    private int size = 0; // 数组中有效个数

    public MyBlockingQueue(int Capacity) {
        data = new String[Capacity];
    }

    // 插入元素
    public void put(String elem) throws InterruptedException {
        synchronized (this) {
            // 队满时 进行阻塞等待
            while (size >= data.length) {
                this.wait();
            }
            data[tail] = elem;
            tail++;
            if (tail >= data.length) {
                tail = 0;
            }
            // tail = (tail + 1) % data.length;
            size++;
            this.notify();
        }
    }

    // 删除元素
    public String take() throws InterruptedException {
        synchronized (this) {
            // 队空时阻塞等待
            while (size == 0) {
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

public class Demo3 {
    public static void main(String[] args) {
        MyBlockingQueue queue = new MyBlockingQueue(10);
        // 需要两个线程 组成生产者消费者模型
        Thread Producer = new Thread(() -> {
            int n = 0;
            while (true) {
                try {
                    queue.put(n+"");
                    System.out.println("生产元素：" + n);
                    n++;
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "Producer");

        Thread Consumer = new Thread(() -> {
            String n = null;
            while (true) {
                try {
                    n = queue.take();
                    System.out.println("消费元素：" + n);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "Consumer");

        // 启动线程
        Producer.start();
        Consumer.start();
    }
}
