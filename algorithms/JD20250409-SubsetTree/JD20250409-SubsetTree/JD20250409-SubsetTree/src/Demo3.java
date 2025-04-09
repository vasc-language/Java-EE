/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-04-09
 * Time: 21:40
 */
public class Demo3 {
    int ans; // 存放解的个数
    public int findTargetSumWays(int[] nums, int target) {
        ans = 0;
        DFS(nums, target, 0, 0);
        return ans;
    }

    /**
     *
     * @param nums 输入数组
     * @param target 目标值
     * @param i 当前处理的元素索引
     * @param expv 当前表达式的累计值
     */
    private void DFS(int[] nums, int target, int i, int expv) {
        if (i == nums.length) {
            // 找到叶子节点 && 找到目标值
            if (expv == target) {
                ans++; // 找到一个解（一般不止一个解）
            }


        } else {
            expv += nums[i]; // nums[i]前选择'+'
            DFS(nums, target, i + 1, expv);
            expv -= nums[i]; // 回溯

            expv -= nums[i];
            DFS(nums, target, i + 1, expv);
            expv += nums[i]; // 回溯
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        Demo3 demo3 = new Demo3();
        System.out.println("处理结果：");
        System.out.println(demo3.findTargetSumWays(nums, -4));
    }
}
