package Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// 相比于Demo3 多实现一个去重操作
public class Demo4 {
    private List<List<Integer>> ans = new ArrayList<>();
    private List<Integer> a = new ArrayList<>();

    // 让 数组 nums 转化成 ArrayList 
    public List<List<Integer>> permuteUnique(int[] nums) {
        for(int num: nums) {
            a.add(num);
        }
        dfs(nums.length, 0);

        return ans;
    }

    public void dfs(int n, int i) {
        // 递归终止条件，当前位置以达到列表长度
        if (i >= n) {
            ans.add(new ArrayList<>(a));
        } else {
            // 从当前元素 [i, n - 1] 开始固定，a 中后面元素进行交换
            for (int j = i; j < n; j++) {
                if (judge(i, j)) {
                    Collections.swap(a, i, j);  
                    // 进行回溯
                    dfs(n, i + 1);
                    // 还原
                    Collections.swap(a, i, j);
                }
            }
        }
    }

    // 判断二者元素是否需要交换
    private boolean judge(int i, int j) {
        // 检查从位置i到j-1之间是否有和a[j]相同的元素
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
        Demo4 obj = new Demo4();
        int[] a = {1,1,2};
        System.out.println("求解结果");
        System.out.println( obj.permuteUnique(a));
    }
}
