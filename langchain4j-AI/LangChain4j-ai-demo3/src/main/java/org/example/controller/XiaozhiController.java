package org.example.controller;

import dev.langchain4j.memory.ChatMemory;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.assistant.XiaozhiAgent;
import org.example.bean.ChatForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-05-03
 * Time: 17:07
 */
@Tag(name = "xiaozhiAgent")
@RequestMapping("/agent")
@RestController
public class XiaozhiController {
    @Autowired
    private XiaozhiAgent xiaozhiAgent;

    @Operation(summary = "对话")
    @GetMapping("/chat")
    public String chat(@RequestBody ChatForm chatForm) {
        return xiaozhiAgent.chat(chatForm.getMemoryId(), chatForm.getMessage());
    }
}
