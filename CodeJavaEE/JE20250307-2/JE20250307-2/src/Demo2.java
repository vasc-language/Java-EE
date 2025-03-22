import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * Description: 创建目录
 * User: 姚东名
 * Date: 2025-03-07
 * Time: 7:37
 */
public class Demo2 {
    public static void main(String[] args) {
        File file = new File(".test/111/222/333");
        // mkdir() 只能创建一级目录，无法创建多级目录
        // boolean mkdir = file.mkdir();
        boolean mkdirs = file.mkdirs(); // mkdir() 就可以创建多级目录
        System.out.println("mkdir = " + mkdirs);
    }
}
