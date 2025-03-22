package Mode;

/**
 * Created with IntelliJ IDEA.
 * Description: 回调函数的基本实现方式2：使用匿名内部类
 * User: 姚东名
 * Date: 2024-11-09
 * Time: 21:28
 */
public class Main2 {
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
