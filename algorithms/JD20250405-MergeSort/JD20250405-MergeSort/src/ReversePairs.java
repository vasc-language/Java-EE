public class ReversePairs {
    public int reversePairs(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        return mergeSort(nums, 0, nums.length - 1);
    }

    private int mergeSort(int[] nums, int left, int right) {
        if (left >= right) {
            return 0;
        }
        int mid = left + (right - left) / 2;
        // 递归统计左右子数组中的重要翻转对数量
        int count = mergeSort(nums, left, mid) + mergeSort(nums, mid + 1, right);
        // 统计跨越两个子数组的重要翻转对数量
        count += countPairs(nums, left, mid, right);
        // 合并两个有序子数组
        merge(nums, left, mid, right);
        return count;
    }

    private int countPairs(int[] nums, int left, int mid, int right) {
        int count = 0;
        int j = mid + 1;
        for (int i = left; i <= mid; i++) {
            // 找到满足 nums[i] > 2 * nums[j] 的最大 j
            while (j <= right && (long) nums[i] > 2 * (long) nums[j]) {
                j++;
            }
            // 统计重要翻转对的数量
            count += j - (mid + 1);
        }
        return count;
    }

    private void merge(int[] nums, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int i = left, j = mid + 1, k = 0;
        while (i <= mid && j <= right) {
            if (nums[i] <= nums[j]) {
                temp[k++] = nums[i++];
            } else {
                temp[k++] = nums[j++];
            }
        }
        // 将剩余的元素复制到临时数组中
        while (i <= mid) {
            temp[k++] = nums[i++];
        }
        while (j <= right) {
            temp[k++] = nums[j++];
        }
        // 将临时数组中的元素复制回原数组
        for (i = left, k = 0; i <= right; i++, k++) {
            nums[i] = temp[k];
        }
    }

    public static void main(String[] args) {
        ReversePairs solution = new ReversePairs();
        int[] nums1 = {1, 3, 2, 3, 1};
        System.out.println("输入: [1,3,2,3,1], 输出: " + solution.reversePairs(nums1));
        int[] nums2 = {2, 4, 3, 5, 1};
        System.out.println("输入: [2,4,3,5,1], 输出: " + solution.reversePairs(nums2));
    }
}

