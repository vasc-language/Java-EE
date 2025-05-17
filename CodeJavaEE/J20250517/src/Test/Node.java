package Test;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private String nodeId;               // 节点标识
    private List<Block> blockchain;      // 本地链
    private boolean isFaulty;            // 是否为恶意节点（拜占庭）
    
    public Node(String nodeId, boolean isFaulty) {
        this.nodeId = nodeId;
        this.isFaulty = isFaulty;
        this.blockchain = new ArrayList<>();
        // 添加创世区块
        Block genesis = new Block(0, System.currentTimeMillis(), "Genesis Block", "0");
        blockchain.add(genesis);
    }
    // 领导者生成新区块（恶意节点可创建无效区块）
    public Block proposeBlock(String data) {
        int newIndex = blockchain.size();
        long timestamp = System.currentTimeMillis();
        String prevHash = blockchain.get(newIndex - 1).getHash();
        boolean valid = !isFaulty ? true : false;
        Block newBlock = new Block(newIndex, timestamp, data, prevHash, valid);
        System.out.println(nodeId + " proposed Block " + newIndex + " (valid=" + valid + ")");
        return newBlock;
    }
    // 验证并投票：返回true表示投票支持该区块
    public boolean validateBlock(Block block) {
        // 如果区块被标记为无效且自己为诚实节点，则拒绝
        if (!block.isValidBlock() && !this.isFaulty) {
            System.out.println(nodeId + ": Reject invalid Block " + block.getIndex());
            return false;
        }
        // 恶意节点签任何区块（简化模拟）
        if (this.isFaulty) {
            System.out.println(nodeId + ": (恶意) Approve Block " + block.getIndex());
            return true;
        }
        // 校验与本地链的衔接
        Block last = blockchain.get(blockchain.size() - 1);
        if (!last.getHash().equals(block.getPreviousHash())) {
            System.out.println(nodeId + ": Block " + block.getIndex() + " prevHash mismatch");
            return false;
        }
        // 校验哈希值
        if (!block.getHash().equals(block.calculateHash())) {
            System.out.println(nodeId + ": Block " + block.getIndex() + " hash invalid");
            return false;
        }
        System.out.println(nodeId + ": Validates Block " + block.getIndex());
        return true;
    }
    // 提交区块到本地链
    public void addBlock(Block block) {
        blockchain.add(block);
        System.out.println(nodeId + ": Added Block " + block.getIndex());
    }
}