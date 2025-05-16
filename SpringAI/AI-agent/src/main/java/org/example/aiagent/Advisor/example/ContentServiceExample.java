package org.example.aiagent.Advisor.example;

import lombok.extern.slf4j.Slf4j;
import org.example.aiagent.Advisor.annotation.PermissionCheck;
import org.example.aiagent.Advisor.annotation.SensitiveWordsCheck;
import org.springframework.stereotype.Service;

/**
 * 内容服务示例
 * 用于演示AOP切面的使用
 */
@Slf4j
@Service
public class ContentServiceExample {
    
    /**
     * 添加内容
     * 需要ADMIN权限，并且内容不能包含敏感词
     *
     * @param content 内容
     * @param userId  用户ID
     * @return 操作结果
     */
    @PermissionCheck({"ADMIN"})
    @SensitiveWordsCheck(paramNames = {"content"}, level = SensitiveWordsCheck.CheckLevel.ERROR)
    public String addContent(String content, Long userId) {
        log.info("添加内容: {}, 用户ID: {}", content, userId);
        return "内容添加成功: " + content;
    }
    
    /**
     * 更新内容
     * 需要WRITE权限，并且内容不能包含敏感词
     *
     * @param id      内容ID
     * @param content 内容
     * @return 操作结果
     */
    @PermissionCheck({"WRITE"})
    @SensitiveWordsCheck(level = SensitiveWordsCheck.CheckLevel.WARN)
    public String updateContent(Long id, String content) {
        log.info("更新内容, ID: {}, 新内容: {}", id, content);
        return "内容更新成功, ID: " + id;
    }
    
    /**
     * 删除内容
     * 需要ADMIN权限
     *
     * @param id 内容ID
     * @return 操作结果
     */
    @PermissionCheck({"ADMIN"})
    public String deleteContent(Long id) {
        log.info("删除内容, ID: {}", id);
        return "内容删除成功, ID: " + id;
    }
    
    /**
     * 查看内容
     * 需要READ权限
     *
     * @param id 内容ID
     * @return 内容
     */
    @PermissionCheck({"READ"})
    public String viewContent(Long id) {
        log.info("查看内容, ID: {}", id);
        return "这是ID为" + id + "的内容";
    }
    
    /**
     * 跳过权限检查的方法
     *
     * @return 结果
     */
    @PermissionCheck(skipCheck = true)
    public String publicMethod() {
        log.info("执行公共方法");
        return "公共方法执行成功";
    }
} 