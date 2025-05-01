import java.util.Stack;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-04-29
 * Time: 7:00
 */
public class Demo1 {
    // 1. 验证栈序列
    // https://leetcode.cn/problems/validate-stack-sequences/description/
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack<>();
        int i = 0; // 为了遍历 popped 数组下标
        int n = pushed.length; // pushed 数组的长度

        // 将 pushed[x] 推进 stack 中去，将popped[i]
        for (int x : pushed) {
            stack.push(x);
            /**
             * 为什么这个判断有效？因为它模拟了栈操作的一个基本原则：只有栈顶元素才能被弹出
             * - 如果stack.peek() == popped[i] 这意味着当前栈顶的元素正好是 popped 序列指示的下一个出栈的元素。那么，我们应该执行出栈
             *   操作（stack.pop()），并将期望值出栈的索引 i 向后移动一位（i + 1）表示我们成功匹配并移除了一个元素，接下来要看 popped
             *   序列中的下一个位置
             * - 如果 stack.peek() 不等于 popped[i]，这意味着当前栈顶元素不是我们期望的下一个出栈元素。根据栈 "先进后出" 的规则，我们
             *   不能跳过栈顶元素去弹出下面的元素。因此，我们 不能 在这个时候执行出栈操作。我们必须继续从 pushed 序列中获取下一个元素并压
             *   入栈中，直到栈顶元素变成我们期望的 popped[i] 为止。
             */
            while (!stack.isEmpty() && stack.peek() == popped[i]) {
                stack.pop();
                i++;
            }
        }
        // 看 i 是否遍历完popped 数组，证明推进 push 和弹出 pop 操作序列正确
        return i == n;
    }


}
