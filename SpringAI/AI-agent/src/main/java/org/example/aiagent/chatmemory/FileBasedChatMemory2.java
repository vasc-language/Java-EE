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
public class FileBasedChatMemory2 implements ChatMemory {
    private final String BASE_DIR;
    private static final Kryo kryo = new Kryo(); // Kryo 序列化器

    /**
     * 初始化：创建目录结构，配置 Kryo 序列化器
     * @param baseDir
     */
    public FileBasedChatMemory2(String baseDir) {
        this.BASE_DIR = baseDir;
        File file = new File(baseDir);
        if (!file.exists()) {
            file.mkdirs(); // 不存在则创建一个新的文件夹
        }
    }

    /**
     * - 使用 Kryo 高性能序列化库将 Java 对象转换为二进制数据
     * - 配置为不要预注册类，提高灵活性
     * - 使用 StdInstantiatorStrategy 可绕过构造方法创建对象
     */
    static {
        kryo.setRegistrationRequired(false);
        kryo.setInstantiatorStrategy(new StdInstantiatorStrategy());
    }

    /**
     * 添加消息：新消息追加到已有历史，整体重新序列化保存
     * @param conversationId
     * @param messages
     */
    @Override
    public void add(String conversationId, List<Message> messages) {
        List<Message> messageList = getOrCreateConversation(conversationId);
        messageList.addAll(messages);
        saveConversation(conversationId, messages);
    }

    /**
     * 获取消息：反序列化文件内容，筛选出最新的的N条消息
     * @param conversationId
     * @param lastN
     * @return
     */
    @Override
    public List<Message> get(String conversationId, int lastN) {
        List<Message> messageList = getOrCreateConversation(conversationId);
        return messageList.stream()
                .skip(Math.max(0, messageList.size() - lastN))
                .toList();
    }

    /**
     * 删除会话：直接删除对应的文件
     * @param conversationId
     */
    @Override
    public void clear(String conversationId) {
        File conversationFile = getConversationFile(conversationId);
        if (conversationFile.exists()) {
            conversationFile.delete(); // 删除历史会话
        }
    }

    /**
     * 读取历史会话
     * @param conversationId
     * @return
     */
    private List<Message> getOrCreateConversation(String conversationId) {
        File conversationFile = getConversationFile(conversationId);
        List<Message> messages = new ArrayList<>();
        if (conversationFile.exists()) {
            try (Input input = new Input(new FileInputStream(conversationFile))) {
                messages = kryo.readObject(input, ArrayList.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return messages;
    }

    /**
     * 保存历史会话
     * @param conversationId
     * @return
     */
    private void saveConversation(String conversationId, List<Message> messages) {
        File conversationFile = getConversationFile(conversationId);
        try (Output output = new Output(new FileOutputStream(conversationFile))) {
            kryo.writeObject(output, messages);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private File getConversationFile(String conversationId) {
        return new File(BASE_DIR, conversationId + ".kryo"); // 这是存储消息的文件
    }
}

