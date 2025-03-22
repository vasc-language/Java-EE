/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2024-11-09
 * Time: 12:50
 */
public class Demo9 {
    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            System.out.println("hello thread");
        });
        //启动线程
        //t.start();
        t.start();
        //t.run();
    }
}
