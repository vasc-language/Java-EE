import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created with IntelliJ IDEA.
 * Description: 阻塞队列的使用
 * User: 姚东名
 * Date: 2024-12-20
 * Time: 18:00
 */
public class Demo1 {
    public static void main(String[] args) throws InterruptedException {
        // 实例一个阻塞队列对象
        BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(100);
        for (int i = 0; i < 100; i++) {
            // put(E e)：将元素e插入队列，如果队列已满，线程会被阻塞，直到有空位插入成功。
            queue.put(i);
        }
        System.out.println("队列已经满了");
        queue.put(101);
        System.out.println("尝试再次put元素");
    }
}
