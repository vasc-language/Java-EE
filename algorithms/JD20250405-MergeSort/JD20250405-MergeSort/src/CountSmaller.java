import java.util.ArrayList;
import java.util.List;

class Element {
    int value;
    int originalIndex;

    public Element(int value, int originalIndex) {
        this.value = value;
        this.originalIndex = originalIndex;
    }
}

public class CountSmaller {
    public List<Integer> countSmaller(int[] nums) {
        int n = nums.length;
        Element[] elements = new Element[n];
        for (int i = 0; i < n; i++) {
            elements[i] = new Element(nums[i], i);
        }
        int[] counts = new int[n];
        mergeSort(elements, 0, n - 1, counts);
        List<Integer> result = new ArrayList<>();
        for (int count : counts) {
            result.add(count);
        }
        return result;
    }

    private void mergeSort(Element[] elements, int left, int right, int[] counts) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(elements, left, mid, counts);
            mergeSort(elements, mid + 1, right, counts);
            merge(elements, left, mid, right, counts);
        }
    }

    private void merge(Element[] elements, int left, int mid, int right, int[] counts) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        Element[] L = new Element[n1];
        Element[] R = new Element[n2];
        System.arraycopy(elements, left, L, 0, n1);
        System.arraycopy(elements, mid + 1, R, 0, n2);
        int i = 0, j = 0, k = left;
        int rightCount = 0;
        while (i < n1 && j < n2) {
            if (L[i].value >= R[j].value) {
                counts[L[i].originalIndex] += rightCount;
                elements[k++] = L[i++];
            } else {
                elements[k++] = R[j++];
                rightCount++;
            }
        }
        while (i < n1) {
            counts[L[i].originalIndex] += rightCount;
            elements[k++] = L[i++];
        }
        while (j < n2) {
            elements[k++] = R[j++];
        }
    }

    public static void main(String[] args) {
        CountSmaller solution = new CountSmaller();
        int[] nums = {5,2,6,1};
        List<Integer> result = solution.countSmaller(nums);
        System.out.println(result);
    }
}    