class Solution2 {
    int ans; // 存放解个数

    public int findTargetSumWays(int[] nums, int target) {
        ans = 0;
        dfs(nums, target, 0, 0);
        return ans;
    }

    void dfs(int[] nums, int target, int i, int expv) {
        if (i == nums.length) { // 到达一个叶子结点
            if (expv == target) // 找到一个解
                ans++;
        } else {
            expv += nums[i]; // nums[i]前选择'+'
            dfs(nums, target, i + 1, expv);
            expv -= nums[i]; // 回溯：恢复expv
            expv -= nums[i]; // nums[i]前选择'-'
            dfs(nums, target, i + 1, expv);
            expv += nums[i]; // 回溯：恢复expv
        }
    }
}

public class LeetCode494 {
    public static void main(String[] args) {
        Solution2 obj = new Solution2();
        int a[] = {1, 2, 3};
        int t = -4;
        System.out.println("求解结果");
        obj.findTargetSumWays(a, t);
        System.out.println(obj.findTargetSumWays(a, t));
    }
}

