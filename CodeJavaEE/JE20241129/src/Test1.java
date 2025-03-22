/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2024-11-29
 * Time: 10:20
 */
public class Test1 {
    private static int count = 0;
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            Thread cur = Thread.currentThread(); //Thread[Thread-0,5,main]
            System.out.println(cur);
            for (int i = 0; i < 50000; i++) {
                synchronized (cur) {
                    count++;
                }
            }
            System.out.println("t1 线程结束");
        });
        Thread t2 = new Thread(() -> {
            /*Thread cur = Thread.currentThread(); // Thread[Thread-1,5,main]
            System.out.println(cur);*/
            for (int i = 0; i < 50000; i++) {
                synchronized (t1) {
                    count++;
                }
            }
            System.out.println("t2 线程结束");
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();
        //count预期是10_000
        System.out.println("count: " + count); // count: 100000，符合预期
    }
}
