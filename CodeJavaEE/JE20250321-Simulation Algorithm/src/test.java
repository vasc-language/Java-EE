/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-03-21
 * Time: 20:59
 */
public class test {
    // 5. 外观数列
    // https://leetcode.cn/problems/count-and-say/description/
    public String countAndSay(int n) {
        String ret = "1"; // 字符串
        for (int i = 1; i < n; i++) {
            StringBuilder tmp = new StringBuilder();
            int len = ret.length();
            for (int left = 0, right = 0; right < len; ) {
                while (right < len && ret.charAt(left) == ret.charAt(right)) {
                    right++; // 相同的就向前走
                }
                tmp.append(Integer.toString(right - left)); // 将 int 转化为 String 类型拼接到 tmp 中
                tmp.append(ret.charAt(left));
                left = right;
            }
            ret = tmp.toString();
        }
        return ret;
    }
    // 4. 模拟：提莫攻击
    // https://leetcode.cn/problems/teemo-attacking/description/
    public int findPoisonedDuration(int[] timeSeries, int duration) {
        int ret = 0;
        for (int i = 1; i < timeSeries.length; i++) {
            int x = timeSeries[i] - timeSeries[i - 1]; // 每个元素间的时间间隔
            if (x >= duration) {
                ret += duration;
            } else {
                ret += x;
            }
        }
        return duration + ret;
    }

    // 3. 模拟：Z字形变换
    // https://leetcode.cn/problems/zigzag-conversion/
    public String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        int n = s.length();
        int d = 2 * numRows - 2; // 每行中每个元素间的公差
        StringBuilder ret = new StringBuilder();
        // 处理第一行（i = i + d）
        for (int i = 0; i < n; i += d) {
            ret.append(s.charAt(i));
        }
        // 处理中间行k: (k, d-k) (k+d, d-k+d) (k+2d, d-k+2d)
        for (int k = 1; k < numRows - 1; k++) {
            // 注意 j = d - i 而不是 j = d - k
            for (int i = k, j = d - i; i < n || j < n; i += d, j += d) {
                if (i < n) {
                    ret.append(s.charAt(i));
                }
                if (j < n) {
                    ret.append(s.charAt(j));
                }
            }
        }
        // 处理最后一行 n-1: (n-1) (n-1+d) (n-1+2d)
        for (int i = numRows - 1; i < n; i += d) {
            ret.append(s.charAt(i));
        }
        return ret.toString();
    }

    // 模拟：2. 数青蛙
    // https://leetcode.cn/problems/minimum-number-of-frogs-croaking/
    public int minNumberOfFrogs1(String croakOfFrogs) {
        // 我们用 count 数组追踪每个阶段正在“呱”叫的青蛙数量：
        // count[0] 正在发出 'c'
        // count[1] 正在发出 'r'
        // count[2] 正在发出 'o'
        // count[3] 正在发出 'a'
        // count[4] 正在发出 'k'
        int[] count = new int[5];
        int frogs = 0; // 当前同时发声(还在叫"croak")的青蛙数
        int maxFrags = 0; // 整个过程需要的最多同时发声的青蛙数
        for (char ch : croakOfFrogs.toCharArray()) {
            // 字符 不合法
            // 字符遇到了 'c'
            // 字符遇到除 'c' 以外的
            int index = getIndex(ch);
            if (index == -1) {
                // 字符不合法
                return -1;
            }
            if (index == 0) {
                // 遇到了 'c'
                count[0]++; // 在 'c' 下标中填一个
                frogs++; // 青蛙个数 +1
                maxFrags = Math.max(frogs, maxFrags);
            } else {
                // 字符遇到除 'c' 以外的
                // 'r' -> 1 'o' -> 2 'a' -> 3 'k' -> 4
                if (count[index - 1] == 0) {
                    // 证明前一个字符没有叫
                    return -1;
                }
                // 除 'c' 以外的字符不断地向前移动
                count[index - 1]--;
                count[index]++;
                if (ch == 'k') {
                    // 证明有蛙完成了一次蛙叫
                    frogs--;
                }
            }
        }

        // 最后，确保没有青蛙卡在中间半路（如果 count[0..3] 不是全 0，则说明有青蛙没唱完一整套）
        for (int i = 0; i < 4; i++) {
            if (count[i] != 0) {
                return -1;
            }
        }
        return maxFrags;
    }

    public int minNumberOfFrogs(String croakOfFrogs) {
        // count[0] == 'c'
        // count[4] == 'k'
        int[] count = new int[5];
        int frogs = 0; // 青蛙个数
        int maxFrogs = 0; // 青蛙个数最大值

        for (char ch : croakOfFrogs.toCharArray()) {
            int index = getIndex(ch);
            // 1. 字符不合法
            if (index == -1) {
                return -1;
            }

            // 2. 字符为 'c'
            if (index == 0) {
                count[0]++;
                frogs++;
                maxFrogs = Math.max(frogs, maxFrogs);
            } else {
                // 3. 字符为不为 'c' 的其他字符
                if (count[index - 1] == 0) {
                    // 字符前面没有 叫
                    // 得是 前面有一个字符，现字符才能叫 ，顺序也得对（roak）
                    return -1;
                }
                count[index - 1]--;
                count[index]++;

                // 证明有青蛙，已经叫完一遍 "croak"
                if (ch == 'k') {
                    frogs--;
                }
            }
        }
        for (int i = 0; i < 4; i++) {
            if (count[i] != 0) {
                // 证明 有青蛙没叫完 "croak"
                return -1;
            }
        }
        return maxFrogs;
    }

    // 将角色符号 char 类型转换成数字int
    // croak 'c' -> 0 'r' -> 1 'o' -> 2 'a' -> 3 'k' -> 4
    private int getIndex(char ch) {
        switch (ch) {
            case 'c':
                return 0;
            case 'r':
                return 1;
            case 'o':
                return 2;
            case 'a':
                return 3;
            case 'k':
                return 4;
            default:
                return -1;
        }
    }

    public static void main(String[] args) {
        test solution = new test();
        System.out.println("示例 1 输出：" + solution.minNumberOfFrogs("croakcroak"));   // 应输出 1
        System.out.println("示例 2 输出：" + solution.minNumberOfFrogs("crcoakroak"));   // 应输出 2
        System.out.println("示例 3 输出：" + solution.minNumberOfFrogs("croakcrook"));   // 应输出 -1
    }

    // 位运算：1. 只出现一次的数字II
    // https://leetcode.cn/problems/single-number-ii/
    public static int singleNumber(int[] nums) {
        int ones = 0, twos = 0;  // ones 保存出现次数 mod 3 为 1 的位，twos 保存出现次数 mod 3 为 2 的位
        for (int num : nums) {
            // 当某个位在 ones 中出现时，再出现会加入 twos，但如果该位已经在 twos 中，则清除 ones
            ones = (ones ^ num) & ~twos;
            // 类似地，更新 twos；若该位已经在 ones 中，则清除 twos
            twos = (twos ^ num) & ~ones;
        }
        return ones;
    }

    // 测试代码
    public static void main1(String[] args) {
        int[] nums1 = {2, 2, 3, 2};
        System.out.println("输出: " + singleNumber(nums1));  // 应输出 3
        int[] nums2 = {0, 1, 0, 1, 0, 1, 99};
        System.out.println("输出: " + singleNumber(nums2));  // 应输出 99
    }
}

