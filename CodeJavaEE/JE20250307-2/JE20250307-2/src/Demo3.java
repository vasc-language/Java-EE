import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * Description: 文件读写内容：数据流
 * User: 姚东名
 * Date: 2025-03-07
 * Time: 20:12
 */
public class Demo3 {
    public static void main(String[] args) throws IOException {
        // 方式一：传统的 try-finally 方式
        /*InputStream inputStream = null;
        try {
            inputStream = new FileInputStream("./text1.txt");
        } finally {
            inputStream.close();
        }*/
        // 方式二：try-with-resource
        // 相较于第一种处理方式，优点在于：
        // 1. 自动资源管理：避免 NullPointerException
        // 2. 异常透明性：保留原始异常
        // 3. 代码简洁性：减少代码的冗余
        // 4. 资源泄漏防护：强制关闭资源 不用再写 close
        try (InputStream inputStream = new FileInputStream("D:\\Java\\java-ee-beginner\\CodeJavaEE\\JE20250307-2\\JE20250307-2\\text\\text01.txt")) {
            // 读文件操作
            while (true) {
                /*// 一次读一个字节
                int data = inputStream.read();
                // 下一个字节的数据，如果到达流的末尾，则返回 -1。
                if (data == -1) {
                    break;
                }
                System.out.printf("0x%x\n", data); // 转化为 16 进制*/

                // 一次读多个字节，数组的长度，自行定义
                // 1. 读操作，尽可能将数组长度填满
                // 2. 能读多少读多少
                // 3. n 代表的是实际上读了多少的字符
                byte[] data = new byte[3];
                int n = inputStream.read(data);
                if (n == -1) {
                    break;
                }
                System.out.println("n = " + n);
                for (int i = 0; i < n; i++) {
                    System.out.printf("0x%x\n", data[i]);
                }
                System.out.println("============");
            }
        }
    }
}
