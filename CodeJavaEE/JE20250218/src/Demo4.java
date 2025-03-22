import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created with IntelliJ IDEA.
 * Description:字节流 OutputStream().write() 输出
 * User: 姚东名
 * Date: 2025-03-11
 * Time: 18:47
 */
public class Demo4 {
    public static void main(String[] args) {
        try (OutputStream outputStream = new FileOutputStream(".test20250311.txt", true)) {
            // 写入 txt 文件 ASCII 码 98 为 'a'
            /*outputStream.write(98);
            outputStream.write(99);
            outputStream.write(100);
            outputStream.write(101);*/

            byte[] bytes = {97};
            outputStream.write(bytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
