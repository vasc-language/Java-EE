/**
 * Created with IntelliJ IDEA.
 * Description: t.interrupt()的简单介绍
 * User: 姚东名
 * Date: 2024-11-16
 * Time: 12:38
 */
public class Demo1 {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            // 这个代码是在 lambda表达式中，（也就是在 t 线程的入口方法中）调用的
            // 获取当前引用：返回结果是 t
            System.out.println("t：" + Thread.currentThread().getName());
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("hello thread");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    /**
                     * 这个线程掀桌了
                     * e.printStackTrace();
                     * 1. 加上 break 就是立即终止
                     * 2. 啥也不写 就是不终止
                     * 3. catch 中先执行一些其他逻辑 再break，就是稍后终止
                     */
                    // e.printStackTrace();
                    // break;
                }
            }
            System.out.println("t 线程结束");
        });

        t.start();

        Thread.sleep(3 * 1000);
        System.out.println("main线程尝试终止 t 线程结束");
        t.interrupt();

        // 这个代码是在 main 方法中调用的，返回结果是 main
        System.out.println("main：" + Thread.currentThread().getName());
    }
}
