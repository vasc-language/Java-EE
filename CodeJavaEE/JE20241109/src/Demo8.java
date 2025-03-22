/**
 * Created with IntelliJ IDEA.
 * Description: isAlive()：判断当前线程是否存活
 * User: 姚东名
 * Date: 2024-11-09
 * Time: 12:27
 */
public class Demo8 {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                System.out.println("hello thread");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // 是否存活，即简单的理解，为 run ⽅法是否运⾏结束了
        // 此时执行的结果一定是false，因为还没创建线程t
        System.out.println(t.isAlive());

        t.start();

        while (true) {
            System.out.println(t.isAlive());
            Thread.sleep(1000);
        }
    }
}
