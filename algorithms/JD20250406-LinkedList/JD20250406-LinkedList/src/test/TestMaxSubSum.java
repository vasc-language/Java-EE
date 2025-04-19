package test;
// 求最大连续子序列和
public class TestMaxSubSum {
    private int[] dp;

    public int maxSubSum(int a[]) {    
        int n = a.length;
        dp = new int[n];
        dp[0] = a[0];
        for (int i = 1; i < n; i++) {
          dp[i] = Math.max(dp[i - 1] + a[i], a[i]);
        }

        int ans = dp[0];

        for (int i = 1; i < n; i++) {
          ans = Math.max(ans, dp[i]);
        }
        return Math.max(ans, 0);
    }

    // 优化
    public int maxSubSum1(int[] a) {
      int n = a.length;
      int dp = 0;
      int ans = dp; // 每一个以i元素结尾的最大连续子序列(dp[0]、dp[1]、dp[2]……)

      if (n == 1) {
        return a[0];
      }
      // dp[i]：表示以第i个元素结尾的最大连续子序列
      // 状态转移方程：dp[i] = max(dp[i - 1] + a[i], a[i])
      dp = a[0]; // 从0开始
      for (int i = 0; i < n; i++) {
        dp = Math.max(dp + a[i], a[i]);
        ans = Math.max(ans, dp);
      }
      
      return Math.max(ans, 0);
    }
    

    public static void main(String[] args) {
      TestMaxSubSum solution = new TestMaxSubSum();
        
        // 测试用例1: 普通数组，包含正负数
        int[] arr1 = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int expectedResult1 = 6; // 子数组 [4, -1, 2, 1] 的和
        int result1 = solution.maxSubSum1(arr1);
        System.out.println("测试用例1:");
        System.out.println("输入: [-2, 1, -3, 4, -1, 2, 1, -5, 4]");
        System.out.println("预期输出: " + expectedResult1);
        System.out.println("实际输出: " + result1);
        System.out.println("测试结果: " + (result1 == expectedResult1 ? "通过" : "失败"));
        System.out.println();
    }
    public static void main1(String[] args) {
        TestMaxSubSum solution = new TestMaxSubSum();
        
        // 测试用例1: 普通数组，包含正负数
        int[] arr1 = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int expectedResult1 = 6; // 子数组 [4, -1, 2, 1] 的和
        int result1 = solution.maxSubSum(arr1);
        System.out.println("测试用例1:");
        System.out.println("输入: [-2, 1, -3, 4, -1, 2, 1, -5, 4]");
        System.out.println("预期输出: " + expectedResult1);
        System.out.println("实际输出: " + result1);
        System.out.println("测试结果: " + (result1 == expectedResult1 ? "通过" : "失败"));
        System.out.println();
        
        // 测试用例2: 全为负数的数组
        int[] arr2 = {-1, -2, -3, -4, -5};
        int expectedResult2 = 0; // 因为所有子数组和都是负数，所以返回0
        int result2 = solution.maxSubSum(arr2);
        System.out.println("测试用例2:");
        System.out.println("输入: [-1, -2, -3, -4, -5]");
        System.out.println("预期输出: " + expectedResult2);
        System.out.println("实际输出: " + result2);
        System.out.println("测试结果: " + (result2 == expectedResult2 ? "通过" : "失败"));
        System.out.println();
        
        // 测试用例3: 全为正数的数组
        int[] arr3 = {1, 2, 3, 4, 5};
        int expectedResult3 = 15; // 整个数组的和
        int result3 = solution.maxSubSum(arr3);
        System.out.println("测试用例3:");
        System.out.println("输入: [1, 2, 3, 4, 5]");
        System.out.println("预期输出: " + expectedResult3);
        System.out.println("实际输出: " + result3);
        System.out.println("测试结果: " + (result3 == expectedResult3 ? "通过" : "失败"));
        System.out.println();
        
        // 测试用例4: 单一元素数组
        int[] arr4 = {5};
        int expectedResult4 = 5;
        int result4 = solution.maxSubSum(arr4);
        System.out.println("测试用例4:");
        System.out.println("输入: [5]");
        System.out.println("预期输出: " + expectedResult4);
        System.out.println("实际输出: " + result4);
        System.out.println("测试结果: " + (result4 == expectedResult4 ? "通过" : "失败"));
        System.out.println();
        
        // 测试用例5: 复杂情况
        int[] arr5 = {-2, -3, 4, -1, -2, 1, 5, -3};
        int expectedResult5 = 7; // 子数组 [4, -1, -2, 1, 5] 的和
        int result5 = solution.maxSubSum(arr5);
        System.out.println("测试用例5:");
        System.out.println("输入: [-2, -3, 4, -1, -2, 1, 5, -3]");
        System.out.println("预期输出: " + expectedResult5);
        System.out.println("实际输出: " + result5);
        System.out.println("测试结果: " + (result5 == expectedResult5 ? "通过" : "失败"));
        
        // 测试结果汇总
        System.out.println("\n===== 测试结果汇总 =====");
        int passCount = 0;
        passCount += (result1 == expectedResult1) ? 1 : 0;
        passCount += (result2 == expectedResult2) ? 1 : 0;
        passCount += (result3 == expectedResult3) ? 1 : 0;
        passCount += (result4 == expectedResult4) ? 1 : 0;
        passCount += (result5 == expectedResult5) ? 1 : 0;
        System.out.println("通过: " + passCount + "/5");
    }
}
