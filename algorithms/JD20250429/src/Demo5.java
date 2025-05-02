import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-05-02
 * Time: 8:15
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Pair<K, V> {
    private K key;
    private V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }
}

public class Demo5 {
    // 4. 二叉树的锯齿形层序遍历
    // https://leetcode.cn/problems/binary-tree-zigzag-level-order-traversal/description/
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ret = new ArrayList<>();
        if (root == null) {
            return ret;
        }
        int level = 1; // 记录层数，假如是偶数层，就进行逆序
        // 申请一个队列
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            // 计算这一层的大小
            int sz = queue.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < sz; i++) {
                TreeNode t = queue.poll(); // 当前元素出队列
                list.add(t.val);
                // 子节点入队列
                if (t.left != null) {
                    queue.add(t.left);
                }
                if (t.right != null) {
                    queue.add(t.right);
                }
            }
            // 判断是否当前层，是否需要逆序
            if (level % 2 == 0) {
                Collections.reverse(list);
            }
            ret.add(list);
            level++;
        }
        return ret;
    }

    // 5. 二叉树的最大宽度
    // https://leetcode.cn/problems/maximum-width-of-binary-tree/description/
    public int widthOfBinaryTree(TreeNode root) {
        // 用数组模拟队列
        List<Pair<TreeNode, Integer>> queue = new ArrayList<>();
        // 将头节点入队 + 记录当前的编号
        queue.add(new Pair<>(root, 1)); // 将 root 设置为 编号1
        int ret = 0; // 记录最终结果

        while(!queue.isEmpty()) {
            // 先更新这一层的最大宽度
            Pair<TreeNode, Integer> t1 = queue.get(0);
            Pair<TreeNode, Integer> t2 = queue.get(queue.size() - 1);
            ret = Math.max(ret, t2.getValue() - t1.getValue() + 1);

            List<Pair<TreeNode, Integer>> temp = new ArrayList<>();
            for (Pair<TreeNode, Integer> q : queue) {
                // 拿到 当前的节点
                TreeNode node = q.getKey();
                Integer index = q.getValue();

                if (node.left != null) {
                    // 将左孩子加到队列中
                    temp.add(new Pair<TreeNode, Integer>(node.left, index * 2));
                }
                if (node.right != null) {
                    temp.add(new Pair<TreeNode, Integer>(node.right, index * 2 + 1));
                }
            }
            queue = temp;
        }
        return ret;
    }

    // 6. 在每个树行找最大值
    // https://leetcode.cn/problems/find-largest-value-in-each-tree-row/description/
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        if (root == null) {
            return ret;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int sz = queue.size();
            int temp = Integer.MIN_VALUE; // 最小值
            for (int i = 0; i < sz; i++) {
                TreeNode t = queue.poll(); // 将队首元素出队
                // 更新每层的最大节点 val
                temp = Math.max(temp, t.val);
                if (t.left != null) {
                    queue.add(t.left);
                }
                if (t.right != null) {
                    queue.add(t.right);
                }
            }
            ret.add(temp);
        }
        return ret;
    }
}
