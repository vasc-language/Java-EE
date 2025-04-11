package Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Demo5 {
    private List<List<Integer>> ans = new ArrayList<>(); // 存放全排列的 list
    List<Integer> a = new ArrayList<>();
    // 1. 将 nums 转化为 list
    public List<List<Integer>> permuteUnique(int[] nums) {
        for (int num: nums) {
            a.add(num);
        }
        dfs(nums.length, 0);
        return ans;
    }

    private void dfs(int n, int i) {
        if (i >= n) {
            ans.add(new ArrayList<>(a));
        } else {
            // 固定前面的数，从[j, n - 1] 中遍历
            for (int j = i; j < n; j++) {
                if (judge(i, j)) {
                    Collections.swap(a, i, j);
                    dfs(n, i + 1);
                    Collections.swap(a, i, j);
                }

            }
        }
    }

    private boolean judge(int i, int j) {
        // x[j] 是否在 [i, n-1]之前出现过
        if (j > i) {
            for (int k = i; k < j; k++) {
                if (a.get(k).equals(a.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Demo5 obj = new Demo5();
        int a[]={1,1,2};
        System.out.println("求解结果");
        System.out.println( obj.permuteUnique(a));
    }

}

