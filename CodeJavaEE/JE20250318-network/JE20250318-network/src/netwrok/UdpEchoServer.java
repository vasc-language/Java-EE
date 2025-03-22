package netwrok;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * Created with IntelliJ IDEA.
 * Description: 服务器 请求或相应
 * User: 姚东名
 * Date: 2025-03-18
 * Time: 19:44
 */
public class UdpEchoServer {
    // DatagramSocket 是 UPD Socket，用于发送或接收 UPD 数据报[DatagramSocket 发送接收数据包]
    // 请求 request 使用 DatagramSocket.receive()
    // 响应response 使用 DatagramSocket.send()
    // DatagramPacket 是 UDP Socket 发送和请求的数据包[DatagramPacket 是数据包]
    private DatagramSocket socket = null;
    public UdpEchoServer(int port) throws SocketException {
        socket = new DatagramSocket(port);
    }

    // 启动 服务器
    public void start() throws IOException {
        // 启动服务器
        System.out.println("服务器启动");
        while (true) {
            // 循环一次，相当于处理一次请求
            // 服务器处理请求，一般分为三个步骤
            // 1. 读取请求并解析
            //    DatagramPacket 是一个 UDP 数据包，此处传入一个字节数组，保存 UDP 的载荷部分
            DatagramPacket requestPacket = new DatagramPacket(new byte[4096], 4096);
            socket.receive(requestPacket);
            //    将读取到的二进制数据，转化为字符串，只是构造有效的部分（为什么要转化为字符串？）
            String request = new String(requestPacket.getData(),0, requestPacket.getLength());

            // 2. 根据请求，计算响应（服务器最核心的部分）
            //    但是此时写的是回显服务器，这一步就省略
            String response = process(request);


            // 3. 把响应返回客户端
            // 根据 response 构造 DatagramPacket 返回给客户端
            // 此时，有效长度不能使用 response.length()
            // 数据包包含：载荷 + 客户端的地址 requestPacket.getSocketAddress()包括了目的IP和目的端口
            DatagramPacket responsePacket = new DatagramPacket(response.getBytes(), response.getBytes().length,
                    requestPacket.getSocketAddress());
            // 此时还不能发送，UDP 协议并没有保存对方的信息（不知道发给谁）
            // 需要指定目的ID和目的端口
            socket.send(responsePacket);

            // 4. 打印日志
            System.out.printf("[%s:%d] req: %s, resp: %s\n", requestPacket.getAddress().toString(), requestPacket.getPort(),
                    request, response);
        }
    }

    // 这个
    public String process(String request) {
        return request;
    }

    public static void main(String[] args) throws IOException {
        UdpEchoServer server = new UdpEchoServer(9091);
        server.start();
    }
}
