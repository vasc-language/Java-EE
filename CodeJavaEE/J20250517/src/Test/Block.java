package Test;

import java.util.ArrayList;
import java.util.List;
import java.security.MessageDigest;

public class Block {
    private int index;             // 区块高度
    private long timestamp;        // 时间戳
    private String data;           // 交易数据（可用分号分隔多笔交易）
    private String previousHash;   // 前一区块哈希
    private String hash;           // 本区块哈希
    private String merkleRoot;     // 本区块的Merkle根
    private int nonce;             // 随机数（PoW可用，此处未使用）
    private boolean isValidBlock;  // 区块有效性标志（模拟恶意行为）

    // 构造函数：创建新区块
    public Block(int index, long timestamp, String data, String previousHash, boolean isValid) {
        this.index = index;
        this.timestamp = timestamp;
        this.data = data;
        this.previousHash = previousHash;
        this.nonce = 0;
        this.isValidBlock = isValid;
        this.merkleRoot = calculateMerkleRoot(data);
        this.hash = calculateHash();
    }
    // 重载构造函数：默认为有效区块
    public Block(int index, long timestamp, String data, String previousHash) {
        this(index, timestamp, data, previousHash, true);
    }
    // 计算Merkle根（简单的对交易数据进行两两哈希聚合）
    public String calculateMerkleRoot(String data) {
        String[] transactions = data.split(";");
        List<String> list = new ArrayList<>();
        for (String tx : transactions) {
            list.add(applySha256(tx));
        }
        if (list.size() == 0) return "";
        while (list.size() > 1) {
            List<String> newList = new ArrayList<>();
            for (int i = 0; i < list.size(); i += 2) {
                if (i + 1 < list.size()) {
                    newList.add(applySha256(list.get(i) + list.get(i+1)));
                } else {
                    newList.add(applySha256(list.get(i) + list.get(i)));
                }
            }
            list = newList;
        }
        return list.get(0);
    }
    // 计算区块哈希（使用SHA-256）
    public String calculateHash() {
        String record = index + Long.toString(timestamp) + data + previousHash + nonce;
        return applySha256(record);
    }
    // SHA-256工具方法
    public static String applySha256(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(input.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    // 访问器和输出
    public String getPreviousHash() { return previousHash; }
    public String getHash() { return hash; }
    public boolean isValidBlock() { return isValidBlock; }
    public int getIndex() { return index; }
    @Override
    public String toString() {
        return "Block{index=" + index + ", prevHash='" + previousHash + "', merkleRoot='" + merkleRoot + "', hash='" + hash + "'}";
    }
}