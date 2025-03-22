import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * Description: 内存可见性问题
 * User: 姚东名
 * Date: 2024-11-30
 * Time: 20:51
 */
public class Demo4 {
    public static int flag = 0;

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            while (flag == 0) {
                // 在while循环中加上线程休眠sleep
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("t1 线程结束");
        });

        Thread t2 = new Thread(() -> {
            // 针对flag进行修改
            Scanner scanner = new Scanner(System.in);
            System.out.println("请输入flag的值：");
            flag = scanner.nextInt();
        });

        t1.start();
        t2.start();
    }
}
