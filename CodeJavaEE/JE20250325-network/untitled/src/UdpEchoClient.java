import java.io.IOException;
import java.net.*;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * Description: 客户端
 * User: 姚东名
 * Date: 2025-03-25
 * Time: 18:16
 */
public class UdpEchoClient {
    private DatagramSocket socket = null;
    private String serverIp;
    private int serverPort;

    // 客户端不存储对端的 IP 地址和端口号，需手动指定
    public UdpEchoClient(String serverIp, int serverPort) throws SocketException {
        this.serverIp = serverIp;
        this.serverPort = serverPort;
        socket = new DatagramSocket(); // 客户端的端口号不用手动指定，有操作系统自行分配
    }

    public void start() throws IOException {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // 1. 从控制台读取用户输入的内容
            System.out.println("想要输入的内容：");
            if (!scanner.hasNext()) {
                break;
            }
            String request = scanner.next();

            // 2. 把请求发送给服务器，将数据报封装成 DatagramPacket 对象
            DatagramPacket requestPacket = new DatagramPacket(
                    request.getBytes(),
                    request.getBytes().length,
                    InetAddress.getByName(serverIp), // 获取服务器的IP地址和端口号
                    serverPort);

            // 3. 发送数据报
            socket.send(requestPacket);

            // 4. 接受服务器的响应结果
            DatagramPacket responsePacket = new DatagramPacket(new byte[4096], 4096);
            socket.receive(responsePacket);
            
            // 5. 从服务器读取数据，进行解析，打印出来
            String response = new String(responsePacket.getData(), 0, responsePacket.getLength());
            System.out.println(response);
        }
    }

    public static void main(String[] args) throws IOException {
        UdpEchoClient udpEchoClient = new UdpEchoClient("127.0.0.1", 9090);
        udpEchoClient.start();
    }
}
