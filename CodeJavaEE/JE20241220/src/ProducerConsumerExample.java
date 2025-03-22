/**
 * Created with IntelliJ IDEA.
 * Description: 简单模拟实现一个阻塞队列， 并基于这个阻塞队列实现生产者消费者模型
 * User: 姚东名
 * Date: 2024-12-28
 * Time: 17:26
 */

class SimpleBlockingQueue {
    private final String[] queue;
    private int size;
    private int head;
    private int tail;

    // 锁对象，用于线程同步
    private final Object lock = new Object();

    public SimpleBlockingQueue(int capacity) {
        this.queue = new String[capacity];
        this.size = 0;
        this.head = 0;
        this.tail = 0;
    }

    // 生产者往队列中放入元素
    public void put(String item) throws InterruptedException {
        synchronized (lock) {
            while (size == queue.length) {
                lock.wait();
            }
            queue[tail] = item;
            tail = (tail + 1) % queue.length;
            size++;
            lock.notify();
        }
    }

    // 消费者从队列中获取元素
    public String take() throws InterruptedException {
        synchronized (lock) {
            while (size == 0) {
                lock.wait();
            }
            String item = queue[head];
            head = (head + 1) % queue.length;
            size--;
            lock.notify();
            return item;
        }
    }
}

class Producer implements Runnable {
    private final SimpleBlockingQueue queue;

    public Producer(SimpleBlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            try {
                String message = "Message" + i;
                queue.put(message);
                System.out.println("Produced: " + message);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

class Consumer implements Runnable {
    private final SimpleBlockingQueue queue;

    public Consumer(SimpleBlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                String item = queue.take();
                System.out.println("Consumed: " + item);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}

public class ProducerConsumerExample {
    public static void main(String[] args) {
        SimpleBlockingQueue queue = new SimpleBlockingQueue(100);
        // 需要两个线程 组成生产者消费者模型
        Thread Producer = new Thread(() -> {
            int n = 0;
            while (true) {
                try {
                    queue.put(n+"");
                    System.out.println("生产元素：" + n);
                    n++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
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
                    e.printStackTrace();
                }
            }
        }, "Consumer");

        // 启动线程
        Producer.start();
        Consumer.start();
    }
    public static void main1(String[] args) {
        SimpleBlockingQueue queue = new SimpleBlockingQueue(3);
        Thread producerThread = new Thread(new Producer(queue));
        Thread consumerThread = new Thread(new Consumer(queue));

        producerThread.start();
        consumerThread.start();

        try {
            producerThread.join();
            consumerThread.interrupt();
            consumerThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}