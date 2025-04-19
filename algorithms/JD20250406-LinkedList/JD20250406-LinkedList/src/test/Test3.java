package test;

//class ListNode {
//    int val;
//    ListNode next;
//    ListNode() {}
//    ListNode(int val) { this.val = val; }
//    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
//}

public class Test3 {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null || k <= 1) {
            return head;
        }
        
        ListNode newHead = new ListNode(0);
        newHead.next = head;
        ListNode prev = newHead;
        
        int count = 0;
        // 遍历链表，每当计数达到k的倍数时，翻转一组节点
        while (head != null) {
            count++;
            if (count % k == 0) {
                // 找到第k个节点后，翻转从prev.next到head的一组节点
                prev = reverseOneGroup(prev, head.next);
                head = prev.next;// 更新head为翻转后组的下一个节点
            } else {
                head = head.next;
            }
        }
        
        return newHead.next;
    }
    
    /**
     * 翻转从prev.next到end之间的节点（不包括end）
     * @param prev 翻转组的前一个节点
     * @param end 翻转组后一个节点
     * @return
     */
    private ListNode reverseOneGroup(ListNode prev, ListNode end) {
        // prev作为哨兵节点，它的next指针会在翻转过程中不断变化
        // first是待翻转组的第一个节点，翻转后会变成最后一个节点
        ListNode first = prev.next;  // 保存第一个节点，翻转后它将变成尾节点
        ListNode curr = first.next;  // 从第二个节点开始进行头插
        
        // 初始状态：prev -> first -> curr -> ... -> end
        // first的next指针保持不变，始终指向当前处理的curr节点
        first.next = end;  // 先将first节点与end相连，形成尾部链接
        
        // 头插法核心过程
        while (curr != end) {
            // 保存下一个待处理节点
            ListNode next = curr.next;
            
            // 头插：将curr插入到prev和first之间
            curr.next = prev.next;   // curr指向当前的头节点
            prev.next = curr;        // prev指向新的头节点(curr)
            
            // 移动到下一个待处理节点
            curr = next;
        }
        
        // 返回原first节点，它现在是这组的最后一个节点
        return first;
    }
    
    // 创建链表辅助方法
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
    
    // 打印链表辅助方法
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
        Test3 solution = new Test3();
        
        // 示例1: k=2 时的翻转
        System.out.println("示例1: k=2");
        int[] arr1 = {1, 2, 3, 4, 5};
        ListNode head1 = createList(arr1);
        System.out.print("输入: ");
        printList(head1);
        System.out.print("输出: ");
        printList(solution.reverseKGroup(head1, 2));
        System.out.println();
        
        // 示例2: k=3 时的翻转
        System.out.println("示例2: k=3");
        int[] arr2 = {1, 2, 3, 4, 5};
        ListNode head2 = createList(arr2);
        System.out.print("输入: ");
        printList(head2);
        System.out.print("输出: ");
        printList(solution.reverseKGroup(head2, 3));
        System.out.println();
        
        // 示例3: 测试用例 - 不均匀长度的链表和较大的k值
        System.out.println("示例3: 特殊情况测试");
        int[] arr3 = {-2, 11, 4, 13, 5, 20, 7};
        ListNode head3 = createList(arr3);
        System.out.print("输入: ");
        printList(head3);
        System.out.println("k=4");
        System.out.print("输出: ");
        printList(solution.reverseKGroup(head3, 4));
        System.out.println("预期结果: [13,4,11,-2,7,20,5]");
        
        // 示例4: 边界情况 - k=1 时不翻转
        System.out.println("\n示例4: k=1 (不翻转)");
        int[] arr4 = {11, -4, 13, 5};
        ListNode head4 = createList(arr4);
        System.out.print("输入: ");
        printList(head4);
        System.out.print("输出: ");
        printList(solution.reverseKGroup(head4, 1));
        System.out.println("预期结果: [11,-4,13,5]");
    }
}
