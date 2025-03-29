import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * Created with IntelliJ IDEA.
 * Description: 服务器
 * User: 姚东名
 * Date: 2025-03-25
 * Time: 18:16
 */
public class UdpEchoServer {
    private DatagramSocket socket = null;
    public UdpEchoServer(int port) throws SocketException {
        // 指定一个端口号让服务器使用
        socket = new DatagramSocket(port);
    }

    public void start() throws IOException {
        System.out.println("服务器启动");

        while (true) {
            // 1. 读取请求并解析
            DatagramPacket requestPacket = new DatagramPacket(new byte[4096], 4096);
            socket.receive(requestPacket);
            // 把读取到的字节内容转换为字符串
            String request = new String(requestPacket.getData(), 0, requestPacket.getLength());

            // 2. 根据请求，计算响应 （最重要）
            String response = process(request);


            // 3. 把响应的结果返回客户端
            DatagramPacket responsePacket = new DatagramPacket(response.getBytes(), response.getBytes().length,
                    requestPacket.getSocketAddress()); // 拿到客户端的IP地址和端口号
            socket.send(responsePacket);

            // 4. 打印一个日志
            // 客户端的请求的地址:客户端的端口号 request: 发送的请求, response:
            System.out.printf("[%s:%d] request: %s, response: %s\n",
                    requestPacket.getAddress().toString(),
                    requestPacket.getPort(),
                    request,
                    response);
        }
    }

    public String process(String request) {
        return request;
    }

    public static void main(String[] args) throws IOException {
        UdpEchoServer udpEchoServer = new UdpEchoServer(9090);
        udpEchoServer.start();
    }
}


