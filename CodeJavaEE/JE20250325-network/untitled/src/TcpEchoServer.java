/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-03-25
 * Time: 19:53
 */

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * TCP 协议 Transmission Control Protocol 传输控制协议协议 可靠、有序、流量控制
 * UDP 协议 User Datagram Protocol 用户数据报协议 快速、无连接、尽最大努力交付
 *
 *TCP 流套接字编程
 * ServerSocket(int port) 专门给服务器用的，并绑定一个端口号
 * Socket 服务器和客户端都可以用
 * Socket accept() 开始监听指定的端口号，有客户端连接后，返回一个 Socket 服务端对象
 * 并基于该 Socket 建立与客户端的连接，否则阻塞等待
 *
 * TCP 协议的一个核心特点，面向字节流。读写数据的基本单位就是字节 byte
 */
public class TcpEchoServer {
    private ServerSocket serverSocket = null;

    // 这里和 UDP 服务器类似, 也是在构造对象的时候, 绑定端口号.
    public TcpEchoServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }

    public void start() throws IOException {
        System.out.println("启动服务器");

        // 这种情况一般不会使用 fixedThreadPool, 意味着同时处理的客户端连接数目就固定了.
        ExecutorService executorService = Executors.newCachedThreadPool();

        while (true) {
            // tcp 来说, 需要先处理客户端发来的连接.
            // 通过读写 clientSocket, 和客户端进行通信.
            // 如果没有客户端发起连接, 此时 accept 就会阻塞.

            // 主线程负责进行 accept, 每次 accept 到一个客户端, 就创建一个线程, 由新线程负责处理客户端的请求.
            Socket clientSocket = serverSocket.accept();

            // 使用多线程的方式来调整
//            Thread t = new Thread(() -> {
//                processConnection(clientSocket);
//            });
//            t.start();

            // 使用线程池来调整
            executorService.submit(() -> {
                processConnection(clientSocket);
            });
        }
    }

    // 处理一个客户端的连接.
    // 可能要涉及到多个客户端的请求和响应.
    private void processConnection(Socket clientSocket) {
        System.out.printf("[%s:%d] 客户端上线!\n", clientSocket.getInetAddress(), clientSocket.getPort());
        try (InputStream inputStream = clientSocket.getInputStream();
             OutputStream outputStream = clientSocket.getOutputStream()) {
            // 针对 InputStream 套了一层
            Scanner scanner = new Scanner(inputStream);
            // 针对 OutputStream 套了一层
            PrintWriter writer = new PrintWriter(outputStream);
            // 分成三个步骤
            while (true) {
                // 1. 读取请求并解析. 可以直接 read, 也可以借助 Scanner 来辅助完成.
                if (!scanner.hasNext()) {
                    // 连接断开了
                    System.out.printf("[%s:%d] 客户端下线!\n", clientSocket.getInetAddress(), clientSocket.getPort());
                    break;
                }
                String request = scanner.next();
                // 2. 根据请求计算响应
                String response = process(request);
                // 3. 返回响应到客户端
                // outputStream.write(response.getBytes());
                writer.println(response);
                writer.flush();

                // 打印日志
                System.out.printf("[%s:%d] req: %s, resp: %s\n", clientSocket.getInetAddress(), clientSocket.getPort(),
                        request, response);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private String process(String request) {
        return request;
    }

    public static void main(String[] args) throws IOException {
        TcpEchoServer server = new TcpEchoServer(9090);
        server.start();
    }
}

/*public class TcpEchoServer {
    private ServerSocket serverSocket = null;

    public TcpEchoServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }

    public void start() throws IOException {
        System.out.println("服务器启动");
        while (true) {
            // tcp 来讲 需要先处理客户端发来的连接
            // 通过读写 clientSocket 和客户端进行通信
            // 假如通信不成功 accept就会阻塞等待

            // 主线程负责进行 accept ，每次与客户端通信成功，就 创建一个新的线程来负责处理客户端的请求和响应
            Socket clientSocket = serverSocket.accept();

            Thread t = new Thread(() -> {
                try {
                    processConnection(clientSocket);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            t.start();
        }
    }

    // 处理一个客户端的连接
    public void processConnection(Socket clientSocket) throws IOException {
        System.out.printf("[%s:%d] 客户端上线", clientSocket.getInetAddress(), clientSocket.getPort());
        try (InputStream inputStream = clientSocket.getInputStream();
             OutputStream outputStream = clientSocket.getOutputStream()) {
            // 针对 inputStream 进行一次套壳
            Scanner scanner = new Scanner(inputStream);
            // 针对 outputStream 进行一次套壳
            PrintWriter writer = new PrintWriter(outputStream);
            // 分为三个步骤
            while (true) {
                // 1. 读取请求并解析，可以直接使用 read，也可借助 Scanner 来辅助完成
                if (!scanner.hasNext()) {
                    System.out.printf("[%s:%d] 客户端下线\n", clientSocket.getInetAddress(), clientSocket.getPort());
                    break;
                }
                String request = scanner.next();
                // 2. 根据请求计算响应
                String response = process(request);

                // 3. 返回响应到客户端
                writer.println(response);
                writer.flush(); // 刷新一下缓存

                // 4. 打印日志
                System.out.printf("[%s:%d] req: %s, resp: %s\n",
                        clientSocket.getInetAddress(),
                        clientSocket.getPort(),
                        request,
                        response);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private String process(String request) {
        return request;
    }

    public static void main(String[] args) throws IOException {
        TcpEchoServer server = new TcpEchoServer(9090);
        server.start();
    }
}*/
