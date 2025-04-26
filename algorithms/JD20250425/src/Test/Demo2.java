package Test;

public class Demo2 {
    // 1. 二进制求和
    // https://leetcode.cn/problems/add-binary/description/
    public String addBinary(String a, String b) {
        StringBuffer ret = new StringBuffer(); // 用于字符串拼接
        // 申请两个指针，分别遍历各自的字符串
        int cur1 = a.length() - 1, cur2 = b.length() - 1;
        int t = 0;

        while (cur1 >= 0 || cur2 >= 0 || t != 0) {
            if (cur1 >= 0) {
                t += (a.charAt(cur1--) - '0');
            }
            if (cur2 >= 0) {
                t += (b.charAt(cur2--) - '0');
            }

            /**
             * 在二进制加法中，每一位的计算结果加上可能来自低位的进位后，可能有以下几种情况
             * （bit_a + bit_b + carry_in）：
             * 0 + 0 + 0 = 0
             * 0 + 0 + 1 = 1
             * 0 + 1 + 0 = 1
             * 1 + 0 + 0 = 1
             * 0 + 1 + 1 = 2 (二进制为 10)
             * 1 + 0 + 1 = 2 (二进制为 10)
             * 1 + 1 + 0 = 2 (二进制为 10)
             * 1 + 1 + 1 = 3 (二进制为 11)
             *
             * t % 2 计算的是 t 除于2的余数
             * - 当和为0或者2时，余数都是0
             * - 当和为1或者3时，余数都是1
             * 这个余数（0/1）正好就是当前位应该填入的结果的值
             * 所以 ret.append((char)('0' + (char)(t % 2)));
             * 这行代码就是把当前计算位的二进制结果（0/1）追加到结果字符串中
             */
            ret.append((char)('0' + (char)(t % 2)));
            /**
             * t / 2 计算的是 t 除于2的商（整数部分），再次观察加法结果：
             * - 但和为0或者1时，商是0
             * - 当和为2或者3时，商是1
             * 这个商（0/1）正好就是需要向更高位传递的进位值
             */
            t /= 2;
        }
        ret.reverse();
        return ret.toString();
    }
}
