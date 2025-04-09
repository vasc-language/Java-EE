/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
class Solution {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // 申请哨兵位
        ListNode newHead = new ListNode(0);
        newHead.next = head;
        ListNode prev = newHead, cur = prev.next, next = cur.next, nnext = cur.next;
        while (cur != null && next != null) {
            // 修改指针的指向
            prev.next = next;
            next.next = cur;
            cur.next = nnext;

            // 指针进行前进，但不是一步一步的往前走
            prev = cur;
            cur = nnext;
            if (cur != null) {
                next = cur.next;
            }
            if (next != null) {
                nnext = next.next;
            }
        }
        return newHead.next;
    }
    // 1. 两数相加
    // https://leetcode.cn/problems/add-two-numbers/description/
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode newHead = new ListNode(0); // 哨兵位
        ListNode cur1 = l1, cur2 = l2;
        ListNode prev = newHead;
        int t = 0;

        while (cur1 != null || cur2 != null || t != 0) {
            // 先加第一个链表
            if (cur1 != null) {
                t += cur1.val;
                cur1 = cur1.next;
            }

            // 第二个链表
            if (cur2 != null) {
                t += cur2.val;
                cur2 = cur2.next;
            }

            // 将 t 的个位数添加到 新的链表中，使用尾插法
            prev.next = new ListNode(t % 10);
            prev = prev.next;
            // 将 t 的十位数进位
            t /= 10;
        }
        return newHead.next;
    }
}