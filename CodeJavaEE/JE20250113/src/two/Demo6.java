package two;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-01-14
 * Time: 17:52
 */
class SharedData {
    // private static boolean flag = false;
    private volatile boolean flag = false;

    // 写操作
    public void write() {
        flag = true;
    }

    // 读操作
    public void read() {
        if (flag) {
            System.out.println("Flag is true"); // Flag is true
        } else {
            System.out.println("Flag is false");
        }
    }
}
public class Demo6 {
    public static void main(String[] args) throws InterruptedException {
        SharedData data = new SharedData();

        Thread t1 = new Thread(data::write);
        Thread t2 = new Thread(data::read);

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }
}
