package netwrok;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-03-18
 * Time: 7:03
 */

/**
 * Socket 套接字主要在传输层协议进行三种划分
 * 1. 流套接字 使用 TCP 协议，简称 Transmission Control Protocol(传输控制协议)，传输层协议
 * 特点：1）有连接 2）可靠传输 3）面向字节流
 *      4）有接收缓存区，也有发送缓存区
 *      5）大小不限
 * 2. 数据报套接字 使用 UDP 协议，简称 User Datagram Protocol(用户数据报协议)，传输层协议
 * 特点：1）无连接 2）不可靠传输 3）面向数据报
 *      4）有接收缓存区，也有发送缓存区
 *      5）大小限制：一次最多传输64k
 * 3. 原始套接字
 */
public class Text {
    public static void main(String[] args) {

    }
}
