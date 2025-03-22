/**
 * Created with IntelliJ IDEA.
 * Description: 看当前线程的执行状态：NEW && TIMED_WAITING
 * User: 姚东名
 * Date: 2024-11-16
 * Time: 16:58
 */
public class Demo3 {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            while (true) {
                // 这里什么都不执行
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // 看此时线程的执行状态
        System.out.println(t.getState()); // NEW

        t.start();

        Thread.sleep(1000);

        // 看此时线程的执行状态
        System.out.println(t.getState()); // TIMED_WAITING
    }

    public static void main1(String[] args) {
        // 观察线程所有的状态
        for (Thread.State state : Thread.State.values()) {
            System.out.println(state);
        }
    }
}
