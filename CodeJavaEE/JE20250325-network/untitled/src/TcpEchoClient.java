import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-03-25
 * Time: 19:54
 */


public class TcpEchoClient {
    private Socket socket = null;

    public TcpEchoClient(String serverIp, int serverPort) throws IOException {
        // 直接把字符串的IP地址设置进来
        // 127.0.0.1
        socket = new Socket(serverIp, serverPort);
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("要输入的内容：");
        try (InputStream inputStream = socket.getInputStream();
             OutputStream outputStream = socket.getOutputStream()) {
            // 方便使用，还是对 inputSteam 和 outputStream 进行套壳
            Scanner scannerNet = new Scanner(inputStream);
            PrintWriter writer = new PrintWriter(outputStream);

            // 从控制台中获取数据 发送给服务器
            while (true) {
                // 1. 从控制台读取用户输入的内容
                String request = scanner.next();
                // 2.将内容发送到服务端中
                writer.println(request);
                // 进行刷新缓存
                writer.flush();
                // 3. 接收服务端响应回来的结果
                String response = scannerNet.next();
                // 4. 打印日志
                System.out.println(response);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws IOException {
        TcpEchoClient client = new TcpEchoClient("127.0.0.1", 9090);
        client.start();
    }
}
