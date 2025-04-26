package Test2;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-04-26
 * Time: 10:57
 */
public class Demo1 {
    public String multiply(String num1, String num2) {
        int m = num1.length(), n = num2.length();
        char[] n1 = new StringBuffer(num1).reverse().toString().toCharArray();
        char[] n2 = new StringBuffer(num2).reverse().toString().toCharArray();

        // 创建一个临时数组，来记录无进位相加再相乘的数据
        int[] tmp = new int[m + n - 1];

        // 1. 无进位相加再相乘
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                tmp[i + j] += (n1[i] - '0') * (n2[j] - '0');
            }
        }

        // 2. 处理进位，并构建结果字符串
        int cur = 0, t = 0;
        StringBuffer ret = new StringBuffer();

        while (cur < m + n - 1 || t != 0) {
            if (cur < m + n - 1) {
                t += tmp[cur++];
            }
            // 拼接字符串
            ret.append((char)((t % 10) + '0'));
            // 求出下一个进位值
            t /= 10;
        }

        // 3. 处理前导零
        // 因为时逆序的，最后一个可能是 0（1230） ，再逆序回来，就得把这个0 去掉（0321）
        while (ret.length() > 1 && ret.charAt(ret.length() - 1) == '0') {
            ret.deleteCharAt(ret.length() - 1);
        }

        return ret.reverse().toString();
    }
}
