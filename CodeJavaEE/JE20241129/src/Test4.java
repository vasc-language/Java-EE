import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * Description: volatile 关键字
 * User: 姚东名
 * Date: 2024-11-30
 * Time: 22:04
 */
public class Test4 {
    public volatile static int flag = 0;

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            while (flag == 0) {

            }
            System.out.println("t1 线程结束");
        });

        Thread t2 = new Thread(() -> {
            // 修改flag的值
            Scanner scanner = new Scanner(System.in);
            System.out.println("请输入flag的值：");
            flag = scanner.nextInt();
        });

        t1.start();
        t2.start();
    }
}
