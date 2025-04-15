package test;

import java.util.PriorityQueue;

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

public class Test {
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
    // 
    public ListNode mergeKLists(ListNode[] lists) {
        return merge(lists, 0, lists.length - 1);
    }

    private ListNode merge(ListNode[] lists, int left, int right) {
        if (left > right) return null;
        // 递归结束条件是：
        if (left == right) return lists[left]; 

        // 使用算法：分治法
        // 1. 将链表分成两个
        // [left, mid] [mid + 1, right]
        int mid = left + (right - left) / 2;

        // 2. 进行递归排序 
        ListNode l1 = merge(lists, left, mid); // 递归左边
        ListNode l2 = merge(lists, mid + 1, right); // 递归右边

        // 3. 合并两个有序链表
        return mergeTwoLists(l1, l2);
    }

    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // 存在一个链表，另外的链表为 null
        // 直接返回 存在节点链表即可，也无需在进行遍历
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        // 生成一个哨兵位：旨在任何时候都指向头节点，最后返回头节点即可（newHead.next）
        ListNode newHead = new ListNode(0);
        ListNode cur1 = l1, cur2 = l2;
        ListNode prev = newHead; // 这个节点是用来尾插用的
        while (cur1 != null && cur2 != null) {
            // 哪个节点小，那个就放前面
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

        // 连接剩下的未被遍历过的节点
        if (cur1 != null) prev.next = cur1;
        if (cur2 != null) prev.next = cur2;

        return newHead.next;
    }
    
    // 创建链表的辅助方法
    private static ListNode createList(int[] vals) {
        if (vals == null || vals.length == 0) return null;
        
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        for (int val : vals) {
            curr.next = new ListNode(val);
            curr = curr.next;
        }
        return dummy.next;
    }
    
    // 打印链表的辅助方法
    private static void printList(ListNode head) {
        System.out.print("[");
        while (head != null) {
            System.out.print(head.val);
            if (head.next != null) {
                System.out.print(",");
            }
            head = head.next;
        }
        System.out.println("]");
    }
    
    public static void main(String[] args) {
        Test solution = new Test();
        
        // 示例1: lists = [[1,4,5],[1,3,4],[2,6]]
        System.out.println("示例1:");
        ListNode[] lists1 = new ListNode[3];
        lists1[0] = createList(new int[]{1,4,5});
        lists1[1] = createList(new int[]{1,3,4});
        lists1[2] = createList(new int[]{2,6});
        System.out.print("输入: ");
        System.out.println("lists = [[1,4,5],[1,3,4],[2,6]]");
        System.out.print("输出: ");
        printList(solution.mergeKLists(lists1));
        
        // 示例2: lists = []
        System.out.println("\n示例2:");
        ListNode[] lists2 = new ListNode[0];
        System.out.print("输入: ");
        System.out.println("lists = []");
        System.out.print("输出: ");
        printList(solution.mergeKLists(lists2));
        
        // 示例3: lists = [[]]
        System.out.println("\n示例3:");
        ListNode[] lists3 = new ListNode[1];
        lists3[0] = null;
        System.out.print("输入: ");
        System.out.println("lists = [[]]");
        System.out.print("输出: ");
        printList(solution.mergeKLists(lists3));
    }
}
