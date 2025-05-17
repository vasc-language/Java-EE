import java.util.Arrays;
import java.util.Random;

public class BubbleSortOptimized {
    public static void bubbleSortOptimized(int[] arr) {
        int n = arr.length;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped)
                break;
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[1000];
        Random rand = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rand.nextInt(1000);
        }
        long startTime = System.nanoTime();
        bubbleSortOptimized(arr);
        long endTime = System.nanoTime();
        System.out.println("优化后的冒泡排序时间：" + (endTime - startTime) + "ns");
        System.out.println(Arrays.toString(arr));
    }
}