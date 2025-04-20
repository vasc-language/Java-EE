/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-04-20
 * Time: 9:18
 */
public class Demo2 {
    // 2. 判断是否互为字符重排
    // https://leetcode.cn/problems/check-permutation-lcci/description/
    public boolean CheckPermutation(String s1, String s2) {
        if (s1.length() != s2.length()) return false;

        // 使用数组模拟哈希表
        int[] hash = new int[26]; // 一共26个小写字母
        for (int i = 0; i < s1.length(); i++) {
            // 将字符串s1的每个字符都放到哈希数组中
            hash[s1.charAt(i) - 'a']++;
        }
        // 将字符串s2的每个字符都放到哈希数组中
        // 与s1的字符进行比较，直接使用索引进行消除
        // 最后如果互为字符重合，个数一定为 0
        for (int i = 0; i < s2.length(); i++) {
            hash[s2.charAt(i) - 'a']--;
            if (hash[s2.charAt(i) - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Demo2 demo = new Demo2();
        // 示例1
        String s1 = "abc", s2 = "bca";
        System.out.println(demo.CheckPermutation(s1, s2)); // 输出: true

        // 示例2
        String s3 = "abc", s4 = "bad";
        System.out.println(demo.CheckPermutation(s3, s4)); // 输出: false
    }
}
