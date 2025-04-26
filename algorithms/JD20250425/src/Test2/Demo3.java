package Test2;

import java.util.*;

/**
 * 贪心算法-背包问题
 */
class Goods { // 物品类
    int no; // 物品的编号
    int w; // 物品的重量
    int v; // 物品的价值

    public Goods(int no, int w, int v) { // 构造方法
        this.no = no;
        this.w = w;
        this.v = v;
    }
}

class Solution4 {
    Goods[] g; //全部物品
    int W; //背包容量
    int n; //物品个数

    void knap(Goods g[], int W) { // 贪心法求解背包问题
        this.g = g;
        this.W = W;
        this.n = g.length;
        Arrays.sort(g, new Comparator<Goods>() { // 按 v/w 递减排序
            @Override
            public int compare(Goods o1, Goods o2) {
                if ((double) o2.v / o2.w >= (double) o1.v / o1.w)
                    return 1;
                else
                    return -1;
            }
        });
        double[] x = new double[n]; // 存放最优解向量
        Arrays.fill(x, 0); // 初始化x向量
        int bestv = 0; // 存放最大价值,初始为0
        double rw = W; // 背包中能装入的余下重量
        int i = 0;
        // g[i].w g[i] 的重量
        // g[i].v g[i] 的价值
        // rw 剩余的背包容量
        while (i < n && g[i].w < rw) { // 物品i能够全部装入时循环
            x[i] = 1; // 装入物品i
            rw -= g[i].w; // 减少背包中能装入的余下重量
            bestv += g[i].v; // 累计总价值
            i++; //继续循环
        }
        if (i < n && rw > 0) { // 当余下重量大于0
            x[i] = rw / g[i].w; // 将物品i的一部分装入，
            bestv += x[i] * g[i].v; // 累计总价值
        }
        System.out.printf("最优解\n"); // 输出结果
        for (int j = 0; j < n; j++) {
            if (x[j] == 1)
                System.out.printf("  选择物品%d[%d,%d]百分之百\n", g[j].no, g[j].w, g[j].v);
            else if (x[j] > 0)
                System.out.printf("选择物品%d[%d,%d]为百分之%.1f\n", g[j].no, g[j].w, g[j].v, x[j] * 100);
        }
        System.out.printf("  总价值=%d\n", bestv);
    }
}

public class Demo3 {
    public static void main(String[] args) {
        Solution4 obj = new Solution4();
        Goods[] g = {new Goods(0, 10, 20),
                new Goods(1, 20, 30),
                new Goods(2, 30, 66),
                new Goods(3, 40, 40),
                new Goods(4, 50, 60)
        };
        int W = 100;
        obj.knap(g, W);
        System.out.println();
    }
}
