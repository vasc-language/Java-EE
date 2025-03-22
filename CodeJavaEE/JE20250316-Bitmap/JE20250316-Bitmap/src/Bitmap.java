/**
 * Created with IntelliJ IDEA.
 * Description: 位图的基本结构
 * User: 姚东名
 * Date: 2025-03-16
 * Time: 9:46
 */
public class Bitmap {
    private long[] bits;

    // 假设我们要表示 n 个元素
    public Bitmap(int n) {
        // 每一个 long 有64位 bit
        this.bits = new long[(n / 63) + 64]; // 向上取整
    }

    // 设置第 i 位为1，表示存在
    public void set(int i) {
        bits[i / 64] |= 1l << (i % 64);
    }

    // 设置第 i 位为0，表示不存在
    public void clear(int i) {
        bits[i / 64] &= ~(1l << (i % 64));
    }

    // 检查第 i 位是否为1
    public boolean get(int i) {
        return (bits[i / 64] & (1l << (i % 64))) != 0;
    }
}
