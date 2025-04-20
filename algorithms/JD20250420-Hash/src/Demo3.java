import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-04-20
 * Time: 9:59
 */
public class Demo3 {
    /**
     * HashSet 判断元素是否包含在哈希表中 使用 contains()
     * HashMap 判断元素是否包含在哈希表中 使用 containsKey()
     * @param nums
     * @return
     */
    // 3. 重复元素I
    // https://leetcode.cn/problems/contains-duplicate/description/
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int x: nums) {
            if (set.contains(x)) {
                return true;
            }
            set.add(x);
        }
        return false;
    }

    // 4. 重复元素II
    // https://leetcode.cn/problems/contains-duplicate-ii/description/
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        // HashMap<nums[i], i>
        // 将哈希表设成[键 = 元素值, 值 = 索引]
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                // abs(i - j) <= k
                if (Math.abs(map.get(nums[i]) - i) <= k) {
                    return true;
                }
            }
            map.put(nums[i], i);
        }
        return false;
    }

    public static void main(String[] args) {
        Demo3 demo = new Demo3();
        // 测试用例1
        int[] nums1 = {1, 2, 3, 1};
        int k1 = 3;
        System.out.println("containsDuplicate(nums1): " + demo.containsDuplicate(nums1)); // true
        System.out.println("containsNearbyDuplicate(nums1, k1): " + demo.containsNearbyDuplicate(nums1, k1)); // true
    
        // 测试用例2
        int[] nums2 = {1, 2, 3, 4};
        int k2 = 1;
        System.out.println("containsDuplicate(nums2): " + demo.containsDuplicate(nums2)); // false
        System.out.println("containsNearbyDuplicate(nums2, k2): " + demo.containsNearbyDuplicate(nums2, k2)); // false
    }
}
