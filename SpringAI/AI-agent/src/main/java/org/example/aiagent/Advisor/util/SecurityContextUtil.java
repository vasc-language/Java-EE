package org.example.aiagent.Advisor.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 安全上下文工具类
 * 模拟用户的权限信息获取
 */
public class SecurityContextUtil {
    
    // 模拟当前用户的权限列表，实际项目中可能从会话、数据库或认证服务获取
    private static List<String> currentUserPermissions = new ArrayList<>();
    
    // 初始化权限（模拟）
    static {
        currentUserPermissions.add("READ");
        currentUserPermissions.add("WRITE");
    }
    
    /**
     * 检查当前用户是否拥有指定权限
     *
     * @param requiredPermissions 需要的权限列表
     * @return 是否有权限
     */
    public static boolean hasPermission(String... requiredPermissions) {
        if (requiredPermissions == null || requiredPermissions.length == 0) {
            return true;
        }
        
        for (String required : requiredPermissions) {
            if (!currentUserPermissions.contains(required)) {
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * 获取当前用户的所有权限
     *
     * @return 权限列表
     */
    public static List<String> getCurrentUserPermissions() {
        return new ArrayList<>(currentUserPermissions);
    }
    
    /**
     * 模拟添加权限（仅用于测试）
     *
     * @param permissions 权限
     */
    public static void addPermissions(String... permissions) {
        currentUserPermissions.addAll(Arrays.asList(permissions));
    }
    
    /**
     * 模拟移除权限（仅用于测试）
     *
     * @param permission 权限
     */
    public static void removePermission(String permission) {
        currentUserPermissions.remove(permission);
    }
    
    /**
     * 重置权限为默认状态（仅用于测试）
     */
    public static void resetPermissions() {
        currentUserPermissions.clear();
        currentUserPermissions.add("READ");
        currentUserPermissions.add("WRITE");
    }
} 