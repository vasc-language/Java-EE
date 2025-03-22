package netwrok.test;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * Description: 客户端
 * User: 姚东名
 * Date: 2025-03-19
 * Time: 18:00
 */
public class UdpEchoClient {
    // DatagramSocket 发送接收数据包
    // 发出请求(request) 使用 DatagramSocket.send()
    // 接收响应(response) 使用 DatagramSocket.receive()
    // DatagramPacket 是一个数据包
    DatagramSocket socket = null;
    // UDP 本身没有现存 目的IP和目的端口号，需手动指定
    private String serverIp;
    private int serverPort;

    public UdpEchoClient(String serverIp, int serverPort) throws SocketException {
        this.serverIp = serverIp;
        this.serverPort = serverPort;
        socket = new DatagramSocket(); // 客户端的端口号由操作系统分配，这是为什么？
    }

    public void start() throws IOException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            // 1. 从控制台获取输入的数据报
            System.out.println("请输入要发送的内容：");
            if (!scanner.hasNext()) {
                break;
            }
            String request = scanner.next();

            // 2. 将请求的数据封装成数据包 DatagramPacket ，发送给服务端
            //    数据报包含：载荷 + 服务端的ID地址和端口号
            DatagramPacket requestPacket = new DatagramPacket(request.getBytes(), request.getBytes().length,
                    InetAddress.getByName(serverIp), serverPort);

            // 3. 发送数据包
            socket.send(requestPacket);

            // 4. 接收服务器的响应
            DatagramPacket responsePacket = new DatagramPacket(new byte[4096], 4096);
            socket.receive(responsePacket);

            // 将接收的字节数组的内容转化为 字符串
            String response = new String(responsePacket.getData(), 0, responsePacket.getLength());
            System.out.println(response);
        }
    }

    public static void main(String[] args) throws IOException {
        UdpEchoClient udpEchoClient = new UdpEchoClient("127.0.0.1", 9091);
        udpEchoClient.start();
    }

}
