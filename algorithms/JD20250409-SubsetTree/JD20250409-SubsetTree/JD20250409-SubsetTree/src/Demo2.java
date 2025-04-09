import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-04-09
 * Time: 20:43
 */
public class Demo2 {
    // 存放解向量，进行去重操作
    Set<List<Integer>> ans = new HashSet<>();
    List<Integer> x = new ArrayList<>(); // 解向量，存放一条路径

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums); // 将相同的节点放到一起，方便 HashSet 进行去重
        DFS(nums, 0); // 进行深度递归，找解向量
        return new ArrayList<>(ans); // 转化为 ArrayList
    }

    private void DFS(int[] nums, int i) {
        if (i == nums.length) {
            // 将这条路径加到 ans 中
            ans.add(new ArrayList<>(x));
        } else {
            // 将当前元素 nums[i] 添加到解向量 x 中
            x.add(nums[i]);
            DFS(nums, i + 1);

            // 不将当前元素 num[i] 添加到解向量中 x 中，
            // 进行回溯，将 num[i] 从解向量中删除
            x.remove(x.size() - 1);
            DFS(nums, i + 1);
        }
    }


    public static void main(String[] args) {
        int[] a = {1, 2, 2};
        System.out.println("求解结果：");
        Demo2 demo2 = new Demo2();
        System.out.println(demo2.subsetsWithDup(a));
    }
}
