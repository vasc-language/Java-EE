/**
 * Created with IntelliJ IDEA.
 * Description: synchronized 的变种写法：synchronized 修饰方法
 * User: 姚东名
 * Date: 2024-11-25
 * Time: 20:26
 */
class Counter {
    private int count = 0;

    public synchronized void add() {
        count++;
    }

    public void add1() {
        synchronized (this) {
            count++;
        }
    }

    public int get() {
        return count;
    }
    public synchronized static void func() {
        synchronized (Counter.class) {

        }
    }
}
public class Demo4 {
    public static void main(String[] args) throws InterruptedException {
        Object locker = new Object();
        Counter counter = new Counter();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 50000; i++) {
                /*synchronized (locker) {
                    counter.add();
                }*/
                counter.add();
            }
            System.out.println("t1 线程结束");
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 50000; i++) {
                /*synchronized (locker) {
                    counter.add();
                }*/
                counter.add();
            }
            System.out.println("t2 线程结束");
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("count：" + counter.get());
    }
}
