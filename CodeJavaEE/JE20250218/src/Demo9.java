/**
* Created with IntelliJ IDEA.
* Description:
* User: 姚东名
* Date: 2025-03-11
* Time: 21:26
*/

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;

/**
 * 示例3
 * 扫描指定目录，并找到名称或者内容包含指定的字符的所有的普通目录（不包含目录）
 * 注意：我们现在的方案性能比较差，所以尽量不要再太复杂的目录下或者大文件实验
 */
public class Demo9 {
    public static void main(String[] args) {
        // 1. 请输入要搜索的路径
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入要搜索的路径：");
        String rootPath = scanner.next();
        File rootFile = new File(rootPath);
        if (!rootFile.isDirectory()) {
            System.out.println("输入的路径不是目录");
            return;
        }
        // 3. 请输入要搜索的关键字
        System.out.println("请输入要搜索的关键字：");
        String keyword = scanner.next();
        scanDir(rootFile, keyword);
    }

    private static void scanDir(File rootFile, String keyword) {
        // 1. 列出当前目录下所有的内容
        File[] files = rootFile.listFiles();
        // 当前目录是否为空
        if (files == null) {
            return;
        }
        // 2. 遍历当前目录下所有的文件
        for (File file : files) {
            if (file.isFile()) {
                // 找关键字
                dealFile(file, keyword);
            } else {
                scanDir(file, keyword);
            }
        }
    }

    private static void dealFile(File file, String keyword) {
        // 判定文件名是否包含关键字
        if (file.getName().contains(keyword)) {
            // 包含关键字，打印文件名
            System.out.println("w文件名包含关键字：" + file.getAbsolutePath());
            return;
        }
        // 2. 判定文件内容是否包含. 由于 keyword 是字符串. 就按照字符流的方式来处理.
        StringBuilder stringBuilder = new StringBuilder();
        try (Reader reader = new FileReader(file)) {
            while (true) {
                char[] chars = new char[1024];
                int n = reader.read(chars);
                if (n == -1) {
                    break;
                }
                stringBuilder.append(chars, 0, n);
            }
        } catch (IOException e) {
            throw new RuntimeException();
        }
        // 3. 判定 stringBuilder 是否包含关键字
        if (stringBuilder.indexOf(keyword) >= 0) {
            System.out.println("文件内容包含关键字:" + file.getAbsolutePath());
        }
        return;
    }
}
