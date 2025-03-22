import java.io.File;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-02-20
 * Time: 12:49
 */
public class Demo2 {
    public static void main(String[] args) throws IOException {
        File file = new File("./test20250220.txt");

        // createNewFile() 创建一个新的空文件
        // （如果文件创建成功[且不存在]，返回 true ，如果创建失败，返回 false 且抛出 IOException）
        file.createNewFile();

        // exists() 检查文件或目录是否存在（存在返回 true ，不存在 返回 false）
        System.out.println(file.exists());
        // isFile() 判断是否为文件（必须先存在 exists() 为 true，且是文件类型，返回 true， 否则返回 false）
        System.out.println(file.isFile());
        // isDirectory() 判断是否是目录（必须先存在 exists() 为 true， 且是目录类型才返回 true，否则返回 false）
        System.out.println(file.isDirectory());
    }
}
