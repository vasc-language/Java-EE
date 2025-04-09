import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-04-05
 * Time: 11:58
 */
public class Test {
    // 归并排序：2. 反转对
    // https://leetcode.cn/problems/reverse-pairs/description/
    public int reversePairs(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        return mergeSort(nums, 0, nums.length - 1);
    }

    //
    private int mergeSort(int[] nums, int left, int right) {
        if (left >= right) {
            return 0;
        }
        // [left, mid] [mid + 1, right]
        int mid = left + (right - left) / 2;
        // 进行归并排序
        int count = mergeSort(nums, left, mid) + mergeSort(nums, mid + 1, right);
        // 对反转对进行统计个数
        count += countPairs(nums, left, mid, right);
        // 将两部分进行合并
        merge(nums, left, mid, right);
        return count;
    }

    private int countPairs(int[]nums, int left, int mid, int right) {
        int count = 0, j = mid + 1;
        for (int i = left; i <= mid; i++) {
            // 计算当前元素后面，有多少个元素的两倍比我小
            while (j <= right && nums[i] > 2.0 * nums[j]) {
                // 在[mid + 1, right]中找到比num[i]大的元素就停下来
                j++;
            }
            // 注意是 j 不是 right
            count += j - (mid + 1);
        }
        return count;
    }

    // 进行升序的递归
    private void merge(int[] nums, int left, int mid, int right) {
        // 创建一个临时数组
        int[] temp = new int[right - left + 1];
        int i = left, j = mid + 1, k = 0;
        while (i <= mid && j <= right) {
            if (nums[i] < nums[j]) {
                temp[k++] = nums[i++];
            } else {
                temp[k++] = nums[j++];
            }
        }

        // 将没有遍历到元素再拷贝到temp
        while (i <= mid) {
            temp[k++] = nums[i++];
        }
        while (j <= right) {
            temp[k++] = nums[j++];
        }

        System.arraycopy(temp, 0, nums, left, temp.length);
    }
    // 归并排序：1. 计算右侧小于当前元素的个数
    // https://leetcode.cn/problems/count-of-smaller-numbers-after-self/description/
    public List<Integer> countSmaller(int[] nums) {
        int n = nums.length;
        int[] counts = new int[n]; // 用于存储每个元素右侧小于它的元素个数，初始化为θ
        int[] indices = new int[n]; // 用于存储元素在原数组中的索引，初始化为从0到n-1
        for (int i = 0; i < n; i++) {
            indices[i] = i;
        }
        // 开始归并排序
        // 调整归并排序方法对 indices 数组进行排序，并在排序中更新 count 数组
        mergeSort(nums, indices, counts, 0, n - 1);
        // 将 count 是数组的数据存储起来
        List<Integer> result = new ArrayList<>();
        for (int count : counts) {
            result.add(count);
        }
        return result;
    }

    private void mergeSort(int[] nums, int[] indices, int[] counts, int left, int right) {
        // 归并排序
        if (left >= right) {
            return;
        }
        // [left, mid] [mid + 1, right]
        int mid = left + (right - left) / 2;
        // 递归左半部分
        mergeSort(nums, indices, counts, left, mid);
        // 递归右半部分
        mergeSort(nums, indices, counts, mid + 1, right);
        // 合并两部分
        merge(nums, indices, counts, left, mid, right);
    }

    private void merge(int[] nums, int[] indices, int[] counts, int left, int mid, int right) {
        // 创建一个临时数组，存储合并的数据
        int[] temp = new int[right - left + 1];
        int i = left, j = mid + 1, k = 0;
        while (i <= mid && j <= right) {
            if (nums[indices[i]] > nums[indices[j]]) {
                counts[indices[i]] += right - j + 1;
                // 修改的是数组索引[将数组 nums 的索引放到了temp 数组中]
                temp[k++] = indices[i++];
            } else {
                temp[k++] = indices[j++];
            }
        }
        while (i <= mid) {
            temp[k++] = indices[i++];
        }
        while (j <= right) {
            temp[k++] = indices[j++];
        }

        System.arraycopy(temp, 0, indices, left, temp.length);
    }
}
