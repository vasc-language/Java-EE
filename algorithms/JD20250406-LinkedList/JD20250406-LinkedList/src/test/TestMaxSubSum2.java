package test;
// 求一个最大的连续子序列

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestMaxSubSum2 {
    // dp[i] 代表以第 i 个元素结尾的最大子序列的和
    private int[] dp; // 动态规划数组

    public List<Integer> subSumList(int[] a) {
        int n = a.length;
        List<Integer> x = new ArrayList<>();
        // 遍历 dp 数组找到其中的最大值和索引
        int maxi = 0;
        for (int i = 0; i < n; i++) {
            if (a[i] > a[maxi]) {
                maxi = i;
            }
        }
        // 反向寻找起始位置，每次都减掉a[i]
        int rsum = a[maxi];
        int i = maxi;
        while (i >= 0 && rsum != 0) {
            rsum -= a[i];
            x.add(a[i]);
            i--;
        }
        // 进行逆序
        // 由于我们是从后面向前构建子序列。所以最后需要将结果翻转，得到从左到右的正确顺序
        Collections.reverse(x);
        return x;
    }

    // 简单测试 main 方法
    public static void main(String[] args) {
        TestMaxSubSum2 tester = new TestMaxSubSum2();
        
        // 测试示例1: 全部正数
        int[] arr1 = {1, 2, 3, 4, 5};
        System.out.println("输入: " + java.util.Arrays.toString(arr1));
        System.out.println("输出子序列: " + tester.subSumList(arr1));
        System.out.println();
        
        // 测试示例2: 负数和正数混合
        int[] arr2 = {-2, 11, -4, 13, -5, -2};
        System.out.println("输入: " + java.util.Arrays.toString(arr2));
        System.out.println("输出子序列: " + tester.subSumList(arr2));
    }
}
