package Test2;

import java.util.Stack;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-04-27
 * Time: 8:51
 */
public class Demo7 {
    // 5. 字符串解码
    // https://leetcode.cn/problems/decode-string/description/
    public String decodeString(String _s) {
        Stack<StringBuffer> stack = new Stack<>();
        // 先放一个空字符串到字符串栈中
        stack.push(new StringBuffer());
        // 再申请一个数字栈，存放倍数的
        Stack<Integer> nums = new Stack<>();

        // 用于遍历字符数组的下标
        int i = 0, n = _s.length();
        // 将字符串转化为字符数组
        char[] s = _s.toCharArray();

        while (i < n) {
            // 遇到数字，将其放到数字栈中
            if (s[i] >= '0' && s[i] <= '9') {
                int tmp = 0;
                while (i < n && s[i] >= '0' && s[i] <= '9') {
                    tmp = tmp * 10 + (s[i] - '0');
                    i++;
                }
                nums.push(tmp);
            } else if (s[i] == '[') {
                // 遇到 '[' 时，把后面的拼接好的字符串放到字符串栈中
                i++;
                StringBuffer ret = new StringBuffer();
                while (i < n && s[i] >= 'a' && s[i] <= 'z') {
                    ret.append(s[i]);
                    i++;
                }
                stack.push(ret);
            } else if (s[i] == ']') {
                // 遇到 ']' 时，放到字符串栈的栈顶元素的后面
                StringBuffer tmp = stack.pop();
                int k = nums.pop();
                while (k-- != 0) {
                    stack.peek().append(tmp);
                }
                i++;
            } else {
                // 遇到单独的字符时，提取这个拼接好的字符串，直接放到字符串栈的栈顶元素的后面
                StringBuffer tmp = new StringBuffer();
                while (i < n && s[i] >= 'a' && s[i] <= 'z') {
                    tmp.append(s[i]);
                    i++;
                }
                stack.peek().append(tmp);
            }
        }
        return stack.peek().toString();
    }
}
