import java.util.Stack;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-05-01
 * Time: 10:12
 */
public class Demo3 {
    public String decodeString(String _s) {
        // 先申请一个栈空间
        Stack<StringBuffer> stringStack = new Stack<>(); // 这个栈存放字符串
        stringStack.push(new StringBuffer()); // 先丢一个空字符串进去
        Stack<Integer> intStack = new Stack<>(); // 这个栈存放数字

        int i = 0; // 用于遍历 _s 的索引
        int n = _s.length();

        // 将字符串转化为字符数组
        char[] s = _s.toCharArray();

        while (i < n) {
            // 第一种情况：遇到数字，将整个数字放到数字栈中，不排除数字很大
            if (s[i] >= '0' && s[i] <= '9') {
                int temp = 0;
                while (s[i] >= '0' && s[i] <= '9') {
                    temp = temp * 10 + (s[i] - '0');
                    i++;
                }
                intStack.push(temp);
            } else if (s[i] == '[') {
                // 第二种情况：遇到 '[' ，将左括号后面的字符串拼接起来，放到字符串栈中
                i++; // 跳过左括号
                StringBuffer temp = new StringBuffer();
                while (s[i] >= 'a' && s[i] <= 'z') {
                    temp.append(s[i]);
                    i++;
                }
                stringStack.push(temp);
            } else if (s[i] == ']') {
                // 第三种情况：遇到 ']' ,将右括号前面的字符串和数字进行解析，放到字符串栈中
                // abcabc3[cd]
                StringBuffer temp = stringStack.pop(); // 将栈顶元素拿出来（把 "cd" 拿出来了）
                int k = intStack.pop(); // 将栈顶元素拿出来（把 3 拿出来了）
                while (k-- != 0) {
                    // 1. stringStack.peek()：将 "abcabc" 呈现出来
                    // append(temp)：将 "cd" 拼接到 "abcabc" 后面，重复 k = 2 遍
                    stringStack.peek().append(temp); // 变成 "abcabccdcdcd"
                }
                i++;
            } else {
                // 第四种情况：遇到单独的字符串，将字符串拼接好，放到字符串栈的栈顶元素后面
                StringBuffer temp = new StringBuffer();
                while (i < n && s[i] >= 'a' && s[i] <= 'z') {
                    temp.append(s[i]);
                    i++;
                }
                stringStack.peek().append(temp);
            }
        }
        return stringStack.peek().toString();
    }
}
