/**
 * Created with IntelliJ IDEA.
 * Description: 多线程：wait的作用
 * User: 姚东名
 * Date: 2024-12-10
 * Time: 8:00
 */
public class Demo1 {
    public static void main(String[] args) throws InterruptedException {
        Object object = new Object();
        System.out.println("在wait 之前");
        synchronized (object) {

            object.wait();

        }
        System.out.println("在wait之后");
    }
}
