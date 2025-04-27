package Test2;

/**
 * Created with IntelliJ IDEA.
 * Description: 栈
 * User: 姚东名
 * Date: 2025-04-27
 * Time: 7:18
 */
public class Demo4 {
    // 3. 删除字符串中所有相邻的重复项
    // https://leetcode.cn/problems/remove-all-adjacent-duplicates-in-string/description/
    public String removeDuplicates(String s) {
        StringBuffer ret = new StringBuffer(); // 拼接字符串
        // 将字符串转化为字符数组
        char[] ch = s.toCharArray();

        for (char c : ch) {
            if (ret.length() > 0 && ret.charAt(ret.length() - 1) == c) {
                // 使用删除字符串最后一个字符作为出栈
                ret.deleteCharAt(ret.length() - 1);
            } else {
                // 使用字符串拼接作为入栈
                ret.append(c);
            }
        }

        return ret.toString();
    }
}
