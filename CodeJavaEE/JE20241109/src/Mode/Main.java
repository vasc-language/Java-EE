package Mode;
/**
 * Created with IntelliJ IDEA.
 * Description: 回调函数的基本实现方式1：使用接口
 * User: 姚东名
 * Date: 2024-11-09
 * Time: 13:07
 */
interface Callback {
    void onCompletion(String result);
}

class Task {
    public void execute(Callback callback) {
        // 模拟一些工作
        String result = "Task Completed";
        
        // 调用回调函数
        callback.onCompletion(result);
    }
}

public class Main {
    public static void main(String[] args) {
        Task task = new Task();
        task.execute(new Callback() {
            @Override
            public void onCompletion(String result) {
                System.out.println(result);
            }
        });
    }
}