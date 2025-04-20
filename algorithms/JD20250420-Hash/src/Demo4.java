import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-04-20
 * Time: 10:49
 */
public class Demo4 {
    // 5. 字母异位词分组
    // https://leetcode.cn/problems/group-anagrams/description/
    public List<List<String>> groupAnagrams(String[] strs) {
        // 将排序后的字符串（String）当作哈希表的 Key 值
        // 将字母异位词数组（String[]）当作哈希表的 Value 值
        Map<String, List<String>> map = new HashMap<>();

        for (String s: strs) {
            // 将字符串转化为字符数组
            char[] tmp = s.toCharArray();
            // 对字符进行排序
            Arrays.sort(tmp);
            // 将字符数组又变成字符串
            String key = new String(tmp);

            if (!map.containsKey(key)) {
                map.put(key, new ArrayList());
            }
            // map.get(key) 相当于一个列表，将字符串(同一个类型)放到一个列表中
            map.get(key).add(s);
        }

        // 提取结果
        // 列表套列表，里面的列表存放字母异位词数组（String[]）
        return new ArrayList(map.values());
    }

    public static void main(String[] args) {
        Demo4 demo = new Demo4();
        // 示例 1
        String[] strs1 = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println("示例1: " + demo.groupAnagrams(strs1));
        // 示例 2
        String[] strs2 = {""};
        System.out.println("示例2: " + demo.groupAnagrams(strs2));
        // 示例 3
        String[] strs3 = {"a"};
        System.out.println("示例3: " + demo.groupAnagrams(strs3));
    }
}
