package Mode;

/**
 * Created with IntelliJ IDEA.
 * Description: 回调函数的基本实现方式3：使用lambda表达式
 * User: 姚东名
 * Date: 2024-11-09
 * Time: 21:30
 */
public class Main3 {
    public static void main(String[] args) {
        Task task = new Task();
        task.execute(result -> System.out.println(result));
    }
}
