package org.example.aiagent.chatmemory;


import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import org.objenesis.strategy.StdInstantiatorStrategy;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.messages.Message;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-05-15
 * Time: 21:23
 */

/**
 * 基于文件持久化的对话记忆
 */
public class FileBasedChatMemory implements ChatMemory {

    private final String BASE_DIR;
    private static final Kryo kryo = new Kryo(); // Kryo 序列化器

    /**
     * 配置 kryo 序列器
     * - 将对话历史（List<Message>）序列化到 .kryo 文件中
     * - 从文件中可靠地恢复对话历史
     * - 处理Spring AI 框架中各种各样的消息类型
     * - 实现基于文件的持久化存储，保持对话上下文连续性
     */
    static {
        kryo.setRegistrationRequired(false); // 不需要预先注册，允许序列化任何数据类型
        // 设置实例化策略
        kryo.setInstantiatorStrategy(new StdInstantiatorStrategy());
    }

    // 构造对象时，指定文件保存目录
    public FileBasedChatMemory(String dir) {
        this.BASE_DIR = dir;
        File baseDir = new File(dir);
        if (!baseDir.exists()) {
            baseDir.mkdirs();
        }
    }

    /**
     * 将新信息添加到现有的会话消息列表中
     * - 先获取指定ID的会话历史（如果不存在则创建新的空列表）
     * - 将新消息添加到已有的历史中
     * - 保存 更新后完整历史 到文件
     * @param conversationId
     * @param messages
     */
    @Override
    public void add(String conversationId, List<Message> messages) {
        List<Message> conversationMessages = getOrCreateConversation(conversationId);
        conversationMessages.addAll(messages);
        saveConversation(conversationId, conversationMessages);
    }

    /**
     * 从完整的对话历史中，只返回最后的N条消息
     * @param conversationId
     * @param lastN
     * @return
     */
    @Override
    public List<Message> get(String conversationId, int lastN) {
        List<Message> allMessages = getOrCreateConversation(conversationId);
        return allMessages.stream()
                // 计算需要跳过的消息数量（确保跳过的数量不为负数）
                .skip(Math.max(0, allMessages.size() - lastN))
                // 将筛选后的流元素收集到新的 List 中返回
                .toList();
    }

    /**
     * 删除会话文件
     * @param conversationId
     */
    @Override
    public void clear(String conversationId) {
        File file = getConversationFile(conversationId);
        if (file.exists()) {
            file.delete();
        }
    }

    /**
     * 读取会话历史
     * @param conversationId
     * @return
     */
    private List<Message> getOrCreateConversation(String conversationId) {
        File file = getConversationFile(conversationId);
        List<Message> messages = new ArrayList<>();
        if (file.exists()) { // 无效返回 false
            try (Input input = new Input(new FileInputStream(file))) {
                messages = kryo.readObject(input, ArrayList.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return messages;
    }

    /**
     * 保存会话内容
     * @param conversationId
     * @param messages
     */
    private void saveConversation(String conversationId, List<Message> messages) {
        File file = getConversationFile(conversationId);
        try (Output output = new Output(new FileOutputStream(file))) {
            kryo.writeObject(output, messages);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据会话ID构建对应的存储对象
     * 返回一个表示该会话存储文件的 File 对象
     * @param conversationId
     * @return
     */
    private File getConversationFile(String conversationId) {
        // 将BASE_DIR(基础目录)和会话ID组合，并添加“.kryo” 扩展名
        // 例如：BASE_DIR 是 "/conversations" conversationId 是 "userId123" 则返回的文件路径为："/conversations/userId123.kryo"
        return new File(BASE_DIR, conversationId + ".kryo");
    }
}

