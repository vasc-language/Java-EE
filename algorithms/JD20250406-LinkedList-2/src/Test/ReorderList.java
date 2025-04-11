package Test;

import java.util.List;
import java.util.ArrayList;

/**
 * Definition for singly-linked list.
 * 定义链表节点结构
 */
class ListNode {
    int val; // 节点存储的值
    ListNode next; // 指向下一个节点的指针

    ListNode() {
    } // 默认构造函数

    ListNode(int val) {
        this.val = val;
    } // 带值构造函数

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    } // 带值和下一个节点指针的构造函数
}

public class ReorderList {

    /**
     * 143. 重排链表
     * 给定一个单链表 L 的头节点 head ，单链表 L 表示为：
     * L₀ → L₁ → … → Lₙ₋₁ → Lₙ
     * 请将其重新排列后变为：
     * L₀ → Lₙ → L₁ → Lₙ₋₁ → L₂ → Lₙ₋₂ → …
     * 不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
     *
     * @param head 链表的头节点
     */
    public void reorderList(ListNode head) {
        // 边界条件处理：如果链表为空、只有一个节点或只有两个节点，则无需重排
        // 记忆：假如head == null 那么后面的 head.next == null 必然为空，使用逻辑与(&&) 就会导致空指针异常
        if (head == null || head.next == null || head.next.next == null) {
            return;
        }

        // 步骤 1: 找到链表的中间节点
        // 使用快慢指针法。慢指针每次走一步，快指针每次走两步。
        // 当快指针到达链表末尾时，慢指针正好指向链表的中间节点（或前半部分的最后一个节点）。
        ListNode slow = head;
        ListNode fast = head;
        // 循环条件保证 fast 和 fast.next 不为空，这样快指针可以安全地移动两步
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;       // 慢指针走一步
            fast = fast.next.next; // 快指针走两步
        }

        // 步骤 2: 反转链表的后半部分
        // slow 指向的是前半部分的最后一个节点
        // secondHalfStart 是后半部分的起始节点
        ListNode secondHalfStart = slow.next;
        // 断开前后两部分链表的连接
        slow.next = null;
        // 调用 reverseList 方法反转后半部分链表
        ListNode reversedSecondHalf = reverseList(secondHalfStart);

        // 步骤 3: 合并两个链表
        // firstHalf 指向前半部分的头节点
        ListNode firstHalf = head;
        // secondHalf 指向反转后的后半部分的头节点
        ListNode secondHalf = reversedSecondHalf;

