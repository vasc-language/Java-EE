import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class HashTableVsConcurrentHashMap {

    public static void main(String[] args) throws InterruptedException {
        int threadCount = 10;
        int operationsPerThread = 100000;

        // Hashtable Test
        testHashTable(threadCount, operationsPerThread);

        // ConcurrentHashMap Test
        testConcurrentHashMap(threadCount, operationsPerThread);
    }

    private static void testHashTable(int threadCount, int operationsPerThread) throws InterruptedException {
        Map<String, String> map = new Hashtable<>();

        Runnable task = () -> {
            for (int i = 0; i < operationsPerThread; i++) {
                map.put(Thread.currentThread().getName() + "-" + i, "value" + i);
            }
        };

        runTest(map, task, threadCount, "Hashtable");
    }

    private static void testConcurrentHashMap(int threadCount, int operationsPerThread) throws InterruptedException {
        Map<String, String> map = new ConcurrentHashMap<>();

        Runnable task = () -> {
            for (int i = 0; i < operationsPerThread; i++) {
                map.put(Thread.currentThread().getName() + "-" + i, "value" + i);
            }
        };

        runTest(map, task, threadCount, "ConcurrentHashMap");
    }

    private static void runTest(Map<String, String> map, Runnable task, int threadCount, String mapName) throws InterruptedException {
        Thread[] threads = new Thread[threadCount];
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < threadCount; i++) {
            threads[i] = new Thread(task, "Thread-" + i);
            threads[i].start();
        }

        for (int i = 0; i < threadCount; i++) {
            threads[i].join();
        }

        long endTime = System.currentTimeMillis();
        System.out.println(mapName + " Time: " + (endTime - startTime) + " ms, Size: " + map.size());
    }
}