class Solution {
    public String convert(String s, int numRows) {
        if (numRows == 1)
            return s;
        int d = 2 * numRows - 2, n = s.length();
        StringBuilder ret = new StringBuilder();
        // 1. 处理第一行
        for (int i = 0; i < n; i += d)
            ret.append(s.charAt(i));

        // 2. 处理中间行
        for (int k = 1; k < numRows - 1; k++) // 依次枚举中间行
        {
            for (int i = k, j = d - i; i < n || j < n; i += d, j += d) {
                if (i < n)
                    ret.append(s.charAt(i));
                if (j < n)
                    ret.append(s.charAt(j));
            }
        }

        // 3. 处理最后一⾏
        for (int i = numRows - 1; i < n; i += d)
            ret.append(s.charAt(i));
        return ret.toString();
    }
}

class Solution2 {
    public String countAndSay(int n) {
        String ret = "1";
        for (int i = 1; i < n; i++) // 解释 n - 1 次 ret 即可
        {
            StringBuilder tmp = new StringBuilder();
            int len = ret.length();
            for (int left = 0, right = 0; right < len; ) {
                while (right < len && ret.charAt(left) == ret.charAt(right))
                    right++;
                tmp.append(Integer.toString(right - left));
                tmp.append(ret.charAt(left));
                left = right;
            }
            ret = tmp.toString();
        }
        return ret;
    }
}

