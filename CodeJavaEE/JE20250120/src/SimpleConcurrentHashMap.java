import java.util.concurrent.atomic.AtomicReferenceArray;

/**
 * Created with IntelliJ IDEA.
 * Description: 二次模拟
 * User: 姚东名
 * Date: 2025-01-27
 * Time: 10:41
 */
public class SimpleConcurrentHashMap<K, V> {
    private static final int DEFAULT_CAPACITY = 16;
    private AtomicReferenceArray<Node<K, V>> table = null;

    public SimpleConcurrentHashMap() {
        table = new AtomicReferenceArray<>(DEFAULT_CAPACITY);
    }

    static class Node<K, V> {
        private final K key;
        private volatile V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }

    // put() 方法，该方法用于插入键值对
    public void put(K key, V value) {
        int index = Math.abs(key.hashCode()) % table.length(); // 哪个书架 的编号
        Node<K, V> newNode = new Node<>(key, value);
        while (true) {
            Node<K, V> current = table.get(index); // 当前书架上的 当前书的编号
            if (current == null) {
                // 当前书架上的没有书，直接插入，使用 CAS1操作
                if (table.compareAndSet(index, null, newNode)) {
                    break;
                }
            } else if (current.getKey().equals(key)) {
                // 当前书架上存在相同的书（即编号相同）,也可插入（书中的数据）
                if (table.compareAndSet(index, current, newNode)) {
                    break;
                }
            } else {
                index = (index + 1) % table.length(); // 找下一个书架
            }
        }
    }

    // get() 方法，当前方法是查找给定的键对应的值
    public V get(K key) {
        int index = Math.abs(key.hashCode()) % table.length(); // 当前书架的编号
        Node<K, V> node = table.get(index); // 书架上的一本书（的编号）

        while (node != null) {
            if (node.getKey().equals(key)) {
                return node.getValue();
            }
            // 找不到，就继续往下一个书架走
            index = (index + 1) % table.length();
            node = table.get(index);
        }

        return null;
    }
}
