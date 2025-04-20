/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-04-20
 * Time: 11:17
 */
public class Demo5 {
    // 6. 最长公共前缀
    // https://leetcode.cn/problems/longest-common-prefix/description/
    public String longestCommonPrefix(String[] strs) {
        // 这层是拿出 str[0] 字符串数组中的第一个字符串
        // 对其(strs[0])的字符进行遍历
        for (int i = 0; i < strs[0].length(); i++) {
            char tmp = strs[0].charAt(i);
            // 统一比较
            //
            for (int j = 1; j < strs.length; j++) {
                /**
                 * 1. i == strs[j].length()
                 * 判断当前比较的位置 i，是否已经到达了第 j 个字符串的末尾
                 * 如果到达了末尾，最长只能是 substring(0, j) 
                 * 
                 * 2. strs[j].charAt(i) != tmp
                 * 判断第 j 个字符串在第 i 个字符上，是否和第一个字符串的第 i 个字符相同
                 * 统一判断
                 */
                if (i == strs[j].length() || strs[j].charAt(i) != tmp) {
                    // substring(0, i) 从 0 到 i 进行字符串截断
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0];
    }

    public static void main(String[] args) {
        Demo5 demo = new Demo5();
        // 示例 1
        String[] strs1 = {"flower", "flow", "flight"};
        System.out.println("示例1输出: " + demo.longestCommonPrefix(strs1)); // 应输出 "fl"
        // 示例 2
        String[] strs2 = {"dog", "racecar", "car"};
        System.out.println("示例2输出: " + demo.longestCommonPrefix(strs2)); // 应输出 ""
    }
}
