package Test2;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-04-27
 * Time: 8:10
 */
public class Demo6 {
    // 基本计算器II
    // https://leetcode.cn/problems/basic-calculator-ii/description/
    public int calculate(String _s) {
        // 申请一个栈空间
        Deque<Integer> queue = new ArrayDeque<>();
        // 操作符 加减乘除
        char op = '+';
        int i = 0, n = _s.length();
        // 将字符串转化为字符数组
        char[] s = _s.toCharArray();

        // 处理字符串
        while (i < n) {
            // 处理空格
            if (s[i] == ' ') {
                i++;
            } else if (s[i] >= '0' && s[i] <= '9') {
                // 处理字符的加减乘除
                int tmp = 0;
                while (i < n && s[i] >= '0' && s[i] <= '9') {
                    // 处理多位数（例如 123）
                    tmp = tmp * 10 + (s[i] - '0');
                    i++;
                }
                if (op == '+') {
                    // 直接放到栈中
                    queue.push(tmp);
                } else if (op == '-') {
                    // 直接放到栈中
                    queue.push(-tmp);
                } else if (op == '*') {
                    // 直接乘到栈顶元素
                    queue.push(queue.pop() * tmp);
                } else {
                    // 直接除到栈顶元素
                    queue.push(queue.pop() / tmp);
                }
            } else {
                // 处理操作符
                op = s[i];
                i++;
            }
        }

        // 将栈中元素全都加起来，即为结果
        int ret = 0;
        while (!queue.isEmpty()) {
            ret += queue.pop();
        }

        return ret;
    }
}
