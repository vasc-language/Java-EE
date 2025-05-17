package Test;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        // 场景1：所有节点诚实
        List<Node> nodes1 = Arrays.asList(
            new Node("NodeA1", false),
            new Node("NodeA2", false),
            new Node("NodeA3", false),
            new Node("NodeA4", false)
        );
        System.out.println("== Scenario 1: All honest nodes ==");
        consensusRound(nodes1, "tx1;tx2");

        // 场景2：领导者为恶意节点
        List<Node> nodes2 = Arrays.asList(
            new Node("NodeB1", true),  // 恶意领导者
            new Node("NodeB2", false),
            new Node("NodeB3", false),
            new Node("NodeB4", false)
        );
        System.out.println("== Scenario 2: Malicious leader ==");
        consensusRound(nodes2, "tx3;tx4");

        // 场景3：一个跟随节点恶意但非领导者
        List<Node> nodes3 = Arrays.asList(
            new Node("NodeC1", false),
            new Node("NodeC2", false),
            new Node("NodeC3", true),  // 恶意跟随者
            new Node("NodeC4", false)
        );
        System.out.println("== Scenario 3: Malicious follower ==");
        consensusRound(nodes3, "tx5;tx6");
    }

    // 模拟一次共识轮次
    public static void consensusRound(List<Node> nodes, String data) {
        int n = nodes.size();
        int threshold = 2 * n / 3 + 1;
        Node leader = nodes.get(0);               // 简化：第一个节点为领导者
        Block proposed = leader.proposeBlock(data);
        int votes = 0;
        for (Node node : nodes) {
            if (node.validateBlock(proposed)) {
                votes++;
            }
        }
        System.out.println("Votes: " + votes + " / threshold " + threshold);
        if (votes >= threshold) {
            for (Node node : nodes) {
                node.addBlock(proposed);
            }
            System.out.println("Block " + proposed.getIndex() + " committed.\n");
        } else {
            System.out.println("Block " + proposed.getIndex() + " commit failed.\n");
        }
    }
}