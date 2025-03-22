import java.util.concurrent.atomic.AtomicReferenceArray;

/**
 * Created with IntelliJ IDEA.
 * Description: ConcurrentHashMap 借助 AtomicReferenceArray 原子类来模拟实现
 * User: 姚东名
 * Date: 2025-01-26
 * Time: 17:57
 */
public class Demo13<K, V> {
    private static final int DEFAULT_CAPACITY = 16;
    private AtomicReferenceArray<Node<K, V>> table = null; // 使用 AtomicReferenceArray 来存储键值对，并通过 CAS 操作控制并发

    public Demo13() {
        table = new AtomicReferenceArray<>(DEFAULT_CAPACITY);
    }

    static class Node<K, V> {
        private K key;
        private V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }

    // put() 方法 将键值对存储到 HashMap 中，如果键已经存在，则更新对应的值
    public void put(K key, V value) {
        int index = Math.abs(key.hashCode()) % table.length(); // 线性探索下标
        Node<K, V> newNode = new Node<>(key, value);
        while (true) {
            Node current = table.get(index);
            // 1. 如果当前节点为 null ，则直接插入新节点
            if (current == null) {
                if (table.compareAndSet(index, null, newNode)) {
                    break;
                }
            } else if (current.getKey().equals(key)) {
                // 2. 如果当前位置已有节点，且键相等，则更新节点的值
                if (table.compareAndSet(index, current, newNode)) {
                    break;
                }
            } else {
                // 3. 如果当前位置已有节点，但是键不相同，则进行线性探测
                index = (index + 1) % table.length(); // 查找下一个可用位置
            }
        }
    }


    // get() 方法，用于获取 HashMap 中的键值对 的Value（该方法用于从哈希表中查找给定键对应的值）
    public V get(K key) {
        int index = Math.abs(key.hashCode()) % table.length();
        Node<K, V> node = table.get(index);
        while (node != null) {
            if (node.getKey().equals(key)) {
                return node.getValue();
            }

            index = (index + 1) % table.length(); // 继续寻找下一个位置
            node = table.get(index);
        }
        return null;
    }
}
