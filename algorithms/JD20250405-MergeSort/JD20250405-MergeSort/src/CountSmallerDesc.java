import java.util.ArrayList;
import java.util.List;

public class CountSmallerDesc {
    public List<Integer> countSmaller(int[] nums) {
        int n = nums.length;
        int[] counts = new int[n];
        int[] indices = new int[n];
        for (int i = 0; i < n; i++) {
            indices[i] = i;
        }
        mergeSort(nums, indices, counts, 0, n - 1);
        List<Integer> result = new ArrayList<>();
        for (int count : counts) {
            result.add(count);
        }
        return result;
    }

    private void mergeSort(int[] nums, int[] indices, int[] counts, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = left + (right - left) / 2;
        mergeSort(nums, indices, counts, left, mid);
        mergeSort(nums, indices, counts, mid + 1, right);
        merge(nums, indices, counts, left, mid, right);
    }

    private void merge(int[] nums, int[] indices, int[] counts, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int i = left;
        int j = mid + 1;
        int k = 0;
        while (i <= mid && j <= right) {
            if (nums[indices[i]] > nums[indices[j]]) {
                counts[indices[i]] += right - j + 1;
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

    public static void main(String[] args) {
        CountSmallerDesc solution = new CountSmallerDesc();
        int[] nums1 = {5, 2, 6, 1};
        List<Integer> result1 = solution.countSmaller(nums1);
        System.out.println(result1);

        int[] nums2 = {-1};
        List<Integer> result2 = solution.countSmaller(nums2);
        System.out.println(result2);

        int[] nums3 = {-1, -1};
        List<Integer> result3 = solution.countSmaller(nums3);
        System.out.println(result3);
    }
}    