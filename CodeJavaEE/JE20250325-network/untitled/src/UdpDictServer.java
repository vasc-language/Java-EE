import java.io.IOException;
import java.net.SocketException;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * Description: 编写一个英译汉的服务器，只需要重写 process 方法
 * User: 姚东名
 * Date: 2025-03-25
 * Time: 18:16
 */
public class UdpDictServer extends UdpEchoServer {
    private HashMap<String, String> dict = new HashMap<>();

    public UdpDictServer(int port) throws SocketException {
        super(port);

        // 初始化词典
        dict.put("小猫", "cat");
        dict.put("小狗", "dog");
        dict.put("小兔子", "rabbit");
        dict.put("小鸭子", "duck");
    }

    @Override
    public String process(String request) {
        // 查字典.
        return dict.getOrDefault(request, "未找到该词条");
    }

    public static void main(String[] args) throws IOException {
        UdpDictServer server = new UdpDictServer(9090);
        server.start();
    }
}
