import java.io.*;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-03-11
 * Time: 20:52
 */
// 示例2：进行普通文件的复制
public class Demo8 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 1. 请输入源文件路径
        System.out.println("请输入源文件路径：");
        String srcPath = scanner.next();
        // 2. 请输入目标文件路径
        System.out.println("请输入目标文件路径：");
        String destPath = scanner.next();
        // 3. 源文件不存在或不是文件
        File srcFile = new File(srcPath);
        if (!srcFile.isFile()) {
            return;
        }
        // 要求 destFile 不一定存在.
        // 但是 destFile 所在的目录必须存在.
        // 例如, destFile 为 d:/test/test.txt, 则要求 d:/test 目录存在.
        File destFile = new File(destPath);
        if (!destFile.getParentFile().isDirectory()) {
            System.out.println("目标文件所在目录不存在！");
            return;
        }

        // 真正进行复制文件的操作
        // 此处不应该使用追加写. 需要确保目标文件和源文件一模一样.
        try (InputStream inputStream = new FileInputStream(srcFile);
             OutputStream outputStream = new FileOutputStream(destFile)) {
            while (true) {
                // 输入，从 srcFile 中读取数据
                byte[] buf  = new byte[1024];
                int n = inputStream.read(buf);
                if (n == -1) {
                    break;
                }
                // 此处的 write 不应该写整个 buf 数组的
                // 因为 buf 数组不一定会填满，读多少 写多少
                outputStream.write(buf, 0, n);
            }
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
