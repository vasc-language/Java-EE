package org.example.jd20250417springbootdemo.controller;

import org.example.jd20250417springbootdemo.model.MessageInfo;
import org.example.jd20250417springbootdemo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
// import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-04-17
 * Time: 19:08
 */
@RequestMapping("/message")
@RestController
public class MessageController {
    @Autowired
    private MessageService messageService;

    // 存储留言
    @PostMapping("/publish")
    public String publish(@RequestBody MessageInfo messageInfo) {
        // 验证参数格式是否正确
        if (!StringUtils.hasLength(messageInfo.getFrom())
            || !StringUtils.hasLength(messageInfo.getTo())
            || !StringUtils.hasLength(messageInfo.getMessage())) {
            return "{\"ok\": 0}";
        }
        messageService.addMessage(messageInfo);

        return "{\"ok\": 1}";
    }

    // 查询所有留言信息
    @GetMapping("/getList")
    public List<MessageInfo> getList() {
        return messageService.queryAll();
    }

}
