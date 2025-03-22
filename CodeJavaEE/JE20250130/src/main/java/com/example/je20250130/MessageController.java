package com.example.je20250130;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description: 留言板后端处理
 * User: 姚东名
 * Date: 2025-03-04
 * Time: 21:46
 */
@RequestMapping("/message")
@RestController
public class MessageController {
    // 保存留言数据，重新刷新后，再次调用 list 后，还是会出现留言数据
    private List<MessageInfo> messageInfoList = new ArrayList<>();

    // 发表新留言
    @PostMapping(value = "/publish", produces = "application/json")
    public String publish(@RequestBody MessageInfo messageInfo) {
        if (!StringUtils.hasLength(messageInfo.getMessage())
        || !StringUtils.hasLength(messageInfo.getFrom())
        || !StringUtils.hasLength(messageInfo.getTo())) {
            return "{\"ok\": 0}";
        }
        messageInfoList.add(messageInfo);
        return "{\"ok\": 1}";
    }

    // 获取全部留言
    @GetMapping("/getList")
    public List<MessageInfo> getList() {
        return messageInfoList;
    }
}
