/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-04-20
 * Time: 11:51
 */
public class Demo6 {
    // 7. 最长回文字串
    // https://leetcode.cn/problems/longest-palindromic-substring/description/
    public String longestPalindrome(String s) {
        /**
         * 回文扩展过程
         * 每次扩展时，left 和 right 都会在最后一次循环多走一步
         * - left-- 使得 left 指向了回文前边界的前一个位置
         * - right++ 使得 right 指向了回文右边界的后一个位置
         * 区间：[left+1, right-1]
         */
        int begin = 0, len = 0, n = s.length();
        for (int i = 0; i < n; i++) {
            // 定义两个指针
            int left = i, right = i;
            // 先做长度为奇数的扩展
            while (left >= 0 && right < n && s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
            }
            if (right - left - 1 > len) {
                begin = left + 1;
                // 区间：[left+1, right-1]
                len = right - left - 1; // (right - 1) - (left + 1) + 1
            }

            // 再做长度为偶数的扩展
            left = i;
            right = i + 1;
            while (left >= 0 && right < n && s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
            }
            if (right - left - 1 > len) {
                begin = left + 1;
                len = right - left - 1;
            }
        }

        // return s.substring(begin, begin + len);
        return s.substring(begin, begin + len);
    }

    public static void main(String[] args) {
        Demo6 demo = new Demo6();

        String s1 = "babad";
        System.out.println("输入: " + s1 + "，输出: " + demo.longestPalindrome(s1));

        String s2 = "cbbd";
        System.out.println("输入: " + s2 + "，输出: " + demo.longestPalindrome(s2));

        String s3 = "a";
        System.out.println("输入: " + s3 + "，输出: " + demo.longestPalindrome(s3));

        String s4 = "ac";
        System.out.println("输入: " + s4 + "，输出: " + demo.longestPalindrome(s4));
    }
}
