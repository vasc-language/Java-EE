import java.util.PriorityQueue;

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
    // 4. K个一组翻转链表
    // https://leetcode.cn/problems/reverse-nodes-in-k-group/description/
    public ListNode reverseKGroup(ListNode head, int k) {
        // 链表为空 || 链表只有一个节点 || k<=1 自身反转没有意义
        if (head == null || head.next == null || k <= 1) {
            return head;
        }

        // 1. 申请一个哨兵位
        ListNode newHead = new ListNode(0);
        newHead.next = head;
        ListNode prev = newHead;

        // 2. 进行计数，每次遍历到 k 的倍数，就进行翻转
        int count = 0;
        while (head != null) {
            count++;
            if (count % k == 0) {
                // [prev, head] [head.next, end]
                prev = reverseOneGroup(prev, head.next);
                // [head, prev] [prev.next, end]
                head = prev.next;
            } else {
                head = head.next; //  没有达到 k 个节点一组，就继续往下走
            }
        }
        return newHead.next;
    }
    // prev 为哨兵位
    // end 为下一次排序链表的头节点
    private ListNode reverseOneGroup(ListNode prev, ListNode end) {
        ListNode first = prev.next; // 真正的头节点
        ListNode cur = first.next; // 从第二个开始
        // 让头节点和下一次排序链表的头节点连接
        first.next = end;

        while (cur != end) {
            ListNode next = cur.next;
            cur.next = prev.next;
            prev.next = cur;
            cur = next;
        }
        return first;
    }

    // 3. 合并K个升序链表
    // https://leetcode.cn/problems/merge-k-sorted-lists/description/
    // 方法一：使用优先级队列
    public ListNode mergeKLists2(ListNode[] lists) {
        // 1. 创建一个小根堆，从而得到栈顶元素一定是最小的
        PriorityQueue<ListNode> queue = new PriorityQueue<>(
            (a, b) -> a.val - b.val
        );

        // 2. 将链表中所有节点都放到栈中
        for (ListNode node : lists) {
            // 结束条件（链表结尾肯定是 null）
            if (node != null) {
                queue.offer(node);
            }
        }

        // 3. 创建一个哨兵位
        // 旨在任何时候都指向头节点
        ListNode newHead = new ListNode(0);
        ListNode tail = newHead;

        // 4. 取出栈顶元素（一定是当前所有节点最小的），尾插在新的链表中
        while (!queue.isEmpty()) {
            ListNode cur =  queue.poll(); // 取出栈顶元素
            // 使用尾插法
            tail.next = cur;
            tail = tail.next;

            // 如果当前节点有下一个节点，将其加入队列
            // 这一步相当于 cur 节点往下走
            if (cur.next != null) {
                queue.offer(cur.next);
            }
        }

        return newHead.next;
    }

    // 方法二：使用分治法来求
    public ListNode mergeKLists(ListNode[] lists) {
        return merge(lists, 0, lists.length - 1);
    }

    private ListNode merge(ListNode[] lists, int left, int right) {
        if (left > right) return null;
        if (left == right) return lists[left]; // 递归的条件

        // 使用分治-递归的策略
        // 1. 将链表分成两部分
        // [left, mid] [mid + 1, right]
        int mid = left + (right - left) / 2;

        // 2. 进行归并排序
        ListNode l1 = merge(lists, left, mid);
        ListNode l2  =merge(lists, mid + 1, right);

        // 3. （合并两个有序链表）进行合并
        return mergeTwoLists(l1, l2);
    }

    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // 其中一个链表没有，就不需要合并，直接返回另一个链表即可
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        // 创建一个哨兵位，目的是任何时候都指向头节点
        ListNode newHead = new ListNode(0);
        ListNode cur1 = l1, cur2 = l2;
        ListNode prev = newHead; // 让 prev 进行尾插

        while (cur1 != null && cur2 != null) {
            if (cur1.val <= cur2.val) {
                prev.next = cur1;
                prev = cur1;
                cur1 = cur1.next;
            } else {
                prev.next = cur2;
                prev = cur2;
                cur2 = cur2.next;
            }
        }

        // 为什么使用 if 而不使用 while
        if (cur1 != null) prev.next = cur1;
        if (cur2 != null) prev.next = cur2;

        return newHead.next;
    }
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