import java.util.*;

class Action { // 活动类
    int b; // 活动起始时间
    int e; // 活动结束时间
    public Action(int b, int e) {  // 构造方法
        this.b = b;
        this.e = e;
    }
}

class Solution1 {
    Action[] A; // 问题描述
    int n;
    boolean[] flag; // 标记选择的活动

    void greedly() {                        // 贪心算法
        flag = new boolean[n];
        Arrays.fill(flag, false);          // 初始化为false
        Arrays.sort(A, new Comparator<Action>() {    // 按e递增排序
            @Override
            public int compare(Action o1, Action o2) {
                return o1.e - o2.e;
            }
        });
        int preend = 0; // 前一个兼容活动的结束时间
        for (int i = 0; i < n; i++) {
            if (A[i].b >= preend) {
                flag[i] = true; // 选择A[i]活动
                preend = A[i].e;
            }
        }
    }

    void action(Action[] A, int n) { // 求解活动安排问题Ⅰ
        this.A = A;
        this.n = n;
        greedly();
        System.out.printf("求解结果\n");
        System.out.printf("  选取的活动:");
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if (flag[i]) {
                System.out.printf("[%d,%d] ", A[i].b, A[i].e);
                cnt++;
            }
        }
        System.out.printf("\n  共%d个活动\n", cnt);
    }
}

public class action1 {
    public static void main(String[] args) {
        Solution1 obj = new Solution1();
        Action[] A = {new Action(1, 4),
                new Action(3, 5),
                new Action(0, 6),
                new Action(5, 7),
                new Action(3, 8),
                new Action(5, 9),
                new Action(6, 10),
                new Action(8, 11),
                new Action(8, 12),
                new Action(2, 13),
                new Action(12, 15)
        };
        int n = A.length;
        System.out.println("A:");
        for (int i = 0; i < n; i++) {
            System.out.printf(" [%d,%d]", A[i].b, A[i].e);
        }
        System.out.println();
        obj.action(A, n);
    }
}