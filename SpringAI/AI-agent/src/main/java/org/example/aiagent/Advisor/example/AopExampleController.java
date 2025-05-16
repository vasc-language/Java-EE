package org.example.aiagent.Advisor.example;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.aiagent.Advisor.exception.PermissionDeniedException;
import org.example.aiagent.Advisor.exception.SensitiveWordsException;
import org.example.aiagent.Advisor.util.SecurityContextUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * AOP示例控制器
 * 用于演示AOP切面的使用
 */
@Slf4j
@RestController
@RequestMapping("/api/aop-example")
@RequiredArgsConstructor
public class AopExampleController {
    
    private final ContentServiceExample contentService;
    
    /**
     * 添加内容
     */
    @PostMapping("/content")
    public ResponseEntity<?> addContent(@RequestParam String content, @RequestParam Long userId) {
        try {
            return ResponseEntity.ok(contentService.addContent(content, userId));
        } catch (PermissionDeniedException e) {
            return ResponseEntity.status(403).body(e.getMessage());
        } catch (SensitiveWordsException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("服务器错误: " + e.getMessage());
        }
    }
    
    /**
     * 更新内容
     */
    @PutMapping("/content/{id}")
    public ResponseEntity<?> updateContent(@PathVariable Long id, @RequestParam String content) {
        try {
            return ResponseEntity.ok(contentService.updateContent(id, content));
        } catch (PermissionDeniedException e) {
            return ResponseEntity.status(403).body(e.getMessage());
        } catch (SensitiveWordsException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("服务器错误: " + e.getMessage());
        }
    }
    
    /**
     * 删除内容
     */
    @DeleteMapping("/content/{id}")
    public ResponseEntity<?> deleteContent(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(contentService.deleteContent(id));
        } catch (PermissionDeniedException e) {
            return ResponseEntity.status(403).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("服务器错误: " + e.getMessage());
        }
    }
    
    /**
     * 查看内容
     */
    @GetMapping("/content/{id}")
    public ResponseEntity<?> viewContent(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(contentService.viewContent(id));
        } catch (PermissionDeniedException e) {
            return ResponseEntity.status(403).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("服务器错误: " + e.getMessage());
        }
    }
    
    /**
     * 公共方法
     */
    @GetMapping("/public")
    public ResponseEntity<?> publicMethod() {
        return ResponseEntity.ok(contentService.publicMethod());
    }
    
    /**
     * 添加用户权限
     */
    @PostMapping("/permissions")
    public ResponseEntity<?> addPermission(@RequestParam String permission) {
        SecurityContextUtil.addPermissions(permission);
        return ResponseEntity.ok("权限添加成功: " + permission);
    }
    
    /**
     * 获取当前用户权限
     */
    @GetMapping("/permissions")
    public ResponseEntity<?> getPermissions() {
        return ResponseEntity.ok(SecurityContextUtil.getCurrentUserPermissions());
    }
} 