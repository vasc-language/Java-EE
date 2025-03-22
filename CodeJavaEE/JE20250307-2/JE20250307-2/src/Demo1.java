import java.io.File;
import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-03-07
 * Time: 7:20
 */
public class Demo1 {
    public static void main(String[] args) {
        // 针对文件是无法进行 list 的
        // File file = new File("./text.txt");
        // 必须要针对目录才能进行 list
        // list() 的作用：返回 list 对象代表目录下面的所有的文件名
        File file = new File("c:/");
        String[] list = file.list();
        System.out.println(Arrays.toString(list));

        System.out.println("------------------------------------");
        // listFile() 的作用：返回 listFile 对象代表的目录下的所有文件，以 file 对象来表示
        File[] files = file.listFiles();
        //files.
        System.out.println(Arrays.toString(files));
    }
}
