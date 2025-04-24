import java.util.*;

class Solution {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g); // 默认为递增排序
        Arrays.sort(s);
        int ans = 0;
        for (int i = 0, j = 0; i < g.length && j < s.length; j++) {
            if (g[i] <= s[j]) {
                i++;
                ans++;
            }
        }
        return ans;
    }
}

public class Demo1 {
    public static void main(String[] args) {
        Solution obj = new Solution();
        int[] g = {3, 1, 5, 3, 8};
        int[] s = {6, 1, 3, 2};
        System.out.println(obj.findContentChildren(g, s));
    }
}