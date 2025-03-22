package netwrok;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * Description: 客户端接收或请求
 * User: 姚东名
 * Date: 2025-03-18
 * Time: 19:45
 */
public class UdpEchoClient {
    // DatagramSocket 用来接收发送 数据包
    // DatagramPocket 是一个数据包
    private DatagramSocket socket = null; // 将网卡看成一个数据流看待

    // UDP 本身不保存对端的信息，就自己的代码中保存一下
    private String serverIp;
    private int serverPort;

    // 和服务器不同，此构造方法需要指定访问的服务器地址（目的IP地址 + 目的端口号）
    public UdpEchoClient(String serverIp, int serverPort) throws SocketException {
        this.serverIp = serverIp;
        this.serverPort = serverPort;
        socket = new DatagramSocket(); // 不需要指定端口号（这是为什么？）
    }

    // 启动客户端
    public void start() throws IOException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            // 1. 从控制台读取用户输入的内容
            System.out.println("请输入要发送的内容:");
            if (!scanner.hasNext()) {
                break;
            }
            String request = scanner.next();

            // 2. 将读取到的数据，需要构造 DatagramPacket 对象，发送到服务端
            //    不仅要构建 DatagramPacket，还需要设置服务端的目的IP和端口号
            //    request.getBytes().length 字节数组的长度
            //    responsePacket.getLength() 字符串的长度
            //    数据报包含：载荷 + 服务端的ID地址和端口号
            DatagramPacket requestPacket = new DatagramPacket(request.getBytes(), request.getBytes().length,
                    InetAddress.getByName(serverIp), serverPort);

            // 3. 发送数据包
            socket.send(requestPacket);

            // 4. 接收服务器的响应
            //    创建一个空的数据包，来装服务器的载荷和地址
            DatagramPacket responsePacket = new DatagramPacket(new byte[4096], 4096);
            socket.receive(responsePacket);

            // 5. 从服务器中读取的数据进行解析，打印出来
            String response = new String(responsePacket.getData(), 0, responsePacket.getLength());
            System.out.println(response);
        }
    }

    public static void main(String[] args) throws IOException {
        UdpEchoClient client = new UdpEchoClient("127.0.0.1", 9090);
        client.start();
    }
}
