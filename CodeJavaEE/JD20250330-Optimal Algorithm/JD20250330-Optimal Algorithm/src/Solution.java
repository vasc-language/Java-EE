import java.util.Random;

public class Solution {
    // 归并排序：3. 交易逆序对的总数
    // https://leetcode.cn/problems/shu-zu-zhong-de-ni-xu-dui-lcof/description/
    public int reversePairs(int[] record) {
        if (record == null || record.length < 2) {
            return 0;
        }

        // 申请一个新的数组
        int[] temp = new int[record.length];
        return mergeSort3(record, 0, record.length - 1, temp);
    }

    private int mergeSort3(int[] nums, int left, int right, int[] temp) {
        if (left >= right) {
            return 0;
        }
        // 1. 取一个中间数mid将数组分为前后两部分
        int mid = left + (right - left) / 2;
        // 2. 直接统计逆序对的个数
        int count = mergeSort3(nums, left, mid, temp) + mergeSort3(nums, mid + 1, right, temp);
        // 提前检查是否需要合并，减少无效操作
        if (nums[mid] <= nums[mid + 1]) {
            return count;
        }
        count += merge3(nums, left, mid, right, temp);
        return count;
    }

    // 3. 升序，找前面比我大的数
    private int merge3(int[] nums, int left, int mid, int right, int[] temp) {
        //[left mid] [mid + 1 right]
        int i = left, j = mid + 1, k = 0, count = 0;
        while (i <= mid && j <= right) {
            if (nums[i] <= nums[j]) {
                temp[k++] = nums[i++];
            } else {
                count += (mid - i + 1);
                temp[k++] = nums[j++];
            }
        }

        // 4. 将剩下未被访问的元素全都放到 temp
        while (i <= mid) {
            temp[k++] = nums[i++];
        }
        while (j <= right) {
            temp[k++] = nums[j++];
        }

        // 将合并后的结果复制回原数组
        // 现在数组 现在坐标 原数组 原坐标 长度
        System.arraycopy(temp, 0, nums, left, k);
        return count;
    }
    // 2. 分治-归并排序：排序数组
    // https://leetcode.cn/problems/sort-an-array/description/
    public int[] sortArray2(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return nums;
        }
        int[] temp = new int[nums.length];
        mergeSort2(nums, 0, nums.length - 1, temp);
        return nums;
    }

    public void mergeSort2(int[] nums, int left, int right, int[] temp) {
        if (left >= right) {
            return;
        }
        if (left < right) {
            // 1. 根据中间点划分区间
            int mid = left + (right - left) / 2;
            // [left, mid] [mid + 1, right]
            // 2. 先将左右区间排个序
            mergeSort(nums, left, mid, temp);
            mergeSort(nums, mid + 1, right, temp);
            // 3. 合并两个有序数组
            merge2(nums, left, mid, right, temp);
        }
    }

    private void merge2(int[] nums, int left, int mid, int right, int[] temp) {
        System.arraycopy(nums, left, temp, left, right - left + 1);
        int i = left;
        int j = mid + 1;
        int k = left;
        while (i <= mid && j <= right) {
            if (temp[i] <= temp[j]) {
                nums[k++] = temp[i++];
            } else {
                nums[k++] = temp[j++];
            }
        }
        while (i <= mid) {
            nums[k++] = temp[i++];
        }
        while (j <= right) {
            nums[k++] = temp[j++];
        }
    }
    // 优化后：
    public int[] sortArray(int[] nums) {
        int[] temp = new int[nums.length];
        mergeSort(nums, 0, nums.length - 1, temp);
        return nums;
    }
    private void mergeSort(int[] nums, int left, int right, int[] temp) {
        if (left >= right) {
            return;
        }
        // 小数组使用插入排序
        if (right - left <= 15) {
            insertionSort(nums, left, right);
            return;
        }
        int mid = left + (right - left) / 2;
        mergeSort(nums, left, mid, temp);
        mergeSort(nums, mid + 1, right, temp);
        // 如果已经有序，无需合并
        if (nums[mid] <= nums[mid + 1]) {
            return;
        }
        merge(nums, left, mid, right, temp);
    }
    private void merge(int[] nums, int left, int mid, int right, int[] temp) {
        System.arraycopy(nums, left, temp, left, right - left + 1);
        int i = left;
        int j = mid + 1;
        int k = left;
        while (i <= mid && j <= right) {
            if (temp[i] <= temp[j]) {
                nums[k++] = temp[i++];
            } else {
                nums[k++] = temp[j++];
            }
        }
        while (i <= mid) {
            nums[k++] = temp[i++];
        }
        while (j <= right) {
            nums[k++] = temp[j++];
        }
    }
    private void insertionSort(int[] nums, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int key = nums[i];
            int j = i - 1;
            while (j >= left && nums[j] > key) {
                nums[j + 1] = nums[j];
                j--;
            }
            nums[j + 1] = key;
        }
    }
    // 1. 分治：LCR 159. 存库管理 III
    // https://leetcode.cn/problems/zui-xiao-de-kge-shu-lcof/description/
    public int[] inventoryManagement(int[] stock, int cnt) {
        // 基本情况
        if (cnt == 0) {
            return new int[0]; // 如果 cnt 为 0，返回空数组
        }
        if (cnt >= stock.length) {
            return stock; // 如果 cnt 大于或等于数组长度，返回整个数组
        }
        
        // 使用 quickSelect 保证 stock 的前 cnt 个元素
        // 是 cnt 个最小的元素（顺序无关）
        quickSelect(stock, 0, stock.length - 1, cnt);
        
        // 将前 cnt 个元素复制到结果数组中
        int[] result = new int[cnt];
        System.arraycopy(stock, 0, result, 0, cnt);
        return result;
    }
    
    /**
     * quickSelect 方法重新排列数组，使得前 cnt 个元素
     * （从索引 left 到 left+cnt-1）是子数组中 cnt 个最小的数。
     * 它使用随机化的枢轴（pivot）和三路分区。
     *
     * 分区将 [left, right] 分割为：
     *   [left, lt - 1]    --> 小于枢轴的元素
     *   [lt, gt]          --> 等于枢轴的元素
     *   [gt + 1, right]   --> 大于枢轴的元素
     *
     * @param nums 包含库存值的数组
     * @param left 子数组的左边界
     * @param right 子数组的右边界
     * @param targetIndex 在此子数组中要选择的最小元素的数量
     */
    private int quickSelect(int[] nums, int left, int right, int targetIndex) {
        if (left == right) {
            return nums[left];
        }
        
        // 随机选择基准元素
        Random rand = new Random();
        int pivotIndex = left + rand.nextInt(right - left + 1);  // 优化写法
        int pivot = nums[pivotIndex];
        
        // 使用升序进行三路分区
        // 分区结果：[left, lt-1] < pivot, [lt, gt] == pivot, [gt+1, right] > pivot
        int lt = left, i = left, gt = right;
        while (i <= gt) {
            if (nums[i] < pivot) {
                swap(nums, lt, i);
                lt++;
                i++;
            } else if (nums[i] > pivot) {
                swap(nums, i, gt);
                gt--;
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
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}