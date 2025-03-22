package netwrok.test;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * Created with IntelliJ IDEA.
 * Description: 服务端
 * User: 姚东名
 * Date: 2025-03-19
 * Time: 17:59
 */
public class UdpEchoServer {
    // DatagramSocket 发送接收数据包
    // 接收请求(request) 使用 DatagramSocket.receive()
    // 发出响应(response) 使用 DatagramSocket.send()
    // DatagramPacket 为数据包
    private DatagramSocket socket = null;
    public UdpEchoServer(int port) throws SocketException {
        socket = new DatagramSocket(port);
    }

    public void start() throws IOException {
        System.out.println("服务器启动");
        while (true) {
            // 1. 读取请求并解析
            DatagramPacket requestPacket = new DatagramPacket(new byte[4096], 4096);
            socket.receive(requestPacket);
            // 将请求从字节数组转换为字符串
            String request = new String(requestPacket.getData(), 0, requestPacket.getLength());

            // 2. 根据请求，计算响应
            String response = process(request);

            // 3. 把响应结果返回客户端
            DatagramPacket responsePacket = new DatagramPacket(response.getBytes(), response.getBytes().length,
                    requestPacket.getSocketAddress());
            socket.send(responsePacket);

            // 4. 打印日记
            System.out.printf("[%s:%d], req: %s, resp: %s\n", requestPacket.getAddress().toString(), requestPacket.getPort(),
                    request, response);
        }
    }

    // 降低耦合度
    private String process(String request) {
        return request;
    }

    public static void main(String[] args) throws IOException {
        UdpEchoServer server = new UdpEchoServer(9091);
        server.start();
    }

}