        // 交替合并两个链表
        // 当 secondHalf 不为空时，说明后半部分还有节点需要合并
        while (secondHalf != null) {
            // 保存两个链表当前节点的下一个节点，防止链表断裂
            ListNode temp1 = firstHalf.next; // 保存前半部分当前节点的下一个节点
            ListNode temp2 = secondHalf.next; // 保存后半部分当前节点的下一个节点

            // 将前半部分的当前节点连接到后半部分的当前节点
            firstHalf.next = secondHalf;
            // 将后半部分的当前节点连接到前半部分原来的下一个节点
            // 这里需要判断 temp1 是否为 null，因为如果链表长度为偶数，
            // 前半部分可能比后半部分少一个节点，合并完最后一个 secondHalf 节点后，
            // temp1 可能是 null (即 firstHalf 已经是前半部分的最后一个节点)。
            // 在这种情况下，合并就完成了。
            // 但如果原链表长度为奇数，前后两半长度相等，temp1不会为null
            // 在这个标准的中点查找方法下，奇数长度时，前半部分节点数 = 后半部分节点数 + 1
            // 偶数长度时， 前半部分节点数 = 后半部分节点数
            // 所以，当secondHalf != null 时，temp1 必定不为 null (因为前半部分长度 >= 后半部分长度)
            // 因此 `if (temp1 == null) break;` 这句检查理论上可以省略，但加上更健壮。
            if (temp1 == null) { // 实际上这个条件在此算法下不会触发，因为前半部分长度总是>=后半部分
               break;
            }
            secondHalf.next = temp1;

            // 移动指针到下一个要处理的节点
            firstHalf = temp1;   // 移动前半部分的指针
            secondHalf = temp2; // 移动后半部分的指针
        }
        // 合并完成后，整个链表就已经按要求重排好了，head 仍然是重排后链表的头节点
    }

    /**
     * 反转一个单链表
     *
     * @param head 要反转的链表的头节点
     * @return 反转后链表的头节点
     */
    private ListNode reverseList1(ListNode head) {
        ListNode prev = null; // 前一个节点的指针，初始为 null
        ListNode current = head; // 当前节点的指针，初始为头节点
        while (current != null) {
            ListNode nextTemp = current.next; // 临时保存当前节点的下一个节点
            current.next = prev;         // 将当前节点的 next 指针指向前一个节点，实现反转
            prev = current;              // 将 prev 指针向前移动到当前节点
            current = nextTemp;          // 将 current 指针向前移动到原来的下一个节点
        }
        // 循环结束后，prev 指向的就是原链表的最后一个节点，即反转后链表的头节点
        return prev;
    }

    private ListNode reverseList(ListNode head) {
        // 使用头插法反转链表
        if (head == null || head.next == null) {
            return head;
        }
        // 2 -> 4 -> 3（举个例子）
        // 创建哨兵节点：作用就是始终指向反转列表的头部（不会对它进行操作）
        ListNode dummy = new ListNode(0); 
        ListNode current = head; // 2
        
        while (current != null) {
            
            // 保存下一个节点
            ListNode nextNode = current.next; // 4
            // 将当前节点插入到哨兵节点之后
            current.next = dummy.next;
            // 作用就是始终指向反转列表的头部：让 current（2）成为新的头部
            dummy.next = current;
            // 移动到原链表的下一个节点
            current = nextNode; // current == 4
        }
        
        return dummy.next;
    }

    /**
     * 辅助方法：根据数组创建链表（尾插法创建链表）
     */
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

    /**
     * 辅助方法：打印链表
     */
    public static void printLinkedList(ListNode head) {
        List<Integer> list = new ArrayList<>();
        ListNode current = head;
        while (current != null) {
            list.add(current.val);
            current = current.next;
        }
        System.out.println(list);
    }

    // 主函数，用于测试
    public static void main(String[] args) {
        ReorderList solution = new ReorderList();

        // 测试用例 1
        System.out.println("测试用例 1:");
        int[] arr1 = {1, 2, 3, 4};
        ListNode head1 = createLinkedList(arr1);
        System.out.print("原始链表: ");
        printLinkedList(head1); // 输出: [1, 2, 3, 4]
        solution.reorderList(head1);
        System.out.print("重排后链表: ");
        printLinkedList(head1); // 预期输出: [1, 4, 2, 3]
        System.out.println("--------------------");

        // 测试用例 2
        System.out.println("测试用例 2:");
        int[] arr2 = {1, 2, 3, 4, 5};
        ListNode head2 = createLinkedList(arr2);
        System.out.print("原始链表: ");
        printLinkedList(head2); // 输出: [1, 2, 3, 4, 5]
        solution.reorderList(head2);
        System.out.print("重排后链表: ");
        printLinkedList(head2); // 预期输出: [1, 5, 2, 4, 3]
        System.out.println("--------------------");

        // 测试用例 3 - 只有一个节点
        System.out.println("测试用例 3:");
        int[] arr3 = {1};
        ListNode head3 = createLinkedList(arr3);
        System.out.print("原始链表: ");
        printLinkedList(head3); // 输出: [1]
        solution.reorderList(head3);
        System.out.print("重排后链表: ");
        printLinkedList(head3); // 预期输出: [1]
        System.out.println("--------------------");

        // 测试用例 4 - 只有两个节点
        System.out.println("测试用例 4:");
        int[] arr4 = {1, 2};
        ListNode head4 = createLinkedList(arr4);
        System.out.print("原始链表: ");
        printLinkedList(head4); // 输出: [1, 2]
        solution.reorderList(head4);
        System.out.print("重排后链表: ");
        printLinkedList(head4); // 预期输出: [1, 2]
        System.out.println("--------------------");
    }
}