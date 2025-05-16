package org.example.aiagent.Advisor.exception;

/**
 * 权限校验异常
 */
public class PermissionDeniedException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;
    
    public PermissionDeniedException() {
        super("权限不足");
    }
    
    public PermissionDeniedException(String message) {
        super(message);
    }
    
    public PermissionDeniedException(String message, Throwable cause) {
        super(message, cause);
    }
} 