package Test2;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-04-27
 * Time: 7:34
 */
public class Demo5 {
    // 4. 比较含退格的字符串
    // https://leetcode.cn/problems/backspace-string-compare/description/
    public boolean backspaceCompare(String s, String t) {
        return changeStr(s).equals(changeStr(t));
    }

    private String changeStr(String s) {
        // 用数组模拟栈结构
        StringBuffer ret = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            // 出栈(栈顶元素)
            if (ch == '#') {
                if (ret.length() > 0) {
                    ret.deleteCharAt(ret.length() - 1);
                }
            } else {
                // 入栈
                ret.append(ch);
            }
        }
        return ret.toString();
    }
}
