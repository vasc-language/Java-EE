import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description: 使用 Collections.synchronizedList(new ArrayList())
 * User: 姚东名
 * Date: 2025-01-25
 * Time: 22:44
 */
public class Demo11 {
    private static final List<String> list = Collections.synchronizedList(new ArrayList<>());

    public static void main(String[] args) {
        // 添加元素
        for (int i = 0; i < 10; i++) {
            list.add("Item " + i);
        }

        // 遍历 list
        synchronized (list) {
            for (String item : list) {
                System.out.println(item);
            }
        }
    }
}
