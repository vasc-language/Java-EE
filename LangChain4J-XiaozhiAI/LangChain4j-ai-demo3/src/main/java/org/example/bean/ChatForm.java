package org.example.bean;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * Description: 封装对话对象
 * User: 姚东名
 * Date: 2025-05-03
 * Time: 17:04
 */
@Data
public class ChatForm {
    private Long memoryId; // 对话Id
    private String message; // 用户问题
}
