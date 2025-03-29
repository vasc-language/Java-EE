import java.util.LinkedList;
import java.util.Iterator;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-03-26
 * Time: 17:30
 */
public class Test {
    // 2. 分治：数组中第 K 大的元素
    // https://leetcode.cn/problems/kth-largest-element-in-an-array/
    public int findKthLargest1(int[] nums, int k) {
        return quickSelect1(nums, 0, nums.length - 1, k);
    }

    public int quickSelect1(int[] nums, int l, int r, int k) {
        if (l == r)
            return nums[l];

        // 1. 按照随机选择的基准元素
        Random rand = new Random();
        int pivotIndex = l + rand.nextInt(r - l + 1); // 优化写法
        int pivot = nums[pivotIndex];

        int left = l - 1, right = r + 1, i = l;
        // 2. 将数组分为三部分
        while (i < right) {
            if (nums[i] < pivot)
                swap(nums, ++left, i++);
            else if (nums[i] == pivot)
                i++;
            else
                swap(nums, --right, i);
        }
        // 3. 判断 k 在哪个区间中
        // [l, left - 1] [left, right - 1] [right, r]
        //    < pivot         = pivot      > pivot
        //
        int countGreater = r - right + 1; // 大于基准值的元素数量 r - (right - 1) == r - right + 1
        int countEqual = right - left - 1; // 等于基准值的元素数量 right - 1 - (left - 1) == right - left

        if (countGreater >= k) {
            // 在基准元素 pivot 的右边
            return quickSelect(nums, right, r, k);
        } else if (countGreater + countEqual >= k) {
            // 找到了
            return pivot;
        } else {
            // 在基准元素的左边
            return quickSelect(nums, l, left, k - countGreater - countEqual);
        }

        // int c = r - right + 1, b = right - left - 1;
        // if (c >= k)
        //     return quickSelect(nums, right, r, k);
        // else if (c + b >= k)
        //     return pivot;
        // else
        //     return quickSelect(nums, l, left, k - b - c);
    }

    // 辅助方法：交换数组元素
    public int findKthLargest(int[] nums, int k) {
        // 将第 k 大转换成第 (n - k) 小，n 为数组长度
        int targetIndex = nums.length - k;
        return quickSelect(nums, 0, nums.length - 1, targetIndex);
    }

    private int quickSelect(int[] nums, int left, int right, int targetIndex) {
        if (left == right) {
            return nums[left];
        }

        // 随机选择基准元素
        int pivot = nums[new Random().nextInt(right - left + 1) + left];

        // 使用升序进行三路分区
        // 分区结果：[left, lt-1] < pivot, [lt, gt] == pivot, [gt+1, right] > pivot
        int lt = left, i = left, gt = right;
        while (i <= gt) {
            if (nums[i] < pivot) {
                swap(nums, lt++, i++);
            } else if (nums[i] > pivot) {
                swap(nums, i, gt--);
            } else {
                i++;
            }
        }

        // 判断目标索引在哪个区间
        if (targetIndex < lt) {
            // 第 targetIndex 小的元素在左侧区间中
            return quickSelect(nums, left, lt - 1, targetIndex);
        } else if (targetIndex > gt) {
            // 在右侧区间中，此时需要调整目标索引
            return quickSelect(nums, gt + 1, right, targetIndex);
        } else {
            // 当 targetIndex 落在 [lt, gt] 范围内，说明 pivot 就是答案
            return pivot;
        }
    }

    // 辅助方法：交换数组元素
    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    // 1. 分治：颜色分类
    // https://leetcode.cn/problems/sort-colors/
    public void sortColors(int[] nums) {
        if (nums.length == 0 || nums == null) {
            return;
        }
        threeWayQuickSort(nums, 0, nums.length - 1);
    }

    public void threeWayQuickSort(int[] nums, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        int pivot = nums[lo];
        int lt = lo, i = lo, gt = hi;
        while (i <= gt) {
            if (nums[i] < pivot) {
                Swap(nums, i, lt);
                lt++;
                i++;
            } else if (nums[i] > pivot) {
                Swap(nums, i, gt);
                gt--;
            } else {
                i++;
            }
        }
        // 递归左边，再递归右边
        threeWayQuickSort(nums, lo, lt - 1);
        threeWayQuickSort(nums, gt + 1, hi);
    }

    public void Swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
    public static void main(String[] args) {
        Graph graph = new Graph(4);
        graph.addEdge(0,1);
        graph.addEdge(0,2);
        graph.addEdge(1, 2);
        graph.addEdge(2,0);
        graph.addEdge(2,3);
        graph.addEdge(3,3);
        System.out.println("深度优先遍历(从顶点2开始):");//从顶点2开始读用
        graph.DFS(2);//从顶点2开始读用
    }
}
// 图的创建
class Graph {
    private int V; // 顶点数
    private LinkedList<Integer>[] adj; // 邻接表

    public Graph(int v) {
        this.V = v;
        this.adj = new LinkedList[v]; //
        for (int i = 0; i < v; i++) {
            adj[i] = new LinkedList<>(); //
        }
    }

    // 添加边
    public void addEdge(int v, int w) {
        adj[v].add(w); // 将图连接起来
    }
    
    // 使用深度优先遍历 图
    public void DFS(int v) {
        boolean[] visited = new boolean[V];
        DFSUtil(v, visited);
    }

    private void DFSUtil(int v, boolean[] visited) {
        // 标记邻接表是否遍历过
        visited[v] = true;
        System.out.print(v + " ");

        // 使用迭代器遍历相邻的顶点
        Iterator<Integer> i = adj[v].listIterator();
        while (i.hasNext()) {
            Integer n = i.next();
            if (!visited[n]) {
                DFSUtil(n, visited);
            }
        }
    }
}
