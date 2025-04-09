import java.util.*;

class Solution1 {
    Set<List<Integer>> ans = new HashSet<>(); // 存放结果(用HashSet除重)
    List<Integer> x = new ArrayList<>(); // 量解向

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums); // nums数组排序
        dfs(nums, 0);
        return new ArrayList<>(ans); // 转换为 ArrayList
    }

    void dfs(int[] nums, int i) { // 回溯算法
        if (i == nums.length) { // 到达一个叶子结点
            ans.add(new ArrayList<>(x)); // 自动除重
        } else {
            x.add(nums[i]); // 选择nums[i], x中添加nums[i]
            dfs(nums, i + 1);
            x.remove(x.size() - 1); // 回溯，从x中删除nums[i]
            dfs(nums, i + 1); // 不选择nums[i],x中不添加nums[i]
        }
    }

}

public class LeetCode90 {
    public static void main(String[] args) {
        Solution1 obj = new Solution1();
        int a[] = {1, 2, 3};
        System.out.println("求解结果");
        obj.subsetsWithDup(a);
        System.out.println(obj.subsetsWithDup(a));
    }
}

