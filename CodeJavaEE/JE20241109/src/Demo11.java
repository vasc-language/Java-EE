/**
 * Created with IntelliJ IDEA.
 * Description: 内部类访问外部类的成员
 * User: 姚东名
 * Date: 2024-11-09
 * Time: 21:39
 */
public class Demo11 {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            // 这个代码是在lambda中（也就是 t 线程的入口方法中）调用的，返回的结果是 t
            System.out.println("t: " + Thread.currentThread().getName()); // t: Thread-0
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("hello thread");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    break;
                }
            }
            System.out.println("Thread线程结束");
        });

        t.start();

        Thread.sleep(3 * 1000);
        System.out.println("main线程尝试终止 t 线程");
        t.interrupt();

        // 这个代码是在 main 方法中调用的, 此时返回结果就是 main
        System.out.println("t: " + Thread.currentThread().getName()); // t: main
    }
}
