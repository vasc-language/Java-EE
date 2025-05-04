import java.util.PriorityQueue;

public class Demo6 {
    // 7. 最后一块石头的重量
    // https://leetcode.cn/problems/last-stone-weight/description/
    public int lastStoneWeight(int[] stones) {
        // 创建一个大根堆
        PriorityQueue<Integer> heap = new PriorityQueue<>((a, b) -> b - a);
        // 将所有的元素依次放入堆中
        for (int x : stones) {
            heap.offer(x);
        }

        // 开始从堆中取出两个当前两个最大的元素，进行相减
        // 需要两个元素以上才能进行碰撞
        while (heap.size() > 1) {
            int a = heap.poll();
            int b = heap.poll();

            // 为了处理两个石头重量相同的情况（堆顶元素只能证明当前所有元素中最大的，不能说明当前元素只有一个元素最大，也可能存在相同的）
            // 从算法角度看，确实在正常情况下 a 应该大于或等于 b
            if (a > b) {
                heap.offer(a - b);
            }
        }
        return !heap.isEmpty() ? heap.peek() : 0;
    }
    public int lastStoneWeight2(int[] stones) {
        PriorityQueue<Integer> heap = new PriorityQueue<>((a, b) -> b - a);
        // 将石头都放进堆中，堆顶元素一定是最大的
        for (int x : stones) {
            heap.offer(x);
        }
        
        // 模拟
        while (heap.size() > 1) {
            int a = heap.poll();
            int b = heap.poll();

            if (a > b) {
                heap.offer(a - b); // 将新石头的重量重新放到堆中
            }
        }
        // 看最终堆中是否还存在元素
        return heap.isEmpty() ? 0 : heap.peek();
    }
}
