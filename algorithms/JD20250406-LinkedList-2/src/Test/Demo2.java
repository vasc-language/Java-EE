package Test;

import java.util.ArrayList;
import java.util.List;

// Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

public class Demo2 {
    public void reorderList(ListNode head) {
        // 使用逻辑或（||）只要有一个条件成真，就可执行这里的代码
        if (head == null || head.next == null || head.next.next == null) {
            return;
        }
        // 1. 将链表分成两部分[head, slow] [slow +1, 结尾]
        // 使用快慢指针
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // 此时已经将链表分成两部分了

        // 2. 将链表后边部分[slow, 结尾]进行逆序操作
        // 使用头插法
        ListNode head2 = new ListNode(0); // 哨兵位：作用就是任何时候都指向头节点
        ListNode cur = slow.next;
        slow.next = null; // 将第一部分和第二部分分割开
        // 开始逆序
        while (cur != null) {
            ListNode next = cur.next; // 将 cur 下一个指针存放好
            cur.next = head2.next; // cur 下一个指向 (哨兵位的下一个节点) 规定
            head2.next = cur;
            cur = next; // cur 在原链表[slow, 结尾]中继续往下走
        }

        // 3. 合并两个有序链表
        ListNode cur1 = head, cur2 = head2.next;
        ListNode ret = new ListNode(0); // 新的哨兵位
        ListNode prev = ret;
        while (cur1 != null) {
            // 进行尾插操作即可
            // 先放第一部分的链表
            prev.next = cur1;
            prev = cur1; // 使哨兵位指向 cur1
            cur1 = cur1.next;

            if (cur2 != null) {
                prev.next = cur2;
                prev = cur2; // 使哨兵位指向 cur2
                cur2 = cur2.next;
            }
        }
    }

    public static ListNode createLinkedList(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        ListNode head = new ListNode(arr[0]);
        ListNode current = head;
        for (int i = 1; i < arr.length; i++) {
            current.next = new ListNode(arr[i]);
            current = current.next;
        }
        return head;
    }

    public static void printLinkedList(ListNode head) {
        List<Integer> list = new ArrayList<>();
        ListNode current = head;
        while (current != null) {
            list.add(current.val);
            current = current.next;
        }
        System.out.println(list);
    }

    public static void main(String[] args) {
        Demo2 solution = new Demo2();

        // 测试用例1
        System.out.println("测试用例1:");
        int[] arr1 = {1, 2, 3, 4};
        ListNode head1 = createLinkedList(arr1);
        System.out.print("原始链表: ");
        printLinkedList(head1); // 输出: [1, 2, 3, 4]
        solution.reorderList(head1);
        System.out.print("重排后链表: ");
        printLinkedList(head1); // 预期输出: [1, 4, 2, 3]
        System.out.println("--------------------");

        // 测试用例2
        System.out.println("测试用例2:");
        int[] arr2 = {1, 2, 3, 4, 5};
        ListNode head2 = createLinkedList(arr2);
        System.out.print("原始链表: ");
        printLinkedList(head2); // 输出: [1, 2, 3, 4, 5]
        solution.reorderList(head2);
        System.out.print("重排后链表: ");
        printLinkedList(head2); // 预期输出: [1, 5, 2, 4, 3]
        System.out.println("--------------------");
    }
}
