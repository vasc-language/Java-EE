import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * Created with IntelliJ IDEA.
 * Description: 字符流 Writer.FileWriter() 输出
 * User: 姚东名
 * Date: 2025-03-11
 * Time: 19:18
 */
public class Demo6 {
    public static void main(String[] args) {
        try (Writer writer = new FileWriter("D:\\Java\\java-ee-beginner\\CodeJavaEE\\JE20250218\\test20250220.txt", true)) {
            writer.write("\nMight I reclaim youth's golden dawn, An ounce of sun, an ounce of breeze withdrawn?");
            writer.write("\n可否许我再少年，一两黄金一两风");
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
