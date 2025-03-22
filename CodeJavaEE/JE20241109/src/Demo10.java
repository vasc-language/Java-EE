/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2024-11-09
 * Time: 13:07
 */
class Test1 {
    public int value = 0;
}
public class Demo10 {
    // 全局变量
    private static boolean isFinished  = false;

    public static void main(String[] args) throws InterruptedException {
        // 放在这里
        //boolean isFinished  = false;
        Test1 test1 = new Test1();

        Thread t = new Thread(() -> {
            while (!isFinished) {
                System.out.println("hello thread");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Thread线程结束");
            // System.out.println(test1.value);
            // test1.value++;
        });

        t.start();

        Thread.sleep(3 * 1000);
        isFinished = true;
    }
}
