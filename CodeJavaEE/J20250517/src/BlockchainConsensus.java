import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BlockchainConsensus {
    static class Node {
        int id;
        int votes;

        Node(int id) {
            this.id = id;
            this.votes = 0;
        }
    }

    public static Node consensusAlgorithm(List<Node> nodes, int threshold) {
        Random random = new Random();
        while (true) {
            for (Node node : nodes) {
                node.votes = 0;
            }

            for (Node voter : nodes) {
                int randomIndex = random.nextInt(nodes.size());
                nodes.get(randomIndex).votes++;
            }

            int maxVotes = 0;
            Node leader = null;
            for (Node node : nodes) {
                if (node.votes > maxVotes) {
                    maxVotes = node.votes;
                    leader = node;
                }
            }

            // 检查是否达到阈值
            if (maxVotes >= threshold) {
                return leader;
            }
        }
    }

    public static void main(String[] args) {
        List<Node> nodes = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            nodes.add(new Node(i));
        }
        int threshold = 6;
        Node leader = consensusAlgorithm(nodes, threshold);
        System.out.println("共识算法选出的领导者：" + leader.id);
    }
}