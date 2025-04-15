import java.util.*;

class Solution2 {
    private int dp[];

    public int maxSubSum(int a[]) {    //求最大连续子序列和
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

    public ArrayList<Integer> maxSub(int a[]) {    //求一个最大连续子序列
        int n = a.length;
        ArrayList<Integer> x = new ArrayList<>();
        int maxi = 0;
        for (int i = 1; i < n; i++) {
            if (dp[i] > dp[maxi])
                maxi = i;
        }
        int rsum = dp[maxi];
        int i = maxi;
        while (i >= 0 && rsum != 0) {
            rsum -= a[i];
            x.add(a[i]);
            i--;
        }
        Collections.reverse(x);
        return x;
    }

    
    public void solve(int a[]) {        //输出结果
        int ans = maxSubSum(a);
        System.out.printf("求解结果\n");
        System.out.printf("  最大连续子序列和: %d\n", ans);
        if (ans == 0)
            System.out.printf("  所选子序列为空\n");
        else {
            System.out.println("  dp: " + Arrays.toString(dp));
            ArrayList<Integer> x = maxSub(a);
            System.out.println("  所选子序列: " + x.toString());
        }
    }

  public int maxSubSum1(int a[]) {        //求最大连续子序列和
        int n = a.length;
        if (n == 1) return a[0];
        int dp;
        dp = a[0];
        int ans = dp;
        for (int j = 1; j < n; j++) {
            dp = Math.max(dp + a[j], a[j]);
            ans = Math.max(ans, dp);
        }
        return Math.max(ans, 0);
    }

  public void solve1(int a[]) {        //输出结果
        int ans = maxSubSum1(a);
        System.out.printf("求解结果\n");
        System.out.printf("  最大连续子序列和: %d\n", ans);
    }
}

public class maxSubSum {
    public static void main(String[] args) {
        Solution2 obj = new Solution2();
        int[] a = {-2, 11, -4, 13, -5, -2};
        //int a[]={-1,3,-2,4};
        System.out.print("基本解法：");
        obj.solve(a);
        System.out.print("空间优化：");
        obj.solve1(a);
    }
}