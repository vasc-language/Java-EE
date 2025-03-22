import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

/**
 * Created with IntelliJ IDEA.
 * Description: 字符流 Reader.FileReader() 输入流
 * User: 姚东名
 * Date: 2025-03-11
 * Time: 19:10
 */
public class Demo5 {
    public static void main(String[] args) {
        try (Reader reader = new FileReader("D:\\Java\\java-ee-beginner\\CodeJavaEE\\JE20250218\\test20250220.txt")) {
            while (true) {
                // 也是可以自定义的
                char[] buf = new char[1024];
                int n = reader.read(buf);
                if (n == -1) {
                    break;
                }
                for (int i = 0; i < n; i++) {
                    System.out.print(buf[i]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
