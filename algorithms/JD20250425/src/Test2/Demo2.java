package Test2;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-04-26
 * Time: 10:58
 */
public class Demo2 {
    public String largestNumber(int[] nums) {
        int n = nums.length;
        String[] a = new String[n];
        // 将 nums 数组转化为 字符串数组 a
        for (int i = 0; i < n; i++) {
            a[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(a, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                // s 和 t
                String s = o1 + o2;
                String t = o2 + o1;
                // 进行降序排序
                return t.compareTo(s);
            }
        });

        String ans = "";
        // 拼接字符串
        for (int i = 0; i < n; i++) {
            ans += String.valueOf(a[i]);
        }
        if (ans.charAt(0) == '0') {
            return "0";
        }

        return ans;
    }

    public static void main(String[] args) {
        Demo2 obj=new Demo2();
        int nums[]={3,30,34,5,9};
        System.out.println(obj.largestNumber(nums));
    }
}
