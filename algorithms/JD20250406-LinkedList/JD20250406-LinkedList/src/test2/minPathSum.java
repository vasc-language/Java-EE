package test2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class minPathSum {
    private int[][] dp;
    // 最小路径和
    public int minPathSum(int[][] a) {
        // 定义一个动态规划数组
        // dp[i][j] 表示 第i行第j列
        int n = a.length;
        dp = new int[n][n];
        // 初始化 dp
        dp[0][0] = a[0][0];
        // 考虑第 0 列的边界
        for (int i = 1; i < n; i++) {
            dp[i][0] = dp[i - 1][0] + a[i][0];
        }
        // 考虑对角线的边界
        for (int i = 1; i < n; i++) {
            dp[i][i] = dp[i - 1][i - 1] + a[i][i];
        }

        for (int i = 2; i < n; i++) {
            for (int j = 1; j < i; j++) {
                dp[i][j] = Math.min(dp[i - 1][j - 1], dp[i - 1][j]) + a[i][j];
            }
        }

        // 求出最小的ans
        int ans = dp[n - 1][0];
        for (int i = 0; i < n; i++) {
            ans = Math.min(ans, dp[n - 1][i]);
        }
        return ans;
    }

    // 一条最小路径
    public void minPathSum1(int[][] a) {
        int n = a.length;
        // 二位路径数组
        int[][] pre = new int[n][n];
        dp[0][0] = a[0][0]; // 第0行
        // 行数是不需要考虑的，因为倒着往回退 就是前面一行肯定是 i - 1
        // 所以只需要考虑前一个最近相邻节点的列
        // 考虑第0列的边界
        for (int i = 1; i < n; i++) { // 从第一行开始
            pre[i][0] = i - 1;
        }
        // 考虑对角线的边界
        for (int i = 1; i < n; i++) {
            pre[i][i] = i - 1;
        }

        // 考虑其他有两条到达路径的的节点
        for (int i = 2; i < n; i++) {
            for (int j = 1; j < i; j++) {
                if (dp[i - 1][j - 1] < dp[i - 1][j]) {
                    pre[i][j] = j - 1;
                } else {
                    pre[i][j] = j;
                }
            }
        }

        int ans = dp[n - 1][0]; // 初始化最小路径和对应的列数 minJ
        int minI = 0;
        for (int i = 0; i < n; i++) {
            if (ans > dp[n - 1][i]) {
                ans = dp[n - 1][i];
                minI = i;
            }
        }

        System.out.printf("  最小路径和ans=%d\n",ans);
        // 倒着往回退
        int i = n - 1;
        List<Integer> path = new ArrayList<>();
        while (i >= 0) {
            path.add(a[i][minI]);
            minI = pre[i][minI];
            i--;
        }

        Collections.reverse(path);
        System.out.printf("  一条最小路径: "+path.toString());
    }

    public static void main(String[] args) {
        minPathSum obj=new minPathSum();
        
        // 测试用例1: 原有三角形矩阵
        System.out.println("===== 测试用例1 =====");
        int a[][]={{2},{3,4},{6,5,7},{8,3,9,2}};
        System.out.println("输入矩阵:");
        printMatrix(a);
        
        // 先调用minPathSum计算最小路径和并初始化dp数组
        int result = obj.minPathSum(a);
        System.out.println("最小路径和: " + result);
        
        // 调用minPathSum1找到并打印一条最小路径
        System.out.println("路径详情:");
        obj.minPathSum1(a);
        System.out.println("\n");
        
        // 测试用例2: 另一个矩阵示例
        System.out.println("===== 测试用例2 =====");
        int b[][]={{1},{2,3},{4,5,6},{7,8,9,10}};
        System.out.println("输入矩阵:");
        printMatrix(b);
        
        // 先调用minPathSum计算最小路径和并初始化dp数组
        result = obj.minPathSum(b);
        System.out.println("最小路径和: " + result);
        
        // 调用minPathSum1找到并打印一条最小路径
        System.out.println("路径详情:");
        obj.minPathSum1(b);
    }
    
    // 辅助方法：打印三角形矩阵
    private static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
