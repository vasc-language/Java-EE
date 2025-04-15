package test;

import java.util.PriorityQueue;

// 思路二：使用优先级队列，找出当前最小的元素，尾插到新的链表中，从而实现
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}


public class Test2 {
    public ListNode mergeKLists(ListNode[] lists) {
        // 创建优先级队列，按节点值从小到大排序
        // 小根堆
        PriorityQueue<ListNode> queue = new PriorityQueue<>((a, b) -> a.val - b.val);
        
        // 将所有链表的头节点加入队列
        for (ListNode head : lists) {
            if (head != null) {
                queue.offer(head);
            }
        }
        
        // 创建哨兵节点
        ListNode newHead = new ListNode(0);
        ListNode tail = newHead;
        
        // 依次取出最小节点并连接
        while (!queue.isEmpty()) {
            // 取出当前最小节点
            ListNode curr = queue.poll();
            tail.next = curr;
            tail = tail.next;
            
            // 如果当前节点有下一个节点，将其加入队列
            if (curr.next != null) {
                queue.offer(curr.next);
            }
        }
        
        return newHead.next;
    }
    
    // 创建链表的辅助方法
    private static ListNode createList(int[] values) {
        if (values == null || values.length == 0) {
            return null;
        }
        
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        
        for (int val : values) {
            current.next = new ListNode(val);
            current = current.next;
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
        Test2 solution = new Test2();
        
        // 示例1: lists = [[1,4,5],[1,3,4],[2,6]]
        System.out.println("示例1:");
        ListNode[] lists1 = new ListNode[3];
        lists1[0] = createList(new int[]{1,4,5});
        lists1[1] = createList(new int[]{1,3,4});
        lists1[2] = createList(new int[]{2,6});
        
        System.out.print("输入: lists = [[1,4,5],[1,3,4],[2,6]]\n输出: ");
        printList(solution.mergeKLists(lists1));
        System.out.println();
        
        // 示例2: lists = []
        System.out.println("示例2:");
        ListNode[] lists2 = new ListNode[0];
        
        System.out.print("输入: lists = []\n输出: ");
        printList(solution.mergeKLists(lists2));
        System.out.println();
        
        // 示例3: lists = [[]]
        System.out.println("示例3:");
        ListNode[] lists3 = new ListNode[1];
        lists3[0] = null;
        
        System.out.print("输入: lists = [[]]\n输出: ");
        printList(solution.mergeKLists(lists3));
        
        // 额外示例: 不等长链表
        System.out.println("\n额外示例:");
        ListNode[] lists4 = new ListNode[3];
        lists4[0] = createList(new int[]{1,10,20});
        lists4[1] = createList(new int[]{4,5,6,7});
        lists4[2] = createList(new int[]{3,9});
        
        System.out.print("输入: lists = [[1,10,20],[4,5,6,7],[3,9]]\n输出: ");
        printList(solution.mergeKLists(lists4));
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        // 链表为空 || 链表只有一个节点 || k <= 1 自己翻自己（没有自已）
        if (head == null || head.next == null || k <= 1) {
            return head;
        }

        // new 一个哨兵位
        ListNode newHead = new ListNode(0);
        newHead.next = head;
        ListNode prev = newHead; // prev 作为头节点的前一个节点

        int count = 0;
        // 将 k 节点分为一组链表进行翻转
        while (head != null) {
            count++;
            if (count % k == 0) {
                // [prev, head] [head.next, end]
                prev = reverseOneGroup(prev, head.next);
                // [head, prev] [prev.next, end]
                head = prev.next; // 逆序完之后，就开始进行下一个段的链表的逆序
            } else {
                head = head.next; // 直到找到 k 个节点，head 才停下来
            }
        }

        return newHead.next;
    }

    // prev 刚开始是作为哨兵位传过来的
    // end 为下一个段的链表的头节点
    private ListNode reverseOneGroup(ListNode prev, ListNode end) {
        ListNode first = prev.next;
        ListNode cur = first.next; // 从第二个节点开始插（自身尾插在自身）

        first.next = end; // 

        while (cur != end) {
            ListNode next = cur.next;
            cur.next = prev.next;
            prev.next = cur;
            cur = next;
        }

        return first;
    }
}
