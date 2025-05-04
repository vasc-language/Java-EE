import java.util.PriorityQueue;

public class KthLargest {
    // 8. 数据流中第 K 大的元素
    // https://leetcode.cn/problems/kth-largest-element-in-a-stream/description/
    private PriorityQueue<Integer> heap;
    private int _k;

    // 使用整数 k 和整数流 nums 初始化对象
    public KthLargest(int k, int[] nums) {
        heap = new PriorityQueue<>(); // 申请一个小根堆，即堆顶元素是最小的
        _k = k;

        for (int x : nums) {
            heap.offer(x); // 元素按照从小到大的顺序进行排序，最小的元素会在堆顶
            if (heap.size() > _k) {
                heap.poll(); // 确保堆中始终保持 K 个最大的元素
            }
        }
        // 剩下的就是 >= k 的元素
    }
    
    // 将 val 插入数据流 nums 后，返回当前数据流中第 k 大的2元素
    /**
     * 当有新元素来时：
     * - 如果新元素比堆顶元素大，它将被加入堆中，然后堆顶（原来的第 K 元素）会被移除
     * - 如果新元素比堆顶元素小，它将被加入堆中，但随后立即被移除（因为它不够大）
     * @param val
     * @return
     */
    public int add(int val) {
        heap.offer(val);
        if (heap.size() > _k) {
            heap.poll();
        }

        return heap.peek();
    }
}
