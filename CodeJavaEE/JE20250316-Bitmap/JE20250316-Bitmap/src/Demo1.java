/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-03-16
 * Time: 8:31
 */
class ListNode2 {
    ListNode2 next; // 下一个节点的地址
    int val; // 数值域

    public ListNode2() {
        // 无参数的构造方法
    }

    public ListNode2(ListNode2 next, int val) {
        this.next = next;
        this.val = val;
    }
}
public class Demo1 {
    public ListNode2 reverseList(ListNode2 head) {
        // 创建一个哨兵位（没有实际的数值，对应的下一个才是真正的节点）
        // h → null  (我们的新排头兵)
        // 1 → 2 → 3 → null  (我们的原始列表)
        // p (指向 1)
        ListNode2 h = new ListNode2();
        ListNode2 p = head;
        while (p != null) {
            // 记录 p 节点的下一个节点的地址，方便遍历链表
            //q（指向了 2）
            ListNode2 q = p.next;
            // 将 p 节点指向下一个节点的地址改为 h 的下一个节点
            // 1 → null
            p.next = h.next;
            // 将 h 哨兵位 重新插在 p 结点之前
            // h → 1 → null
            h.next = p;
            // p (指向 2)
            p = q;
        }
        return h.next;
    }
    public static void main(String[] args) {
        System.out.println("haha");
        System.out.println("i am fine, thanks");
    }
}
