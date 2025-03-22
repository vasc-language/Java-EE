import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created with IntelliJ IDEA.
 * Description: 字节流 InputStream.read()
 * User: 姚东名
 * Date: 2025-03-11
 * Time: 7:29
 */
public class Demo3 {
    public static void main(String[] args) {
        // 1. 打开文件
        // 2. 读写文件 输入流 从文件中 read 到 CPU 中
        //            输出流 从 CPU 中 write 到 文件中
        // 3. 关闭文件
        try (InputStream inputStream = new FileInputStream("D:\\Java\\java-ee-beginner\\CodeJavaEE\\JE20250218\\test20250220.txt")) {
            // 写文件
            while (true) {
                // 一次写入 一个字节
                /*int data = inputStream.read();
                if (data == -1) {
                    break;
                }
                System.out.printf("0x%x\n", data);*/

                // 一次读多个字节，数组的长度自行设置
                byte[] data = new byte[3]; // 数组长度设置为3，
                int read = inputStream.read(data);
                System.out.println("read = " + read);
                if (read == -1) {
                    // 读完，结束循环
                    break;
                }
                for (int i = 0; i < read; i++) {
                    System.out.printf("0x%x\n", data[i]);
                }
                System.out.println("===================");
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
