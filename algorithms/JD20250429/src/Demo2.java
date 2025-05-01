import java.util.Stack;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-05-01
 * Time: 9:40
 */
public class Demo2 {
    // 2. 字符串解码
    // https://leetcode.cn/problems/decode-string/description/
    public String decodeString(String _s) {
        Stack<StringBuffer> stack = new Stack<>(); // 字符串栈
        stack.push(new StringBuffer());
        Stack<Integer> nums = new Stack<>(); // 数字栈

        int n = _s.length();
        int i = 0; // 遍历 _s 字符串的索引
        char[] s = _s.toCharArray(); // 将字符串转化为字符数组

        while (i < n) {
            // 1. 遇到数字，将整个数字放到数字栈中
            if (s[i] >= '0' && s[i] <= '9') {
                int tmp = 0; // 用于读取整个数字（类似于 1234）
                while (s[i] >= '0' && s[i] <= '9') {
                    tmp = tmp * 10 + (s[i] - '0');
                    i++;
                }
                nums.push(tmp);
            } else if (s[i] == '[') {
                // 2. 遇到 '['，将拼接好的字符串放到字符串栈中
                i++;
                StringBuffer temp = new StringBuffer();
                while (s[i] >= 'a' && s[i] <= 'z') {
                    temp.append(s[i]);
                    i++;
                }
                stack.push(temp);
            } else if (s[i] == ']') {
                // 3. 遇得 ']'，将拼接好的字符串放到栈顶元素的后面
                // 解析：拿出数字栈的栈顶元素 + 拿出字符串栈的栈顶元素
                // 将字符串进行加倍
                StringBuffer temp = stack.pop();
                int k = nums.pop();
                while (k-- != 0) {
                    stack.peek().append(temp); //
                }
                i++;
            } else {
                // 4. 遇到单独的字符串，直接也是放到栈顶元素后面
                StringBuffer temp = new StringBuffer();
                while (i < n && s[i] >= 'a' && s[i] <= 'z') {
                    temp.append(s[i]);
                    i++;
                }
                stack.peek().append(temp);
            }
        }
        return stack.peek().toString();
    }
}
