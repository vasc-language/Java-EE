/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-04-09
 * Time: 7:11
 */
public class Demo1 {
    private int[] x; // 解向量
    // 输出一个解
    public void disp(int a[]) {
        System.out.print(" {");
        for (int i = 0; i < x.length; i++) {
            if (x[i] == 1) {
                // 证明选择了原数组第i的元素
                System.out.print(a[i] + " ");
            }
        }
        System.out.println("}");
    }

    // 深度优先遍历回溯算法
    public void DBS(int a[], int i) {
        if (i >= x.length) {
            disp(a);
        } else {
            // 选择原数组的第 i 个元素
            x[i] = 1;
            DBS(a, i + 1);
            // 不选择原数组的第 i 个元素
            x[i] = 0;
            DBS(a, i + 1);
        }

    }
    // 求 a 的幂集
    public void subSet(int a[]) {
        x = new int[a.length];
        DBS(a, 0); // 从第一层开始
    }
    public static void main(String[] args) {
        int[] a = {1, 2, 3};
        Demo1 d = new Demo1();
        System.out.println("演算结果：");
        d.subSet(a);
    }
}
