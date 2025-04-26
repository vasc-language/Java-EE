package Test;

public class Demo3 {
    // 2. 字符串相乘
    // https://leetcode.cn/problems/multiply-strings/description/
    public String multiply(String num1, String num2) {
        int m = num1.length(), n = num2.length();
        char[] n1 = new StringBuffer(num1).reverse().toString().toCharArray();
        char[] n2 = new StringBuffer(num2).reverse().toString().toCharArray();

        int[] tmp = new int[m + n - 1];

        // 1. 无进位相乘再相加
        // tmp = [18, 27, 28, 13, 4]
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // n1[i] - '0' 和 n2[j] - '0' 都是将字符串转换为整数
                // 它们的乘积累加到 tmp[i + j] 这个位置上。这相当于竖式乘法，将对应的的积位先加起来，暂且不处理进位
                tmp[i + j] += (n1[i] - '0') * (n2[j] - '0');
            }
        }

        // 2. 处理进位，并构建结果字符串
        int cur = 0, t = 0;
        StringBuffer ret = new StringBuffer();
        /**
         * 这一步处理竖式乘法的加法和进位过程
         * cur 是当前处理的 tmp 数组的索引（即当前处理的权位）
         * t 是进位（carry）
         * tmp[0] = 18 tmp[1] = 27
         * cur = 0 t += tmp[0] = 18
         * ret.append((char)(18 % 10 + '0')) -> ret.append('8')
         * t = 18 / 10 = 1 cur = 1 ret = '8'
         *
         * cur = 1 t += tmp[1] = 27 + 1 = 28
         * ret.append((char)(28 % 10 + '0')) -> ret.append('8')
         * t = 28 / 10 = 2 cur = 2 ret = '88'
         *
         * 以此类推~
         */
        while (cur < m + n - 1 || t != 0) {
            // 如果 tmp 数组还有未处理的位，将当前位 tmp[cur] 的值累加到 t （累加到上一步的进位），然后 cur 移动到下一位
            if (cur < m + n - 1) {
                t += tmp[cur++];
            }
            ret.append((char)(t % 10 + '0'));
            // 计算新的进位，供下一位使用
            t /= 10;
        }

        // 3. 处理前导零
        // 因为结果是反向构建的（例如 “88065”），前导零会出现在字符串的末尾。这个循环移除结果末尾的'0'
        // 但至少保持一位数字（例如，但结果是'0'时，不能删除）
        while (ret.length() > 1 && ret.charAt(ret.length() - 1) == '0') {
            ret.deleteCharAt(ret.length() - 1);
        }

        return ret.reverse().toString();
    }

    public static void main(String[] args) {
        Demo3 solution = new Demo3();
        // 生成测试用例
        String[][] tests = {
            {"2", "3", "6"},
            {"123", "456", "56088"},
            {"0", "0", "0"},
            {"999", "999", "998001"},
            {"1", "10", "10"}
        };
        for (String[] test : tests) {
            String num1 = test[0], num2 = test[1], expected = test[2];
            String result = solution.multiply(num1, num2);
            System.out.printf("multiply(%s, %s) = %s, expected = %s%n", num1, num2, result, expected);
        }
    }
}
