package test;

public class TestMaxSubSum3 {
    // dp[i] 表示第 i 个元素结尾的最大子序列
    private int[] dp; // 动态规划数组

    // 1. 求最大连续子序列的和
    public int maxSubSum(int[] a) {
        int n = a.length;
        dp = new int[n]; // 初始化动态规划数组的大小
        dp[0] = a[0];
        for (int i = 0; i < n; i++) {
            // 动态转移方程
            dp[i] = Math.max(dp[i - 1] + a[i], a[i]);
        }

        // 找到最大连续子序列的和
        int ans = dp[0];
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, dp[i]);
        }
        return Math.max(ans, 0);
    }
}
