import java.awt.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-03-16
 * Time: 8:21
 */
class ListNode {
    ListNode next;
    int val;
    public ListNode(){};
    public ListNode(int val) {
        this.val = val;
    }
}
public class Main {
    public ListNode reverseList(ListNode head) {
        ListNode h = new ListNode();
        ListNode p = head;
        while (p != null) {
            ListNode q = p.next;
            p.next = h.next;
            h.next = p;
            p = q;
        }
        return h.next;
    }
    public boolean isUnique(String astr) {
        // 利用鸽巢原理 来做优化
        if (astr.length() > 26) {
            return false;
        }
        int bitMap = 0; // 位图原理：比特位 有 1 表示已经存在 0 表示还未存在
        for (int i = 0; i < astr.length(); i++) {
            // 判断 bit 位是否已经标志上 1 了
            int x = astr.charAt(i) - 'a';
            if (((bitMap >> x) & 1) == 1) {
                // 表示 bitMap >> x == 0 此时 未标志上
                return false;
            }
            // 把当前字符加⼊到位图中
            bitMap |= (1 << x);
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}