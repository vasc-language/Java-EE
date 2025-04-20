import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-04-20
 * Time: 8:26
 */
public class Demo1 {
    // 1. 两数之和
    // https://leetcode.cn/problems/two-sum/description/
    /**
     * 把哈希表设置成[键 = 元素值, 值 = 索引] 就可以直接通过
     * map.containsKey(x) 和 map.get(x) 在 O(1) 时间内判断并获取相应的索引
     * 从而把整体算法的时间复杂度保持在 O(n)
     * 我们查询的是“值”（补数）是否存在，所以将“值”放到 Key 上最合适
     * value 存储的是我们需要返回的索引
     * @param nums 数组
     * @param target 目标值
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        // <nums[i], i>
        // 键是元素的值 值是元素的索引
        for (int i = 0; i < nums.length; i++) {
            // 找当前元素之前符合条件的元素
            int x = target - nums[i];
            // 如果在 hash 表中有这个值，直接放到新的数组中，存放下来
            if (map.containsKey(x)) {
                return new int[]{i, map.get(x)}; // x = Key
            }
            // 没有出现过，就存放到哈希表中
            map.put(nums[i], i); // Key-Value
        }
        return new int[]{-1, -1};
    }

    public static void main(String[] args) {
        Demo1 demo = new Demo1();

        // 示例1
        int[] nums1 = {2, 7, 11, 15};
        int target1 = 9;
        int[] result1 = demo.twoSum(nums1, target1);
        System.out.println("示例1输出: [" + result1[0] + ", " + result1[1] + "]");

        // 示例2
        int[] nums2 = {3, 2, 4};
        int target2 = 6;
        int[] result2 = demo.twoSum(nums2, target2);
        System.out.println("示例2输出: [" + result2[0] + ", " + result2[1] + "]");

        // 示例3
        int[] nums3 = {3, 3};
        int target3 = 6;
        int[] result3 = demo.twoSum(nums3, target3);
        System.out.println("示例3输出: [" + result3[0] + ", " + result3[1] + "]");
    }
}
