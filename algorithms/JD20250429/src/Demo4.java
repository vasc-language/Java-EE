import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-05-02
 * Time: 7:56
 */

class Node1 {
    public int val;
    public List<Node1> children;

    public Node1() {}

    public Node1(int _val) {
        val = _val;
    }

    public Node1(int _val, List<Node1> _children) {
        val = _val;
        children = _children;
    }
}
public class Demo4 {
    // 3. N 叉树的层序遍历
    // https://leetcode.cn/problems/n-ary-tree-level-order-traversal/description/
    public List<List<Integer>> levelOrder(Node1 root) {
        List<List<Integer>> ret = new ArrayList<>();
        if (root == null) {
            return ret;
        }
        // 申请一个队列
        Queue<Node1> queue = new LinkedList<>();
        // 将根节点入队列
        queue.add(root);

        while (!queue.isEmpty()) {
            int sz = queue.size(); // 记录当前队列中有多少个元素
            //再申请列表进行记录当前的层的节点
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < sz; i++) {
                // 在当前节点出队列时，让其子节点进队
                Node1 t = queue.poll();
                list.add(t.val);
                for (Node1 child : t.children) {
                    if (child != null) {
                        queue.add(child);
                    }
                }
            }
            // 一层结束，将这层信息放到 ret 中
            ret.add(list);
        }
        return ret;
    }
}
