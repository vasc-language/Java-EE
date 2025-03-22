import java.io.File;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-03-11
 * Time: 19:55
 */

/**
 * 示例1
 * 扫描指定目录，并找到名称中包含指定字符的所有普通文件（不包含目录），并且后续询问用户是否要删除该文件
 * 1. 查找目录
 * 2. 是否为文件
 * 是普通文件，查找关键字
 */


public class Demo7 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入要查找的目录：");
        String rootDri = scanner.next();
        File rootFile = new File(rootDri);
        if (!rootFile.isDirectory()) {
            System.out.println("输入的不是目录！");
            return;
        }
        System.out.println("输入要删除的关键字：");
        String keyword = scanner.next();
        // 寻找目录
        FindDri(rootFile, keyword);
    }

    private static void FindDri(File rootFile, String keyword) {
        // 1. 展示目录
        File[] files = rootFile.listFiles();
        // 2. 检验
        if (files == null) {
            return;
        }

        // 3. 遍历当前目录中的内容
        for (File file : files) {
            System.out.println("遍历目录&文件: " + file.getAbsolutePath());
            // 4. 判断是普通文件还是目录
            if (file.isFile()) {
                // 5. 是文件，就查找关键字，删除文件中的关键字
                dealFile(file, keyword);
            } else {
                // 是目录，就继续找，知道找到指定目录，使用递归来做
                FindDri(file, keyword);
            }
        }

    }

    private static void dealFile(File file, String keyword) {
        if (file.getName().contains(keyword)) {
            System.out.println("发现文件：" + file.getAbsolutePath() + "，包含关键字，是否删除(Y/N)");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.next();
            if (input.equalsIgnoreCase("Y")) {
                file.delete();
                System.out.println("文件删除成功！");
            }
        }
    }
}
