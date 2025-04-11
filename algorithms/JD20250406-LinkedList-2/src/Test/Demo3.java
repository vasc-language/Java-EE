package Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
// 这个代码使用了回溯算法结合深度优先搜索(DFS)的方式来生成一个数组的所有可能排列。
public class Demo3 {
    private List<List<Integer>> ans = new ArrayList<>(); // 装全排列的所有的list

    public List<List<Integer>> permute(int[] nums) {
        // 将数组 nums 转化为集合 ArrayList
        List<Integer> a = new ArrayList<>();
        for (int num : nums) {
            a.add(num);
        }
        dfs(a, 0);
        
        return ans;
    }

    // 深度优先遍历
    public void dfs(List<Integer> a, int i) {
        if (i == a.size()) {
            ans.add(new ArrayList<>(a));
        }

        for (int j = i; j < a.size(); j++) {
            // 交换元素
            Collections.swap(a, i, j);
            // 进行回溯
            dfs(a, i + 1);
            Collections.swap(a, i, j);
        }
    }

    public static void main(String[] args) {
        Demo3 demo3 = new Demo3();
        int[] nums = {1, 2, 3};
        System.out.println("求解结果：");
        System.out.println(demo3.permute(nums));;
    }
}
