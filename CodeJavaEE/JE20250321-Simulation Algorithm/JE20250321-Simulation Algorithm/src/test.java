/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-03-21
 * Time: 20:59
 */
public class test {
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
        // 特殊情况：如果只有一行，则直接返回原字符串
        if (numRows == 1) return s;

        // 初始化一个 StringBuilder 列表，用于存储每一行的字符
        StringBuilder[] rows = new StringBuilder[Math.min(numRows, s.length())];
        for (int i = 0; i < rows.length; i++) {
            rows[i] = new StringBuilder();
        }

        int curRow = 0;
        boolean goingDown = false;  // 用于判断当前遍历方向

        // 遍历字符串中的每个字符
        for (char c : s.toCharArray()) {
            // 将字符添加到当前行里
            rows[curRow].append(c);

            // 当处于第一行或最后一行时，反转方向
            if (curRow == 0 || curRow == numRows - 1) {
                goingDown = !goingDown;
            }

            // 根据当前方向更新行索引
            curRow += goingDown ? 1 : -1;
        }

        // 合并所有行的结果
        StringBuilder ret = new StringBuilder();
        for (StringBuilder row : rows) {
            ret.append(row);
        }

        return ret.toString();
    }
}

