package org.example.jd20250417springbootdemo.service;

import org.example.jd20250417springbootdemo.mapper.MessageMapper;
import org.example.jd20250417springbootdemo.model.MessageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-04-17
 * Time: 19:02
 */
@Service
public class MessageService {
    @Autowired
    private MessageMapper messageMapper;

    // 查询所有留言信息
    public List<MessageInfo> queryAll() {
        return messageMapper.queryAll();
    }

    // 存储留言
    public void addMessage(MessageInfo messageInfo) {
        messageMapper.insertMessage(messageInfo);
    }
}
